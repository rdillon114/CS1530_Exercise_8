
import java.util.ArrayList;

/**
 *
 * @author Richard Dillon
 */
public class Pi {
    private static Long hits = 0L;
    private static Long attempts = 0L;
    
    public static void main(String[] args) {
        new Pi(args);
    }
    
    public Pi(String[] args) {
        try {
            Long numThreads = Long.parseLong(args[0]);
            Long numIterations = Long.parseLong(args[1]);
            
            Long perThread = numIterations / numThreads;
            ArrayList<HitThread> threads = new ArrayList<>();
            HitThread worker = new HitThread(perThread + (numIterations - (perThread * numThreads)));
            worker.start();
            for (int i = 0; i < numThreads - 1; i++) {
                threads.add(worker);
                worker = new HitThread(perThread);
                worker.start();
            }
            
            for (int i = 0; i < threads.size(); i++) {
                worker = threads.get(i);
                worker.join();
            }
            
            System.out.println("Total\t= " + attempts);
            System.out.println("Inside\t= " + hits);
            double ratio = (double)hits / (double)attempts;
            System.out.println("Ratio\t= " + ratio);
            ratio = ratio * 4.0;
            System.out.println("Pi\t= " + ratio);
        }
        catch(Exception ex) {
            System.out.println("Pi <long, # threads> <long, # iterations>");
        }
    }
    
    public static synchronized void incrementHit() {
        hits++;
    }
    
    public static synchronized void incrementAttempts() {
        attempts++;
    }
}
