package com.apigee.threadpool;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * Created by ankur.mishra on 06/06/15.
 */
public class PoolThread extends Thread {
    private Map<String, BlockingQueue> taskMap = null;

    public PoolThread(Map<String, BlockingQueue> taskMap) {
        this.taskMap = taskMap;
    }

    public void run() {
        while(true) {
            try {
                Map.Entry<String, BlockingQueue> entry = null;
                BlockingQueue q = null;
                synchronized (taskMap) {
                if(!taskMap.isEmpty()) {
                    entry = taskMap.entrySet().iterator().next();
                    q = entry.getValue();
                    taskMap.remove(entry.getKey());
                }
                    if(q != null) {
                        while (!q.isEmpty()) {
                            Runnable runnable = (Runnable) q.take();
                            System.out.println("\n executing task " + entry.getKey() + " by thread " + Thread.currentThread().getName());
                            runnable.run();
                        }
                    }
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
