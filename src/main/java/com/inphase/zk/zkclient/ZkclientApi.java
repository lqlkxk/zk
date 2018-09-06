package com.inphase.zk.zkclient;

import org.I0Itec.zkclient.ZkClient;

/**  
 * All rights Reserved, Designed By inphase.com
 * @Title:  ZkclientApi.java   
 * @Package com.inphase.zk.zkclient   
 * @Description:    zkClient api   
 * @author: ljq 
 * @date:   2018年9月6日 上午11:18:10   
 * @version V1.0 
 */
public class ZkclientApi {
	
	public static void main(String[] args) {
		ZkClient zk = new ZkClient("192.168.56.101:2181");
		zk.createPersistent("/test", "123".getBytes());
		byte[] bytes =zk.readData("/test");
		System.out.println(new String(bytes));
	}
	
}

