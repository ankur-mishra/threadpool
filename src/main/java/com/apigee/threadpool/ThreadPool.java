package com.apigee.threadpool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by ankur.mishra on 06/06/15.
 */
public class ThreadPool implements ThreadPoolWithJobAffinity {

    Map<String, BlockingQueue> taskMap = null;
    List<PoolThread> threadList = new ArrayList<PoolThread>();

    public ThreadPool(int noOfThreads) {
        taskMap = new HashMap<String, BlockingQueue>();

        for(int i=0; i<noOfThreads; i++) {
            threadList.add(new PoolThread(taskMap));
        }

        for(PoolThread thread: threadList){
            thread.start();
        }
    }

    @Override
    public int poolSize() {
        synchronized (taskMap) {
            return taskMap.size();
        }
    }

    @Override
    public void submit(String jobId, Runnable job) throws InterruptedException {
        synchronized (taskMap) {
            if (taskMap.containsKey(jobId))
                taskMap.get(jobId).put(job);
            else {
                BlockingQueue q = new ArrayBlockingQueue(5);
                q.put(job);
                taskMap.put(jobId, q);
            }
        }
    }

    @Override
    public void shutdown() {
        for(Map.Entry<String, BlockingQueue> entry: taskMap.entrySet()) {
            BlockingQueue queue = entry.getValue();
            while(queue.poll() != null) {}
        }
        for(PoolThread thread: threadList){
            thread.stop();
        }

    }
}
