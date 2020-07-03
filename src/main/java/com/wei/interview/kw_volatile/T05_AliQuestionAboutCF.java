package com.wei.interview.kw_volatile;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/7/3
 */
public class T05_AliQuestionAboutCF {
    /*
        阿里面试题:
        多线程发起多个任务，如果所有校验的结果均为true则返回true，只要有一个返回false则返回false，尽量快
     */

    public static void main(String[] args) throws Exception{
        MyTask task1 = new MyTask("task1",3,true);
        MyTask task2 = new MyTask("task2",4,true);
        MyTask task3 = new MyTask("task3",1,false);

        /*CompletableFuture f1 = CompletableFuture.supplyAsync(() -> task1.call())
                .thenAccept((result) -> callback(result));
        CompletableFuture f2 = CompletableFuture.supplyAsync(() -> task2.call())
                .thenAccept((result) -> callback(result));
        CompletableFuture f3 = CompletableFuture.supplyAsync(() -> task3.call())
                .thenAccept((result) -> callback(result));*/

        System.in.read();
    }

    private static void callback(Boolean result){
        if (false == result){
            //处理结束流程
            //通知其他线程结束(回滚)
            //超时处理
            System.exit(0);
        }
    }

    private static class MyTask{
        private String name;
        private int timeInSeconds;
        private boolean ret;

        public MyTask(String name, int timeInSeconds, boolean ret) {
            this.name = name;
            this.timeInSeconds = timeInSeconds * 1000;
            this.ret = ret;
        }
    }


}
