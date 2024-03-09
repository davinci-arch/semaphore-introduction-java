package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int storageSize = 3;
        System.out.print("Write amount of consumer: ");
        int amountOfConsumers = scanner.nextInt();
        System.out.print("Write amount of producer: ");
        int amountOfProducers = scanner.nextInt();
        ProtectedStorage<String> storage = new ProtectedStorage<>(storageSize);

        for (int i = 0; i < amountOfConsumers; i++) {
            new Consumer(i, storage);
        }
        for (int i = 0; i < amountOfProducers; i++) {
            new Producer(i, storage);
        }

    }
}