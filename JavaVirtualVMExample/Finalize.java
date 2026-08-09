import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.function.Consumer;
import java.util.random.RandomGenerator;

public class Finalize {

    static final List<Customer> customers = new ArrayList<>();

    static Runnable consumer =
        ()-> {
            RandomGenerator rd = new Random();
            int val = rd.nextInt(10, 100000);
            for (int i = 0; i < val; i++) {
                try {
                    Customer customer = new Customer("John Doe" + i, 30);
                    //customer = null; // Make the object eligible for garbage collection
                    customers.add(customer);
                   // System.out.println("called");
                } catch (Exception e) {
                    throw new RuntimeException(e);

                }
            }
           // customers.clear();
        };


    static class Customer implements AutoCloseable{
        String name;
        int age;

        public Customer(String name, int age) {
            this.name = name;
            this.age = age;
        }


        @SuppressWarnings("removal")
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("Finalize method called for Customer: " + name);
        }


        public static void main(String[] args) throws Exception {

            try{
                ScheduledExecutorService sc = Executors.newScheduledThreadPool(10);

            sc.scheduleAtFixedRate(consumer,0, 1000, java.util.concurrent.TimeUnit.MILLISECONDS);

                Thread.sleep(1000000);
}catch (Exception e) {
                throw new RuntimeException(e);
            }




            // Suggest the JVM to run the garbage collector
            System.gc();

            // Wait for a moment to allow the finalize method to be called
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }

        @Override
        public void close() throws Exception {
            System.out.println("Closing resources for Customer: " + name);
        }
    }
}
