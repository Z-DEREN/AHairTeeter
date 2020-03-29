package com.zdr.ahairteeter.demo.zTest.v4;

import com.zdr.ahairteeter.demo.zTest.v4.bao.factory;
import com.zdr.ahairteeter.demo.zTest.v4.bao.testabstract;

import java.util.Scanner;

public class operationMain {

    /*public static void main(String[] args) {
        System.out.println("输入");
        Scanner input = new Scanner(System.in);
        String name = input.next();
        switch (name) {
            case "testno1":
                com.zdr.ahairteeter.demo.zTest.v1.bao.testno1 testno1 = new com.zdr.ahairteeter.demo.zTest.v1.bao.testno1();
                testno1.testno1A();
                break;
            case "testno2":
                com.zdr.ahairteeter.demo.zTest.v1.bao.testno2 testno2 = new com.zdr.ahairteeter.demo.zTest.v1.bao.testno2();
                testno2.testno2A();
                break;
            case "testno3":
                com.zdr.ahairteeter.demo.zTest.v1.bao.testno3 testno3 = new com.zdr.ahairteeter.demo.zTest.v1.bao.testno3();
                testno3.testno3A();
                break;
            case "testno4":
                com.zdr.ahairteeter.demo.zTest.v1.bao.testno4 testno4 = new com.zdr.ahairteeter.demo.zTest.v1.bao.testno4();
                testno4.testno4A();
                break;
            default:
                break;
        }
    }*/


    /*public static void main(String[] args) {
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
    }*/


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        System.out.println("输入");
        Scanner input = new Scanner(System.in);
        testabstract testabstract = factory.getfactory(input.next());
        testabstract.testA();
    }




}
