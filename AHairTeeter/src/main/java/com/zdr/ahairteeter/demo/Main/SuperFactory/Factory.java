package com.zdr.ahairteeter.demo.Main.SuperFactory;

import com.zdr.ahairteeter.demo.Main.ThreadExecutionMethod.Warehouse.WarehouseImpl;

//工厂类
//用于达到稳定代码作用
public class Factory {

    //用于简化爬虫与多线程工厂实例化方法
    public static WarehouseImpl GetThreadService(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //根据用户选择名字读取指定模块
        WarehouseImpl WarehouseImpl ;
        String classfileName = "com.zdr.ahairteeter.demo.Main.ThreadExecutionMethod.Warehouse."+className;
        Class cla = Class.forName(classfileName);
        Object obj = cla.newInstance();
        return (WarehouseImpl)obj;
    }
    //用于简化获取实体
    /*public static pojoclassImpl pojoclass(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        pojoclassImpl pojoclassImpl;
        String classfileName = "com.zdr.ahairteeter.demo.Main.Vo"+className;
        Class cla = Class.forName(classfileName);
        Object obj = cla.newInstance();
        return (pojoclassImpl)obj;
    }*/


    /*//用于读取外部文件参数方法(待定)
    public static List<Object> GetFilevalueImpl(String valueName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //根据用户选择名字读取指定外部参数
        WarehouseImpl WarehouseImpl ;
        String classfileName = "com.zdr.ahairteeter.demo.Main.ThreadExecutionMethod.Warehouse."+className;
        Class cla = Class.forName(classfileName);
        Object obj = cla.newInstance();
        return (WarehouseImpl)obj;
    }
*/


}
