package cn.coding.nio.basic;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Classname NIOFileChannelDemo
 * @Date 2021/4/7 11:23 下午
 * @Author by chenyuxiang
 */
public class NIOFileChannelDemo {

    public static void main(String[] args) throws IOException {

//        writeFile();
//        readFile();
        copyFile(1);
    }

    /**
     * @Description: channel 写文件示例
     * @return void
     * @author chenyuxiang
     * @date 2021/4/7 11:39 下午
     */
    public static void writeFile() {
        String str = "hello world";

        try (FileOutputStream outputStream = new FileOutputStream("/Users/chenyuxiang/Desktop/data/test.txt");
             // 输出流的channel
             FileChannel channel = outputStream.getChannel()) {
            // 分配1024大小的字节缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            // 将hello world 添加到字节缓冲区
            byteBuffer.put(str.getBytes());
            // 切换byteBuffer的读写状态
            byteBuffer.flip();
            // 将缓冲区的数据写入到文件中
            channel.write(byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: channel 读文件示例
     * @return void
     * @author chenyuxiang
     * @date 2021/4/7 11:39 下午
     */
    public static void readFile() {
        File file = new File("/Users/chenyuxiang/Desktop/data/test.txt");
        try (FileInputStream inputStream = new FileInputStream(file);
            FileChannel channel = inputStream.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
            channel.read(byteBuffer);
            System.out.println(new String(byteBuffer.array()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void copyFile() throws IOException {
        File file = new File("/Users/chenyuxiang/Desktop/data/test.txt");
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel inputChannel = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream("/Users/chenyuxiang/Desktop/data/testCopy.txt");
        FileChannel outputChannel = outputStream.getChannel();

        while (true) {
            // 必须要清空，否则会死循环
            byteBuffer.clear();
            int read = inputChannel.read(byteBuffer);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            outputChannel.write(byteBuffer);
        }

        inputStream.close();
        outputStream.close();

    }

    public static void copyFile(int i) throws IOException {
        File file = new File("/Users/chenyuxiang/Desktop/data/test.txt");
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel sourceChannel = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream("/Users/chenyuxiang/Desktop/data/testCopy-t.txt");
        FileChannel destChannel = outputStream.getChannel();


        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        inputStream.close();
        outputStream.close();

    }
}
