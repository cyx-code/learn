package cn.coding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * client端
 */
public class Client {
    private final ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
    private final ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);

    private Selector selector;
    private SocketChannel socketChannel;

    public Client() throws IOException {
        this.socketChannel = SocketChannel.open();
        this.socketChannel.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8080));
        this.socketChannel.configureBlocking(false);
        System.out.println("建立连接成功");
        this.selector = Selector.open();
        this.socketChannel.register(this.selector, SelectionKey.OP_WRITE);
    }
    public void start() throws Exception {
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isReadable()) {
                    System.out.println("isReadable");
                    receive(key);
                }
            }
        }
    }

    /**
     * 接受服务端发送的数据
     * @param key
     * @throws IOException
     */
    private void receive(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        socketChannel.read(receiveBuffer);
        receiveBuffer.flip();

        String receiveData = Charset.forName("UTF-8").decode(receiveBuffer).toString();
        System.out.println("receive server data:" + receiveData);
        receiveBuffer.clear();
    }


    public void sendInputMsg() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                synchronized (sendBuffer) {
                    sendBuffer.put((msg + "/r/n").getBytes());
                    sendBuffer.flip();
                    while (sendBuffer.hasRemaining()) {
                        socketChannel.write(sendBuffer);
                    }
                    sendBuffer.compact();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
