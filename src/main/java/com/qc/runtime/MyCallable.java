package com.qc.runtime;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//callable是一个典型的回调接口，看它和谁搭配使用了
public class MyCallable implements Callable<String> {
    private String parameter;

    public MyCallable(String parameter) {
        this.parameter = parameter;
    }
    @Override
    public String call() {
        // 在这里使用 parameter 变量
        return "Hello, " + parameter;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        /**runnable的基础使用*/
        //和thread配合
        Runnable run = ()->{
            System.out.println("hello");
        };
        Thread test =   new Thread(run);
        test.run();
    
        /**Callable*/
        //和执行器以及FeatureTask配合

        /** a、callable的基础使用 */
        String world = "world";
        Callable<String> call = ()->{
            return world;
        };
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> ft = executor.submit(call);
        String result1 = ft.get();
        System.out.println(result1);

        /* b、callable作为FeatureTask的参数获取Feature的一异步方案
         * 见App.java
        */

        /* c、另外一种提供参数的思路, 构造器模式*/
        MyCallable myCallable = new MyCallable("world");
        String result2 = myCallable.call();
        System.out.println(result2);

        /**
         * so:
         * callable是为了适配在不同环境下(线程和一般使用)的回调
         * 而给的接口工具
         */



    }


}
