package com.example.worker;

import java.util.Random;

public class CreditService {
    private final Random random = new Random();

    public int getCreditScore() {
        return 0 + random.nextInt(100); // Range: 0-100
    }

    public boolean getCustomerExists() {
        return random.nextBoolean();
    }
}
