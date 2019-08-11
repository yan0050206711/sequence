package test;

import com.tstd2.client.sequence.GlobalSequence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:sequence-context.xml")
@ActiveProfiles("test")
public class GlobalSequenceTest {

	@Resource(name = "sequence")
	private GlobalSequence globalSequence;
	
	@Test
	public void testNextValue() throws Exception {
	    long start = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			long value = this.globalSequence.nextValue("test");
			System.out.println(value);
		}
		long end = System.currentTimeMillis();
        System.out.println("---------------" + (end - start));

    }
	
	@Test
	public void testNextValueMulti() {
		
		final String bussCode = "test";
		
		int nThreads = 10;
		int num = 1000000;
		
		ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);
		CompletionService<Long> completionService = new ExecutorCompletionService<Long>(threadPool);

        long start = System.currentTimeMillis();

		for (int i = 0; i < num; i++) {
			completionService.submit(new Callable<Long>() {

				@Override
				public Long call() throws Exception {
					long value = globalSequence.nextValue(bussCode);
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

        long end = System.currentTimeMillis();
		System.out.println("---------------count:" + set.size());
        System.out.println("---------------" + (end - start));

		threadPool.shutdown();
		
	}
}
