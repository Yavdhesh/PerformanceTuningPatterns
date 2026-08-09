import java.util.ArrayList;
import java.util.function.UnaryOperator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class StringInterning {

    public static double bytesToMB(long bytes) {
        return (double) bytes / (1024 * 1024);
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Initial ="+bytesToMB(Runtime.getRuntime().freeMemory()));
        ArrayList<String> l= new ArrayList<>();
        for (int i = 0; i < 100000000; i++) {
            String a = ("Hello World"+i);
            a.intern();
            l.add(a);
        }
        System.out.println("After Loop ="+bytesToMB(Runtime.getRuntime().freeMemory()));
        Thread.sleep(4000);
        System.gc();

        Thread.sleep(4000);
        System.out.println("After GC ="+bytesToMB(Runtime.getRuntime().freeMemory()));


    }
}
