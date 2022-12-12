package com.qc.runtime;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;



public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException, ExecutionException
    {

        Callable<Process> task = () -> {
            // 执行异步任务
            Runtime runtime = Runtime.getRuntime();
            //根据具体位置来更换
            Process process = runtime.exec("/Users/zack/Desktop/qc-runtime-callable/src/main/java/com/qc/runtime/shell.sh");
            return process;
        };
    
         // 将Callable包装成FutureTask
         FutureTask<Process> future = new FutureTask<>(task);

         // 启动新线程来执行异步任务
         new Thread(future).start();
 
         // 获取异步任务的结果
         Process result = future.get();
         System.out.println(result);



         
    }
}
