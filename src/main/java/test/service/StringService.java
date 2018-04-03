package test.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.objects.Str;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class StringService {
    @Autowired
    private Str str;

    private int count = 10;

    private final Lock lock = new ReentrantLock();
    private final Condition countCondition = lock.newCondition();

    public Str getStr() {
        lock.lock();
        try {
            return str;
        } finally {
            lock.unlock();
        }
    }

    public void depositThread1(int i) {
        lock.lock();
        try {
            while ((count / 10) % 2 == 0) {
                try {
                    countCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            str.addInt(i);
            count++;
            countCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void depositThread2(int i) {
        lock.lock();
        try {
            while ((count / 10) % 2 == 1) {
                try {
                    countCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            str.addInt(i);
            count++;
            countCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void cleanerStr() {
        str.setS("");
    }
}
