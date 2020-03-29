package com.zdr.ahairteeter.demo.Tool;

import java.util.ArrayList;
import java.util.List;

public class testmain {
	public static void main(String[] args) {
		testmain testmain = new testmain();
		testmain.test();
	}

	public void test() {
		Tool tool = new Tool();
		codec codec = new codec();

		String MD5 = tool.Md5("zhangderen");
		String password = "zhangderen";
		System.out.println("数据库内MD5:"+MD5);
		String puzzlekey = "11100";
		
		List<Object> EPKEY = new ArrayList<Object>();
		EPKEY.add("s");
		EPKEY.add("z");
		EPKEY.add("z");
		EPKEY.add("s");
		EPKEY.add("s");
		
		String passMD5 = MD5;
		String[] puzzKey = puzzlekey.split("");
		for (int num = 0; num < puzzKey.length; num++) {
			passMD5 = codec.puzzEncrypt(EPKEY.get(num).toString(), passMD5, puzzKey[num]);
		}
		
		String shaMD5 = tool.Md5(password+MD5,passMD5);
		
		System.out.println(shaMD5);

		
		
		
	}
	
	
	

}
