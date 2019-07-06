package watch.seckill;

import com.watch.seckill.SecKillDemo;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class SecKillTest {

    private static String key = "macbook";

    private static String num = "100";

    private static ExecutorService executorService = Executors.newFixedThreadPool(8, new MyThreadFactory());

    @Before
    public void before() {
        Jedis jedis = new Jedis("192.168.99.100");
        String script = "redis.call('del',KEYS[1]);return redis.call('set',KEYS[1],ARGV[1])";
        jedis.eval(script, Collections.singletonList(key), Collections.singletonList(num));
        jedis.close();
    }

    @Test
    public void test() {

    }

    public static void main(String[] args) {
        try{
            for (int i = 1; i <= 600; i++) {
                executorService.submit(new SecKillDemo("顾客"+i,key));
            }
        }finally {
            executorService.shutdown();
        }

    }

    static class MyThreadFactory implements ThreadFactory {

        private final AtomicInteger threadNumber = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "顾客" + threadNumber.getAndIncrement());
        }
    }
}
