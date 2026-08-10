package com.garbagecollection.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

public class GarbageCollectionDemo {
    // VM parameters- -XX:InitialHeapSize=5m -XX:MaxHeapSize=5m -XX:+PrintCodeCache -verbose:gc

// This class when running should be viewed in VisualVM to see frequent Full GC running and young GC running.

    public static void main(String [] args){
        //-XX:MaxHeapSize=6m will trigger Full Pause eventually in VisualVM
        RandomGenerator rd = new Random();
        List<Customer> customers = new ArrayList<>();

        while(true){

            Customer s = null;
            s = new Customer("Radhey"+rd.nextInt(10,10000), rd.nextInt(0,127));
            customers.add(s);
            if(customers.size()>10000){
                for (int i = 0; i < 1000; i++) {
                    customers.remove(0);
                }
            }




        }


    }
}
