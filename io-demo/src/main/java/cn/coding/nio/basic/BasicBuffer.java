package cn.coding.nio.basic;

import java.nio.IntBuffer;

/**
 * @Classname BasicBuffer
 * @Description 基本buffer结构
 * @Date 2021/4/7 10:22 下午
 * @Author by chenyuxiang
 */
public class BasicBuffer {
    public static void main(String[] args) {
        // 指定缓冲区大小，如intBuffer可放入5个int数组
        // 超过这个大小将会有 BufferOverflowException
        IntBuffer intBuffer = IntBuffer.allocate(5);

        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }
        // 切换intBuffer的读写状态
        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
