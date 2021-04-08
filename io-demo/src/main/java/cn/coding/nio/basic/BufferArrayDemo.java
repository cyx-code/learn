package cn.coding.nio.basic;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @Classname BufferArrayDemo
 * @Date 2021/4/8 10:48 下午
 * @Author by chenyuxiang
 */
public class BufferArrayDemo {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8666);
        //
        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        int msgLength = 8;
        SocketChannel socketChannel = serverSocketChannel.accept();
        while (true) {
            int byteRead = 0;
            while (byteRead < msgLength) {
                long r = socketChannel.read(byteBuffers);
                byteRead += r;
                System.out.println("byteRead=" + byteRead);
                Arrays.asList(byteBuffers).stream().map(byteBuffer -> {
                    return "position=" + byteBuffer.position() + ",limit=" + byteBuffer.limit();
                }).forEach(System.out::println);
            }
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());
            int byteWrite = 0;
            while (byteWrite < msgLength) {
                long l = socketChannel.write(byteBuffers);
                byteWrite += l;

            }
            Arrays.asList(byteBuffers).stream().forEach(byteBuffer -> byteBuffer.clear());
            System.out.println("byteRead=" + byteRead + ",byteWrite=" + byteWrite + ",msgLength=" + msgLength);
        }


    }
}
