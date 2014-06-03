import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastián
 * Date: 02-06-14
 * Time: 09:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class KDPointGenerator {
    public static List<KDPoint> randomPoints(int c, int n) {
        List<KDPoint> points = new ArrayList<KDPoint>();
        for (int i=0; i<n; i++){
            points.add(randomQueryPoint(c, n));
        }
        return points;
    }

    public static List<KDPoint> lowDiscrepancyPoints(int c, int n) {
        return null;
    }

    public static KDPoint randomQueryPoint(int c, int n) {
        Random r = new Random();
        double end = c * Math.sqrt(n);
        double x = end * r.nextDouble();
        double y = end * r.nextDouble();
        return new KDPoint(x, y);
    }
}
