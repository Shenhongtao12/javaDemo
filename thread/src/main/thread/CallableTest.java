package main.thread;

import java.util.concurrent.*;

/**
 * @author Aaron
 * @date 2020/11/29 12:09
 */
public class CallableTest implements Callable<Boolean> {
    private String name;

    public CallableTest(String name) {
        this.name = name;
    }

    @Override
    public Boolean call() {
        for (int i = 0; i < 8; i++) {
            System.out.println("------ " + name + i);
        }
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableTest callableTest = new CallableTest("张三");
        CallableTest callableTest1 = new CallableTest("李四");
        CallableTest callableTest2 = new CallableTest("王五");

        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //提交执行
        Future<Boolean> r1 = executorService.submit(callableTest);
        Future<Boolean> r2 = executorService.submit(callableTest1);
        Future<Boolean> r3 = executorService.submit(callableTest2);

        r1.get();
        r2.get();
        r3.get();

        //关闭服务
        executorService.shutdownNow();
    }
}
