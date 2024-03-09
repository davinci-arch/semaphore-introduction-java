package org.example;

import java.util.UUID;

public class Producer extends Thread {

    private final ProtectedStorage<String> storage;
    private final int id;

    public Producer(int id, ProtectedStorage<String> storage) {
        this.storage = storage;
        this.id = id;
        start();
    }

    @Override
    public void run() {
        try {
            while (ProtectedGoods.producedGoods.getAndDecrement() > 0) {
                String item = UUID.randomUUID().toString().substring(0, 6);
                storage.addItem(item);
                System.out.printf("Producer {%o} put the item {%s}%n", id, item);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
