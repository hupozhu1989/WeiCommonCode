package com.wei.interview.lock;

/**
 * @author weizhenchao
 * @create 2020-04-02-上午 10:00
 */
public class SynchronizedDemo {
    public static void main(String[] args) {
        //博客地址 https://www.cnblogs.com/aspirant/p/11470858.html
    }

    /*  同步代码块
        monitorenter：每个对象都是一个监视器锁（monitor）。当monitor被占用时就会处于锁定状态，线程执行monitorenter
        指令时尝试获取monitor的所有权，过程如下：
            1.如果monitor的进入数为0，则该线程进入monitor，然后将进入数设置为1，该线程即为monitor的所有者；
            2.如果线程已经占有该monitor，只是重新进入，则进入monitor的进入数加1；
            3.如果其他线程已经占用了monitor，则该线程进入阻塞状态，直到monitor的进入数为0，再重新尝试获取monitor的所有权；
        monitorexit：执行monitorexit的线程必须是objectref所对应的monitor的所有者。指令执行时，monitor的进入数减1，
        如果减1后进入数为0，那线程退出monitor，不再是这个monitor的所有者。其他被这个monitor阻塞的线程可以尝试去获取这个 monitor 的所有权。
            monitorexit指令出现了两次，第1次为同步正常退出释放锁；第2次为发生异步退出释放锁；

        Synchronized的实现原理，Synchronized的语义底层是通过一个monitor的对象来完成，其实wait/notify等方法也依赖于monitor对象，
        这就是为什么只有在同步的块或者方法中才能调用wait/notify等方法，否则会抛出java.lang.IllegalMonitorStateException的异常的原因。
     */
    /*public void method01() {
        synchronized (this) {
            System.out.println("synchronized 代码块");
        }
    }*/

    /*  同步方法
        从编译的结果来看，方法的同步并没有通过指令 monitorenter 和 monitorexit 来完成（理论上其实也可以通过这两条指令来实现），
        不过相对于普通方法，其常量池中多了 ACC_SYNCHRONIZED 标示符。JVM就是根据该标示符来实现方法的同步的：
            当方法调用时，调用指令将会检查方法的 ACC_SYNCHRONIZED 访问标志是否被设置，如果设置了，执行线程将先获取monitor，
            获取成功之后才能执行方法体，方法执行完后再释放monitor。在方法执行期间，其他任何线程都无法再获得同一个monitor对象。
        两种同步方式本质上没有区别，只是方法的同步是一种隐式的方式来实现，无需通过字节码来完成。两个指令的执行是JVM通过调用操作系统
        的互斥原语mutex来实现，被阻塞的线程会被挂起、等待重新调度，会导致“用户态和内核态”两个态之间来回切换，对性能有较大影响。
     */
    public synchronized void method02() {
        System.out.println("synchronized ⽅法");
    }
}
