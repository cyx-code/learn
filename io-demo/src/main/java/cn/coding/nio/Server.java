package cn.coding.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * server端
 */
public class Server {
    // 分配缓冲区
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

    // 声明选择器
    private Selector selector;

    public Server() throws Exception {
        // 打开一个server的通道（channel）
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 负责接受客户端连接请求，并生成与客户端连接的socket
        ServerSocket serverSocket = serverSocketChannel.socket();
        // 设置监听端口
        serverSocket.bind(new InetSocketAddress(8080));
        System.out.println("正在监听8080端口");
        // 打开selector
        this.selector = Selector.open();
        // 在selector注册感兴趣的事情
        serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
    }
    public void start() throws Exception {
        while (true) {
            // 调用阻塞的seletor，等待selector上注册的事件发生
            this.selector.select();
            // 获取就绪事件
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 先移除该事件，避免重复通知
                iterator.remove();
                // 新连接
                if (selectionKey.isAcceptable()) {
                    System.out.println("isAcceptable");
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();

                    // 新注册channel
                    SocketChannel socketChannel = server.accept();
                    if (socketChannel == null) {
                        continue;
                    }
                    // 设置非阻塞模式
                    socketChannel.configureBlocking(false);
                    // 注册只读事件（服务端一般不注册写事件）
                    socketChannel.register(this.selector, SelectionKey.OP_READ);

                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    buffer.put("hi new channel".getBytes());
                    buffer.flip();
                    int writeBytes = socketChannel.write(buffer);
                }

                // 服务端关心的可读，意味着有数据从client传过来了
                if (selectionKey.isReadable()) {
                    System.out.println("isReadable");
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    readBuffer.clear();
                    socketChannel.read(readBuffer);
                    readBuffer.flip();
                    // 解码
                    String receiveData= Charset.forName("UTF-8").decode(readBuffer).toString();
                    System.out.println("receiveData:" + receiveData);

                    // 这里将收到的数据发给客户端
                    writeBuffer.clear();
                    writeBuffer.put(receiveData.getBytes());
                    writeBuffer.flip();
                    while (writeBuffer.hasRemaining()) {
                        // 防止写缓冲区满，需要检测是否完全写入
                        System.out.println("写入数据:"+socketChannel.write(writeBuffer));
                    }
                }
            }
        }
    }

}
