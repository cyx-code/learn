package cn.coding.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Classname BIOClient
 * @Date 2021/4/6 11:43 下午
 * @Author by chenyuxiang
 */
public class BIOClient {

    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 8888;
        try {
            Socket socket = new Socket(ip, port);
            System.out.println("please input text...");
            new ReadMsg(socket).start();
            while (true) {
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                writer.println(new Scanner(System.in).nextLine());
                writer.flush();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static class ReadMsg extends Thread {
        private Socket socket;

        ReadMsg(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            try {

                BufferedReader reader1 = new BufferedReader( new InputStreamReader(socket.getInputStream()));
                String line = null;
                while ((line = reader1.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                clear();
            }

        }
        //必要的资源清理工作
        private void clear() {
            if (socket != null)
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
