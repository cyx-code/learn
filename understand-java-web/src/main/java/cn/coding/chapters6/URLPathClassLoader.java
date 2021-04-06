package cn.coding.chapters6;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

public class URLPathClassLoader extends URLClassLoader {
    private String packageName = "cn.coding.cyx";
    public URLPathClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> aClass = findLoadedClass(name);
        if (aClass != null) {
            return aClass;
        }
        if (!packageName.startsWith(name)) {
            return super.loadClass(name);
        } else {
            return findClass(name);
        }
    }
}
