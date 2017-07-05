
import java.util.concurrent.ThreadLocalRandom;


/**
 * 
 * @author Dillon
 */
public class HitThread extends Thread {
    private Long iterations;
    
    
    public HitThread(Long l) {
        iterations = l;
    }
    
    @Override
    public void run() {
        double x = 0.0;
        double y = 0.0;
        double result = 0.0;
        for (int i = 0; i < iterations; i++) {
            x = ThreadLocalRandom.current().nextDouble(1.0);
            y = ThreadLocalRandom.current().nextDouble(1.0);
            result = (Math.pow(x, 2)) + (Math.pow(y, 2));
            if(result < 1.0) {
                Pi.incrementHit();
            }
            Pi.incrementAttempts();
        }
    }
}
