package com.lock;


import com.exception.LockException;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DistributedLock {

    private static final Logger logger = LoggerFactory.getLogger(DistributedLock.class);
    private CountDownLatch lock;
    private String lockPath;
    private ZkClient zkClient;
    private String currentNode;
    private LockListener lockListener;
    private String watchNode;
    private boolean holdLock;
    private String currentId;

    public DistributedLock(String lockPath, ZkClient zkClient) {
        this.lockPath = lockPath;
        this.zkClient = zkClient;
        this.lockListener = new LockListener();
        this.lock = new CountDownLatch(1);
        this.holdLock = false;
    }

    public void lock() {
        if (holdLock) {
            throw new LockException("[" + currentNode + "] already got a lock!");
        }
        checkPath(lockPath);
        currentNode = zkClient.createEphemeralSequential(lockPath + "/member_", null);
        currentId = currentNode.substring(currentNode.lastIndexOf("/") + 1);
        logger.info("current node :{}", currentNode);
        lockListener.checkGetLock();
        try {
            lock.await();
            if (!holdLock) {
                throw new LockException("Error, [" + currentNode + " ]couldn't acquire the lock!");
            }
            logger.info("{} get lock success", currentNode);
        } catch (InterruptedException e) {
            cancelAttempt();
            throw new LockException("InterruptedException while trying to acquire lock!", e);
        }
    }


    public boolean tryLock(long timeout, TimeUnit unit) {
        if (holdLock) {
            throw new LockException("[" + currentNode + "] already got a lock!");
        }
        checkPath(lockPath);
        currentNode = zkClient.createEphemeralSequential(lockPath + "/member_", null);
        currentId = currentNode.substring(currentNode.lastIndexOf("/") + 1);
        logger.info("current node :{}", currentNode);
        lockListener.checkGetLock();
        try {
            boolean success = lock.await(timeout, unit);
            if (!success) {
                return false;
            }
            if (!holdLock) {
                throw new LockException("Error, [" + currentNode + " ]couldn't acquire the lock!");
            }
            logger.info("{} get lock success", currentNode);
        } catch (InterruptedException e) {
            cancelAttempt();
            return false;
        }
        return true;
    }

    /**
     * 确保创建了结点
     * @param path
     */
    private void checkPath(String path) {
        if (path == null) {
            throw new LockException("path cannot be null");
        }
        if (!zkClient.exists(path)) {
            //递归检查前面的路径
            int index = path.lastIndexOf("/");
            if (index > 0) {
                checkPath(path.substring(0, index));
            }
            try {
                zkClient.createPersistent(path);
            } catch (ZkNodeExistsException e) {
                logger.info("Node existed when trying to ensure path " + path + ", somebody beat us to it?");
            }
        }
    }

    public void unLock() {
        if (currentNode == null || !holdLock) {
            logger.info("Try to unlock,but does not hold one!");
            return;
        }
        if (zkClient.exists(currentNode)) {
            zkClient.delete(currentNode);
        }
        logger.info("{} release lock ", currentNode);
    }


    private void cancelAttempt() {
        lock.countDown();
        unLock();
    }

    /**
     * 给指定路径结点添加监听器
     * @param path
     */
    private void subscribeDataChanges(String path) {
        zkClient.subscribeDataChanges(path, lockListener);
    }


    class LockListener implements IZkDataListener {

        public void checkGetLock() {
            List<String> children = new ArrayList<>();
            children.addAll(zkClient.getChildren(lockPath));
            Collections.sort(children);
            if (children.get(0).equals(currentId)) {
                lock.countDown();
                holdLock = true;
            } else {
                String prevNode = children.get(children.indexOf(currentId) - 1);
                watchNode = String.format("%s/%s", lockPath, prevNode);
                subscribeDataChanges(watchNode);
                logger.info("currentNode:{}->watchNode:{}", currentNode, watchNode);
            }
        }

        @Override
        public void handleDataChange(String dataPath, Object data) throws Exception {

        }

        @Override
        public void handleDataDeleted(String dataPath) throws Exception {
            checkGetLock();
        }
    }

}
