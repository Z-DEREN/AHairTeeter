package com.zdr.ahairteeter.demo.zTest.v4.bao;


public  class factory   {


    public static testabstract getfactory(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        testabstract testabstract;
        System.out.println("工厂 元类  反射");
        String fileurl = "com.zdr.ahairteeter.demo.zTest.v4.bao."+name;
        Class<?> cla = Class.forName(fileurl);
        Object obj = cla.newInstance();
        return (testabstract)obj;
    }

}
