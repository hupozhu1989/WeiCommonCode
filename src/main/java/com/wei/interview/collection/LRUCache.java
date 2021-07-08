package com.wei.interview.collection;

import java.util.LinkedHashMap;
//https://blog.csdn.net/u013568373/article/details/90607083
public class LRUCache<K,V> extends LinkedHashMap<K, V> {
	
	//首先设定最大缓存空间 MAX_ENTRIES 为 3；
	private final int maximumSize;
	
	//之后使用LinkedHashMap的构造函数将 accessOrder设置为 true，开启LRU顺序；
	public LRUCache(final int maximumSize) {
		super(maximumSize, 0.75f, true);
		this.maximumSize = maximumSize;
	}
	
	//最后覆盖removeEldestEntry(）方法实现，在节点多于 MAX_ENTRIES 就会将最近最少使用的数据移除。
	//因为这个函数默认返回false，不重写的话缓存爆了的时候无法删除最近最久未使用的节点
	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		//在容量超过最大允许节点数的时候返回true，使得在afterNodeInsertion函数中能执行removeNode()
		return size() > maximumSize;
	}
	
	public static void main(String[] args) {
	    LRUCache<Integer, Integer> cache = new LRUCache<>(3);
		cache.put(1, 1);
		cache.put(2, 2);
		cache.put(3, 3);
		Integer i2 = cache.get(2);// 返回2
		cache.put(4, 4);    // 该操作会使得关键字 1 作废
		Integer i1 = cache.get(1);       // 返回 null
		Integer i3 = cache.get(3);       // 返回 -1 (未找到)
		cache.put(5, 5);    // 该操作会使得关键字 1 作废
		Integer i2_2 = cache.get(2);       // 返回null
		Integer i4 = cache.get(4);       // 返回  4
		Integer i3_3 = cache.get(3);       // 返回 3
	}
}
