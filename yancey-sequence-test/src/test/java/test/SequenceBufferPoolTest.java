package test;

import com.tstd2.client.sequence.SequenceBufferPool;
import com.tstd2.client.sequence.SequenceBufferPoolByGuava;


public class SequenceBufferPoolTest {
	
	static SequenceBufferPool<String, Integer> pool = new SequenceBufferPool<String, Integer>();

//	static SequenceBufferPoolByGuava<String, Integer> pool = new SequenceBufferPoolByGuava<String, Integer>();

	public static void main(String[] args) {
		Long s = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
//			pool.offer("test", i);
			pool.poll("test");
		}

		System.out.println(System.currentTimeMillis() - s);
	}

}
