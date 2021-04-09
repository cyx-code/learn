package cn.coding.nio.basic;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Classname NIOServer
 * @Date 2021/4/9 11:21 下午
 * @Author by chenyuxiang
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 创建selector
        Selector selector = Selector.open();
        // 绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);
        // 将serverSocketChannel注册到Selector上，事件为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            // 阻塞1秒，如果没有连接事件
            if (selector.select(1000) == 0) {
                System.out.println("wait 1 second");
                continue;
            }
            // 获取到相关的selectionKey集合，事件发生的key
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    // 处理连接事件
                    // 得到socketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 将socketChannel注册到selector上，事件为OP_READ，同时关联一个buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (selectionKey.isReadable()) {
                    // 处理OP_READ事件
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // 获取该channel管理的buffer
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    socketChannel.read(buffer);
                    System.out.println("client accept data is " + new String(buffer.array()));
                }
                // 将集合中的selectionKey删除
                iterator.remove();
            }

        }
    }
}
