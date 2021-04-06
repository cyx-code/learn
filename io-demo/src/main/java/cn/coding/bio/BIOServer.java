package cn.coding.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Classname BIOServer
 * @Date 2021/4/6 11:15 下午
 * @Author by chenyuxiang
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 创建一个socket
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("start server");
        while (true) {
            // 连接客户端,accept会阻塞
            System.out.println("等待连接");
            final Socket socket = serverSocket.accept();
            // 创建线程去处理客户端连接
            executorService.execute(() -> {
                handler(socket);
            });
            System.out.println("connect on client");

        }

    }

    /**
     * @Description: 处理与客户端的连接
     * @param socket
     * @return void
     * @author chenyuxiang
     * @date 2021/4/6 11:37 下午
     */
    public static void handler(Socket socket) {
        try {
            byte[] bytes = new byte[1024];
            // 得到输入流
            InputStream inputStream = socket.getInputStream();
            while (true) {
                // 将数据读到bytes数组，read会阻塞
                System.out.println("wait read data...");
                int read = inputStream.read(bytes);
                if (-1 != read) {
                    // 输出客户端传的信息
                    System.out.println("receive data is " + new String(bytes, 0, read));
                    System.out.println("thread name: " + Thread.currentThread().getName());

                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("close client");
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
