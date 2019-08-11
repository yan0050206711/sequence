package test;

import com.tstd2.client.sequence.DayByDaySequence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:sequence-context.xml")
@ActiveProfiles("test")
public class DayByDaySequenceTest {

	@Resource(name = "dayByDaySequence")
	private DayByDaySequence dayByDaySequence;

	@Test
	public void testNextValue() {
		for (int i = 0; i < 10; i++) {
			Date currentDay = new Date();
			if (i >= 5) {
				Calendar c = Calendar.getInstance();
				c.setTime(currentDay);
				c.add(Calendar.DAY_OF_MONTH, 1);
				currentDay = c.getTime();
			}
			long value = this.dayByDaySequence.nextValue("test", currentDay);
			System.out.println(value);
		}
	}
	
	@Test
	public void testNextValueMulti() {
		
		final String appName = "test";
		
		int nThreads = 10;
		int num = 1000000;
		
		ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);
		CompletionService<Long> completionService = new ExecutorCompletionService<Long>(threadPool);

		long start = System.currentTimeMillis();

		for (int i = 0; i < num; i++) {
			final int seq = i;
			completionService.submit(new Callable<Long>() {

				@Override
				public Long call() throws Exception {
					Date currentDay = new Date();
//					if (seq >= 5) {
//						Calendar c = Calendar.getInstance();
//						c.setTime(currentDay);
//						c.add(Calendar.DAY_OF_MONTH, 1);
//						currentDay = c.getTime();
//					}
					long value = dayByDaySequence.nextValue(appName, currentDay);
					return value;
				}
			});
		}


		Set<Long> set = new ConcurrentSkipListSet<Long>();

		for (int i = 0; i < num; i++) {
			try {
				Long value = completionService.take().get();

				if (!set.add(value)) {
					System.out.println("出现重复。。。" + value);
				}

//				System.out.println(value);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}


		
		threadPool.shutdown();
		
	}

}
