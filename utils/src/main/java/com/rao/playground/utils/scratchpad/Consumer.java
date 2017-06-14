package com.rao.playground.utils.scratchpad;

import java.util.Queue;

/**
 * Created by shuaibrao on 05/04/2017.
 */
public class Consumer extends Thread {

    private Queue<Integer> queue;
    private int maxSize;
    private volatile boolean stop;

    public Consumer(String name, Queue<Integer> queue, int maxSize) {
        super(name);
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (!stop) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                queue.remove();
                queue.notifyAll();
            }

        }
    }
}
