package locks;

import com.rao.playground.utils.locks.AutoCloseableLock;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * AutoCloseableLockTest
 */
public class AutoCloseableLockTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @Test
    public void test_lock_is_locked_on_construction() throws Exception {
        ReentrantLock reentrantLock = new ReentrantLock();

        AutoCloseableLock autoCloseableLock = new AutoCloseableLock("test", reentrantLock, 500);

        assertTrue(reentrantLock.isLocked());

    }

    @Test
    public void test_lock_is_released() throws Exception {
        ReentrantLock reentrantLock = new ReentrantLock();

        AutoCloseableLock autoCloseableLock = new AutoCloseableLock("test", reentrantLock, 500);

        assertTrue(reentrantLock.isLocked());

        autoCloseableLock.close();

        assertFalse(reentrantLock.isLocked());

    }

    @Test (expected = RuntimeException.class)
    public void test_throws_runtime_exception() throws Exception {
        DodgyLock dodgyLock = new DodgyLock();

        try (AutoCloseableLock autoCloseableLock = new AutoCloseableLock("test", dodgyLock, 5)) {
            assertTrue(dodgyLock.isLocked());
        }
    }

    private class DodgyLock extends ReentrantLock {
        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
            return false;
        }
    }
}