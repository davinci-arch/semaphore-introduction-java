package org.example;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class ProtectedStorage<T> {

    private final int size;
    private LinkedList<T> storage;
    private Semaphore fullStorage;
    private Semaphore emptyStorage = new Semaphore(0);
    private Semaphore permissionToStorage = new Semaphore(1);

    public ProtectedStorage(int size) {
        this.size = size;
        storage = new LinkedList<>();
        fullStorage = new Semaphore(size);
    }

    public void addItem(T item) throws InterruptedException {
        fullStorage.acquire();
        permissionToStorage.acquire();
        storage.push(item);
        permissionToStorage.release();
        emptyStorage.release();
    }

    public T getItem() throws InterruptedException {
        emptyStorage.acquire();
        permissionToStorage.acquire();
        T item = storage.poll();
        permissionToStorage.release();
        fullStorage.release();
        return item;
    }

}
