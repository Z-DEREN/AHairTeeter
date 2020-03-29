package com.zdr.ahairteeter.demo.zTest.v3.bao;


import com.zdr.ahairteeter.demo.zTest.v3.bao.testabstract;
import com.zdr.ahairteeter.demo.zTest.v3.bao.testno1;
import com.zdr.ahairteeter.demo.zTest.v3.bao.testno2;
import com.zdr.ahairteeter.demo.zTest.v3.bao.testno3;
import com.zdr.ahairteeter.demo.zTest.v3.bao.testno4;

public  class factory   {

    public static testabstract getfactory(String name){
        testabstract testabstract = null;
        switch (name) {
            case "testno1":
                testabstract = new testno1();
                break;
            case "testno2":
                testabstract = new testno2();
                break;
            case "testno3":
                testabstract = new testno3();
                break;
            case "testno4":
                testabstract = new testno4();
                break;
            default:
                break;
        }
        return testabstract;
    }



}
