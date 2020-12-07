package main.thread;

/**
 * @author Aaron
 * @date 2020/11/29 11:46
 */
public class ThreadTest {

    public static void main(String[] args) {
        A a = new A("张三");
        A a1 = new A("张三a");
        A a2 = new A("张三b");
        A a3 = new A("张三c");
        a.start();
        a1.start();
        a2.start();
        a3.start();
    }

    static class A extends Thread {
        private String name;

        public A(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("执行了" + name + i);
            }
        }
    }
}
