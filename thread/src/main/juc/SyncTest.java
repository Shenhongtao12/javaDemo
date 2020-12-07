package main.juc;

/**
 * @author Aaron
 * @date 2020/12/7 18:46
 */
public class SyncTest {

    public static void main(String[] args) {
        Car1 car = new Car1();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                car.buyCar();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                car.buyCar();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                car.buyCar();
            }
        }, "C").start();


    }

}

class Car1 {
    private int num = 30;
    public synchronized void buyCar() {
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + "现在剩余 " + num + ", 卖出了" + num--);
        }
    }
}
