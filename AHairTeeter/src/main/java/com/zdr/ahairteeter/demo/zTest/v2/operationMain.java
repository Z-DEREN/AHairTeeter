package com.zdr.ahairteeter.demo.zTest.v2;

import com.zdr.ahairteeter.demo.zTest.v2.bao.*;

import java.util.Scanner;

public class operationMain {

    public static void main(String[] args) {
        System.out.println("输入");
        Scanner input = new Scanner(System.in);
        String name = input.next();
        testabstract testabstract;
        switch (name) {
            case "testno1":
                testabstract = new testno1();
                testabstract.testno1A();
                break;
            case "testno2":
                testabstract = new testno2();
                testabstract.testno2A();
                break;
            case "testno3":
                testabstract = new testno3();
                testabstract.testno3A();
                break;
            case "testno4":
                testabstract = new testno4();
                testabstract.testno4A();
                break;
            default:
                break;
        }
    }

}
