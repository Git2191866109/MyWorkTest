package com.mw.java.test.catchtest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wei.ma on 2016/9/30.
 * 创建一个代理类
 */
public class IOrgServiceCachePxy implements InvocationHandler {
    private Object target;

    /**
     * 获取代理对象，必须是个实例（在这个对象的基础进行添加其它行为）
     *
     * @param target
     * @return
     */
    public Object getProxyInstance(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * 当被代理的对象的任何业务方法被调用时，invoke方法被触发，invoke方法获取这个业务方法的方法信息和参数信息
     * 可以实现对这个方法的前置和后置操作
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println(Thread.currentThread().getName() + ": 进入IOrgServiceCachePxy。。。");
        if (method.getName().equals("findAllDepartment")) {
            //System.out.println("args[0]:"+args[0]+",args:[1]"+args[1]+",args[2]:"+args[2]);
            result = findAllDepartment(method, args);
        } else if (method.getName().equals("findDeptByActDeptId")) {
            // System.out.println("args[0]:"+args[0]+",args[1]:"+args[1]);
            result = findDeptByActDeptId(method, args);
        } else if (method.getName().equals("findUserByUserId")) {
            result = findUserByUserId(method, args);
        } else if (method.getName().equals("findUserByDeptId")) {
            result = findUserByDeptId(method, args);
        } else { //非过滤条件
            result = method.invoke(target, args);
        }
        // System.out.println(Thread.currentThread().getName() +": 22222222222222222");
        // System.out.println("after target method...");
        return result;
    }

    // 获得所有的部门
    private Object findAllDepartment(Method method, Object[] args)
            throws IllegalAccessException, InvocationTargetException {
        Object result;
//        // 只有符合参数要求的方法才会进行缓存处理（只对获取当前有效部门列表）
//        if (args[0].equals("0") && args[1].equals("y") && args[2].equals(0)) {
//            // System.out.println(Thread.currentThread().getName() +": 进入IOrgServiceCachePxy。。。2");
//            //System.out.println("方法可进行缓存");
//            String key = "findAllDepartment_0_y_0";
//            if (OrgCachePools.allDeptPools.containsKey(key)) {
//                //System.out.println("从缓存中获取"+key);
//                return OrgCachePools.allDeptPools.get(key);
//            }
//            result = method.invoke(target, args);
//            final ArrayOfDepartment arrayDept = (ArrayOfDepartment) result;
//            OrgCachePools.allDeptPools.put(key, arrayDept);
//        } else {
//            //System.out.println(Thread.currentThread().getName() +": 进入IOrgServiceCachePxy。。。3");
//            //System.out.println("不符合缓存条件");
            result = method.invoke(target, args);
//        }
        return result;
    }

    // 获得部门对象 根据实体id
    private Object findDeptByActDeptId(Method method, Object[] args)
            throws IllegalAccessException, InvocationTargetException {
        Object result;
        // 只有符合参数要求的方法才会进行缓存处理（只对获取当前有效部门列表）
//        if (args[1].equals(0)) {
//            // System.out.println(Thread.currentThread().getName() +": 进入IOrgServiceCachePxy。。。2");
//            //System.out.println("方法可进行缓存");
//            String key = args[0] + "_0";
//            if (OrgCachePools.deptPools.containsKey(key)) {
//                // System.out.println("从缓存中获取"+key);
//                return OrgCachePools.deptPools.get(key);
//            }
//            result = method.invoke(target, args);
//            if (result != null) {
//                Department dept = (Department) result;
//                OrgCachePools.deptPools.put(key, dept);
//            }
//        } else {
            //System.out.println(Thread.currentThread().getName() +": 进入IOrgServiceCachePxy。。。3");
            // System.out.println("方法参数不符");
            result = method.invoke(target, args);
//        }
        return result;
    }

    // 获得用户对象
    private Object findUserByUserId(Method method, Object[] args)
            throws IllegalAccessException, InvocationTargetException {
        Object result;
        // 只有符合参数要求的方法才会进行缓存处理（只对获取当前有效部门列表）
//        if (args[1].equals(0)) {
            // System.out.println(Thread.currentThread().getName() +": 进入IOrgServiceCachePxy。。。2");
            //System.out.println("方法可进行缓存");
//            String key = args[0] + "_0";
//            if (OrgCachePools.userPools.containsKey(key)) {
//                // System.out.println("从缓存中获取"+key);
//                return OrgCachePools.userPools.get(key);
//            }
//            result = method.invoke(target, args);
//            User dept = (User) result;
//            OrgCachePools.userPools.put(key, dept);
//        } else {
            //System.out.println(Thread.currentThread().getName() +": 进入IOrgServiceCachePxy。。。3");
            // System.out.println("方法参数不符");
            result = method.invoke(target, args);
//        }
        return result;
    }

    // 获得用户对象
    private Object findUserByDeptId(Method method, Object[] args)
            throws IllegalAccessException, InvocationTargetException {
        Object result;
        // 只有符合参数要求的方法才会进行缓存处理（只对获取当前有效部门列表）
//        if (args[1].equals("y")) {
//            // System.out.println(Thread.currentThread().getName() +": 进入IOrgServiceCachePxy。。。2");
//            //System.out.println("方法可进行缓存");
//            String key = args[0] + "_y";
//            if (OrgCachePools.deptUserPools.containsKey(key)) {
//                // System.out.println("从缓存中获取"+key);
//                return OrgCachePools.deptUserPools.get(key);
//            }
//            result = method.invoke(target, args);
//            ArrayOfUser userList = (ArrayOfUser) result;
//            OrgCachePools.deptUserPools.put(key, userList);
//        } else {
            //System.out.println(Thread.currentThread().getName() +": 进入IOrgServiceCachePxy。。。3");
            // System.out.println("方法参数不符");
            result = method.invoke(target, args);
//        }
        return result;
    }

    // 原来的代码
//    IOrgServiceClient client = new IOrgServiceClient();
//    IOrgServicePortType service = client.getIOrgServiceHttpPort();
//
//    ArrayOfUser userArr = service.findUserByDeptId(bgsVerid, "y");


    // 缓存代码
//    IOrgServiceClient client = new IOrgServiceClient();
//    IOrgServiceCachePxy proxy = new IOrgServiceCachePxy();
//    cacheService = (IOrgServicePortType)proxy.getProxyInstance(client.getIOrgServiceHttpPort());
//
//    ArrayOfUser userArr = service.findUserByDeptId(bgsVerid, "y");

}
