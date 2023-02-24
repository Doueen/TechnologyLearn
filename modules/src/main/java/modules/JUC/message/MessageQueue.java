package modules.JUC.message;

import java.util.LinkedList;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-24 14:33
 */


public class MessageQueue {
    private LinkedList<Message> list = new LinkedList<>();

    private int capcity;

    public MessageQueue(int capcity) {
        this.capcity = capcity;
    }

    public Message take() {
        //检查队列是否为空
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    System.out.println("队列为空，生产者线程等待！");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message message = list.removeFirst();
            System.out.println("消费消息：" + message);
            //通知生产者线程
            list.notifyAll();
            return message;

        }

    }

    public void put(Message message) {
        synchronized (list) {
            while (list.size() == capcity) {
                try {
                    System.out.println("队列已满，生产者线程等待！");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.addLast(message);
            System.out.println("已经生产消息！" + message);
            //唤醒take的线程
            list.notifyAll();
        }
    }
}

class Main {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(2);
        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                queue.put(new Message(id, "值：" + id));
            }, "生产者" + i).start();
        }

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = queue.take();
            }

        }, "消费者").start();
    }
}

final class Message {
    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}