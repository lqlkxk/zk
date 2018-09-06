package com.inphase.zk.javaapi;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Ids;

/**  
 * All rights Reserved, Designed By inphase.com
 * @Title:  ZKApi.java   
 * @Package com.inphase.zk   
 * @Description: zookeeper API 
 * @author: ljq 
 * @date:   2018年9月5日 下午5:26:20   
 * @version V1.0 
 */
public class ZKApi implements Watcher {
	public static  CountDownLatch count = new  CountDownLatch(1);
	public static ZooKeeper zk ;
	@Override
	public void process(WatchedEvent event) {
		if(event.getState() == KeeperState.SyncConnected){
			if (EventType.None == event.getType() && null == event.getPath()) {
				count.countDown();
                System.out.println("Zookeeper session established");
            } else if (EventType.NodeCreated == event.getType()) {
                System.out.println("success create znode");

            } else if (EventType.NodeDataChanged == event.getType()) {
                System.out.println("success change znode: " + event.getPath());

            } else if (EventType.NodeDeleted == event.getType()) {
                System.out.println("success delete znode");

            } else if (EventType.NodeChildrenChanged == event.getType()) {
                System.out.println("NodeChildrenChanged");
            }
		}
	}
	
	
	public static void main(String[] args) {
		ZKApi api = new ZKApi();
		try {
			 zk = new ZooKeeper("192.168.56.101:2181", 5000,api );
			count.await();
			String result = zk.create("/zkclienttest/test", "456".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println(result);
			Stat stat = new Stat();
//			byte [] bytes = zk.getData("/zkclienttest", api,stat );
//			System.out.println("result" +new String(bytes) +"stat "+stat);
//			zk.delete("/zkclienttest", -1);
//			zk.setData("/zkclienttest", "456".getBytes(), -1);
//			byte [] bytes = zk.getData("/zkclienttest", api,stat );
//			System.out.println("result" +new String(bytes) +"stat "+stat);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
				
		}
		
	}
	 
	
}