package cn.coding.chapters6;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class NetClassLoader extends ClassLoader {
    private String classPath;
    private String packageName = "cn.coding.cyx";

    public NetClassLoader(String classPath) {
        this.classPath = classPath;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> aClass = findLoadedClass(name);
        if (aClass != null) {
            return aClass;
        }
        if (packageName.startsWith(name)) {
            byte[] classData = getData(name);
        }
        return null;
    }

    private byte[] getData(String className) {
        String path = classPath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try {
            URL url = new URL(path);
            InputStream is = url.openStream();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while ((num = is.read(buffer)) != -1) {
                stream.write(buffer, 0, num);
            }
            return stream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private byte[] deCode(byte[] src) {
        byte[] decode = null;
        // 解码代码
        return decode;
    }
}
