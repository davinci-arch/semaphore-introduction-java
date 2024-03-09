package org.example;

import java.util.Collections;

public class Consumer extends Thread {

    private final ProtectedStorage<String> storage;
    private final int id;

    public Consumer(int id, ProtectedStorage<String> storage) {
        this.storage = storage;
        this.id = id;
        start();
    }

    @Override
    public void run() {
        try {
            while (ProtectedGoods.consumedGoods.getAndDecrement() > 0) {
                String item = storage.getItem();
                System.out.printf("Consumer {%o} take an item {%s}%n", id, item);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
