package cn.coding.nio.basic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBUffer
 * @Classname MapperByteBuffer
 * @Date 2021/4/8 10:25 下午
 * @Author by chenyuxiang
 */
public class MappedByteBufferDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile rw = new RandomAccessFile("/Users/chenyuxiang/Desktop/data/test.txt", "rw");
        FileChannel channel = rw.getChannel();

        /**
         * FileChannel.MapMode.READ_WRITE 文件的读写模式
         * position 可以直接修改的位置
         * size 直接修改的范围，字节数position～size - 1
         * 使用的实现类是DirectByteBuffer
         */
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 1, 5);// IndexOutOfBoundsException
        buffer.put(4, (byte) 'w');// 对于文件，此时的index是position + index
        rw.close();
        System.out.println("success");

    }
}
