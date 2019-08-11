package test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author yancey
 * @date 2018/3/26 20:14
 */
public class ThreadPoolTest {

    private static ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) throws InterruptedException {
        ScheduledFuture future = execute();

        Thread.sleep(5000);

//        service.shutdown();
future.cancel(true);
        System.out.println("---");

//        service = Executors.newSingleThreadScheduledExecutor();

//        future.cancel(false);

        execute();
    }

    private static ScheduledFuture execute() {
        ScheduledFuture future = service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
            }
        }, 1, 1, TimeUnit.SECONDS);

        return future;
    }
}
