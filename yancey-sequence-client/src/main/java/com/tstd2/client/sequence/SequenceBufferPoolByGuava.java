package com.tstd2.client.sequence;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;

/**
 * 号池
 * 
 * @author yancey
 * @date:2016年5月28日 下午2:50:46
 * @version V1.0
 * 
 */
public class SequenceBufferPoolByGuava<K, V> {

	/**
	 * 序列号缓冲区
	 */
	private final LoadingCache<K, Queue<V>> SEQUENCE_BUFFER = CacheBuilder.newBuilder().build(new CacheLoader<K, Queue<V>>() {
		@Override
		public Queue<V> load(K key) throws Exception {
			return new ConcurrentLinkedQueue<V>();
		}

	});

	/**
	 * 入队
	 */
	public void offer(K appName, V value) {
		
		try {
			SEQUENCE_BUFFER.get(appName).offer(value);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 弹出一个值
	 */
	public V poll(K appName) {
		Queue<V> queue = null;
		try {
			queue = SEQUENCE_BUFFER.get(appName);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		if (queue == null) {
			return null;
		}

		return queue.poll();
	}

	public void clear(K appName) {
		Queue<V> queue = null;
		try {
			queue = SEQUENCE_BUFFER.get(appName);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		if (queue == null) {
			return;
		}

		queue.clear();
	}

	public int size(K appName) {
		Queue<V> queue = null;
		try {
			queue = SEQUENCE_BUFFER.get(appName);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		if (queue == null) {
			return 0;
		}

        return queue.size();
    }

}
