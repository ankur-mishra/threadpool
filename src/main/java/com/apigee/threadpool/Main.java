package com.apigee.threadpool;

import java.util.Random;

/**
 * Created by ankur.mishra on 07/06/15.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Job1 job1 = new Job1();
        Job2 job2 = new Job2();
        Job3 job3 = new Job3();
        Job4 job4 = new Job4();

        int count = 0;
        ThreadPool threadPool = new ThreadPool(3);
        while(count < 5) {
            Random r = new Random();
            int x = r.nextInt(4);
            if(x == 0) {
                threadPool.submit("job1", job1);
                //System.out.println("\n Thread Pool size is "+ threadPool.poolSize());
                threadPool.submit("job1", job1);
                //System.out.println("\n Thread Pool size is "+ threadPool.poolSize());
                threadPool.submit("job1", job1);
                //System.out.println("\n Thread Pool size is "+ threadPool.poolSize());
            }
            else if(x == 1) {
                threadPool.submit("job2", job2);
                //System.out.println("\n Thread Pool size is "+ threadPool.poolSize());
                threadPool.submit("job2", job2);
                //System.out.println("\n Thread Pool size is "+ threadPool.poolSize());
                threadPool.submit("job2", job2);
                //System.out.println("\n Thread Pool size is "+ threadPool.poolSize());
            }
            else if(x == 2) {
                threadPool.submit("job3", job3);
                //System.out.println("\n Thread Pool size is "+ threadPool.poolSize());
                threadPool.submit("job3", job3);
                //System.out.println("\n Thread Pool size is "+ threadPool.poolSize());
                threadPool.submit("job3", job3);
                //System.out.println("\n Thread Pool size is "+ threadPool.poolSize());
            }
            else if(x == 3) {
                threadPool.submit("job4", job4);
                //System.out.println("\n Thread Pool size is "+ threadPool.poolSize());
                threadPool.submit("job4", job4);
                //System.out.println("\n Thread Pool size is "+ threadPool.poolSize());
                threadPool.submit("job4", job4);
                //System.out.println("\n Thread Pool size is "+ threadPool.poolSize());

            }
            count++;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        threadPool.shutdown();
    }
}

