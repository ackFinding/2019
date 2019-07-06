package lock;

import com.lock.RedisLock;
import com.lock.TtlEnduranceTask;
import com.lock.utils.TimerUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RedisLockTest {

    private static final Logger logger = LoggerFactory.getLogger(RedisLockTest.class);

    private static final String LOCK_KEY = "lockKey";//锁

    private static final String requestId = UUID.randomUUID().toString();//加锁唯一标识

    private static final int EXPIRE_TIME = 3000;//单位：毫秒

    private static final int TTL = 2;//单位：秒

    private static int count = 100;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 600; i++) {
            new Thread(() -> {
                try {
                    secKill();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void secKill() throws InterruptedException {
        Jedis jedis = new Jedis("192.168.99.100", 6379,100000);
        //加锁
        while (!RedisLock.tryLock(jedis, LOCK_KEY, requestId, EXPIRE_TIME));
        //添加定时任务
        TtlEnduranceTask ttlEnduranceTask = new TtlEnduranceTask(jedis, LOCK_KEY, TTL, Thread.currentThread().getName());
        Timer timer = TimerUtils.scheduleEnduranceTask("redis-lock-ttl-endurance", ttlEnduranceTask, 2900, 2000);
        service();
        //停止定时任务
        timer.cancel();
        //解锁
        RedisLock.unLock(jedis, LOCK_KEY, requestId);
    }

    private static void service(){
        if (count > 0) {
            count--;
            logger.info("{} 抢购成功,剩余:{}", Thread.currentThread().getName(), count);
        } else {
            logger.info("商品售空,抢购结束!");
            System.exit(0);
        }
        sleep(2900);//模拟超时
        logger.info("{} 结束!!!", Thread.currentThread().getName());
    }

    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
