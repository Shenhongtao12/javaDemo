package main.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Aaron
 * @date 2020/12/7 18:46
 */
public class LockTest {

    public static void main(String[] args) {
        Car car = new Car();

        new Thread(() -> { for (int i = 0; i < 10; i++) car.buyCar(); }, "A").start();
        new Thread(() -> { for (int i = 0; i < 10; i++) car.buyCar(); }, "B").start();
        new Thread(() -> { for (int i = 0; i < 10; i++) car.buyCar(); }, "C").start();


    }

}

class Car {
    private int num = 30;
    Lock lock = new ReentrantLock();
    public void buyCar() {
        lock.lock();

        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "现在剩余 " + num + ", 卖出了" + num--);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
