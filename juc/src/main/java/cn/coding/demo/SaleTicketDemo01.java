package cn.coding.demo;

public class SaleTicketDemo01 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 1; i < 60; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i < 60; i++) {
                ticket.sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i < 60; i++) {
                ticket.sale();
            }
        }, "C").start();

    }
}

class Ticket {
    private int number = 50;

    public synchronized void sale() {
        if (number > 0)
            System.out.println("卖出了" + (50 - --number) + "张票，还剩" + number + "张");
    }
}