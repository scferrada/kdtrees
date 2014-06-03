import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastián
 * Date: 02-06-14
 * Time: 09:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class KDTreeFactory {
    public static KDTree makeMeanTreeWith(List<KDPoint> points) {
        return new KDMeanTree(new KDMeanTree().constructKdtree(points, Axis.X));
    }

    public static KDTree makeMedianTreeWith(List<KDPoint> points) {
        return new KDMedianTree(new KDMedianTree().constructKdtree(points, Axis.X));
    }
}
