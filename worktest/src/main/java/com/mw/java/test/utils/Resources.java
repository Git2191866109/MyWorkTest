package com.mw.java.test.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wei.ma on 2016/11/17.
 */
public class Resources {
    ClassLoader defaultClassLoader;
    ClassLoader systemClassLoader;

    ClassLoader[] getClassLoaders(ClassLoader classLoader) {
        return new ClassLoader[]{
                classLoader,
                defaultClassLoader,
                Thread.currentThread().getContextClassLoader(),
                getClass().getClassLoader(),
                systemClassLoader};
    }

    /**
     * Returns a resource on the classpath as a Stream object
     * 返回classpath路径下的文件作为一个流
     *
     * @param resource    要获取的资源
     * @param classLoader 获取资源的classloader
     * @return
     * @throws IOException
     */
    public static InputStream getResourceAsStream(ClassLoader classLoader, String resource) throws IOException {
//        InputStream in = getResourceAsStream(resource, loader);
        return null;
    }

    /*
   * Try to get a resource from a group of classloaders
   *
   * @param resource    - the resource to get
   * @param classLoader - the classloaders to examine
   * @return the resource or null
   */
    InputStream getResourceAsStream(String resource, ClassLoader[] classLoader) {
        for (ClassLoader cl : classLoader) {
            if (null != cl) {

                // try to find the resource as passed
                InputStream returnValue = cl.getResourceAsStream(resource);

                // now, some class loaders want this leading "/", so we'll add it and try again if we didn't find the resource
                if (null == returnValue) {
                    returnValue = cl.getResourceAsStream("/" + resource);
                }

                if (null != returnValue) {
                    return returnValue;
                }
            }
        }
        return null;
    }
}
