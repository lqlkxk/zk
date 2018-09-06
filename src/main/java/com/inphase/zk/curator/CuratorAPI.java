package com.inphase.zk.curator;

import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheMode;
import org.apache.curator.framework.recipes.nodes.PersistentEphemeralNode;
import org.apache.curator.framework.recipes.nodes.PersistentEphemeralNode.Mode;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * All rights Reserved, Designed By inphase.com
 * 
 * @Title: CuratorAPi.java
 * @Package com.inphase.zk.curator
 * @Description: curator Api
 * @author: ljq
 * @date: 2018年9月6日 下午1:37:30
 * @version V1.0
 */
public class CuratorAPI {
	public static void main(String[] args) {
		try {
			CuratorFramework client = getInstance();
			//创建节点
//			String rusult = client.create().creatingParentsIfNeeded().forPath("/curator", "curator".getBytes());
//			System.out.println(rusult);
			//获得节点值
//			byte[] bytes = client.getData().forPath("/curator");
//			System.out.println(new String(bytes));
			//修改值
			//client.setData().forPath("/curator", "test".getBytes());
			//删除节点
			//client.delete().forPath("/curator");
//			Stat t = client.checkExists().forPath("/testt");
//			System.out.println(t);
			/*临时节点
			PersistentEphemeralNode pnode = new PersistentEphemeralNode(client, Mode.EPHEMERAL, "/test/test","1111".getBytes());
			pnode.start();
			pnode.waitForInitialCreate(5, TimeUnit.SECONDS);
			byte[] bytes = client.getData().forPath("/test/test");
			System.out.println(new String (bytes));*/
			
		//监听 
		// NodeCache
		// 首先构造了一个NodeCache实例，然后调用start方法，该方法有个boolean类型的参数，默认是false，
		// 如果设置为true，那么NodeCache在第一次启动的时候就会立刻从ZooKeeper上读取对应节点的数据内容，
		// 并保存在Cache中。
		// NodeCache不仅可以用于监听数据节点的内容变更，也能监听指定节点是否存在。如果原本节点不存在，
		// 那么Cache就会在节点被创建后触发NodeCacheListener。但是，如果该数据节点被删除，
		// 那么Curator就无法触发NodeCacheListener了。
		//·PathChildrenCache 
		// 当指定节点的子节点发生变化时，就会回调该方法。PathChildrenCacheEvent类中定义了所有的事件类型，
		// 主要包括新增子节点（CHILD_ADDED）、子节点数据变更（CHILD_UPDATED）和子节点删除（CHILD_REMOVED）三类。

//			PathChildrenCache watcher = new PathChildrenCache(client, "/test", PathChildrenCacheMode.CACHE_DATA);
//			watcher.getListenable().addListener(new PathChildrenCacheListener() {
//				
//				@Override
//				public void childEvent(CuratorFramework curator, PathChildrenCacheEvent event) throws Exception {
//					System.out.println(" type "+event.getType() +" 路径 ："+event.getData().getPath()+" 结果" +event.getData());
//				}
//			});
//			watcher.start(StartMode.BUILD_INITIAL_CACHE);
//			client.create().forPath("/test/test1","test".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
				
		}
	}
	
	public static CuratorFramework getInstance(){
		
		RetryPolicy  retry = new ExponentialBackoffRetry(1000, 3); //重试策略
		
		CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.56.101:2181", 5000, 5000, retry);
		
		client.start();
		
		return client;
	}
}
