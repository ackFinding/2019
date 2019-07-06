
import com.lock.DistributedLock;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class ZookeeperLockTest {

    private static final Logger logger = LoggerFactory.getLogger(ZookeeperLockTest.class);

    private static ZkClient zkClient = new ZkClient("127.0.0.1");
    private static volatile int count = 100;

    public static void secKill() {
        if (count > 0) {
            count--;
            logger.info("{} got {}",Thread.currentThread().getName() , count);
        }else {
            logger.info("售卖完毕!");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
//        zkClient.deleteRecursive("/Animal");
        Runnable runnable = () -> {
            DistributedLock lock = new DistributedLock("/Animal", zkClient);
//            while (!lock.tryLock(1000, TimeUnit.SECONDS));//①
            lock.lock();//②
            try {
               secKill();
            } finally {
                lock.unLock();
            }
        };
        for (int i = 0; i < 300; i++) {
            new Thread(runnable).start();
        }
    }
}
