package com.apigee.threadpool;

/**
 * Created by ankur.mishra on 07/06/15.
 */
public class Job2 implements Runnable {
    @Override
    public void run() {
        System.out.println("\n executing task 2");
    }
}
