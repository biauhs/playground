package com.rao.playground.utils.scratchpad;

import java.util.Queue;
import java.util.Random;

/**
 * Created by shuaibrao on 05/04/2017.
 */
public class Producer extends Thread {

    private Queue<Integer> queue;
    private int maxSize;
    private volatile boolean stop;

    public Producer(Queue<Integer> queue, int maxSize, String name) {
        super(name);
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (!stop) {
            synchronized (queue) {
                while (queue.size() == maxSize) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Random random = new Random();
                int i = random.nextInt();
                queue.add(i);
                queue.notifyAll();
            }
        }
    }



}
