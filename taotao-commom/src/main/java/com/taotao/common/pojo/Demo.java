package com.taotao.common.pojo;

import com.taotao.common.utils.FastDFSClient;

public class Demo {
	public static void main(String[] args) {
		
		try {
			FastDFSClient client = new FastDFSClient(
					"F:\\eclipse-oxygen-workspace\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
			client.uploadFile("F:\\picture\\1.jpg", "jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
