package com.rao.playground.utils.locks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 *  AutoCloseableLock allows you to use a Lock with try with resources feature.
 */
public class AutoCloseableLock implements AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoCloseableLock.class);

    private final Lock lock;

    public AutoCloseableLock(String id, Lock lock, long timeoutMillis) throws InterruptedException {
        this.lock = lock;

        if (!lock.tryLock()) {
            LOGGER.debug("waiting on lock[{}]", id);
            boolean locked = lock.tryLock(timeoutMillis, TimeUnit.MILLISECONDS);

            if (!locked) {
                throw new RuntimeException("Could not get lock " + id + " within timeout " + timeoutMillis);
            }
        }

        LOGGER.debug("acquired lock[{}]", id);
    }

    public void close() throws Exception {
        this.lock.unlock();
    }
}

