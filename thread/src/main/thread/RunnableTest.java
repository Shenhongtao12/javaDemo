package main.thread;

/**
 * @author Aaron
 * @date 2020/11/29 12:01
 */
public class RunnableTest {
    public static void main(String[] args) {
        B a = new B("张三");
        B a1 = new B("张三a");
        B a2 = new B("张三b");
        new Thread(a).start();
        new Thread(a1).start();
        new Thread(a2).start();
    }

    static class B implements Runnable {

        private String name;

        public B(String name) {
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
