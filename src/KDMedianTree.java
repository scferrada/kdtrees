import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Claud
 * Date: 20-05-14
 * Time: 02:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class KDMedianTree extends KDTree {

    protected KDMedianTree(KDNode root) {
        super(root);
    }

    @Override
    protected KDLine getLine(List<KDPoint> points, Axis axis) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
