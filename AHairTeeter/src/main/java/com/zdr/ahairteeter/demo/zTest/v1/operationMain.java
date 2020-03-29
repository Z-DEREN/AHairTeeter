package com.zdr.ahairteeter.demo.zTest.v1;

import com.zdr.ahairteeter.demo.zTest.v1.bao.testno1;
import com.zdr.ahairteeter.demo.zTest.v1.bao.testno2;
import com.zdr.ahairteeter.demo.zTest.v1.bao.testno3;
import com.zdr.ahairteeter.demo.zTest.v1.bao.testno4;

import java.util.Scanner;

public class operationMain {

    public static void main(String[] args) {
        System.out.println("输入");
        Scanner input = new Scanner(System.in);
        String name = input.next();
        switch (name) {
            case "testno1":
                testno1 testno1 = new testno1();
                testno1.testno1A();
                break;
            case "testno2":
                testno2 testno2 = new testno2();
                testno2.testno2A();
                break;
            case "testno3":
                testno3 testno3 = new testno3();
                testno3.testno3A();
                break;
            case "testno4":
                testno4 testno4 = new testno4();
                testno4.testno4A();
                break;
            default:
                break;
        }
    }

}
