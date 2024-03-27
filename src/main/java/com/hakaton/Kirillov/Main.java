package com.hakaton.Kirillov;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        SpringBootApp.startBot();
        //ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        //executor.scheduleAtFixedRate(Comparison::comparisonCost, 0, 15, TimeUnit.SECONDS);


    }
}
