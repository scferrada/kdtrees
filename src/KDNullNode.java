/**
 * Created with IntelliJ IDEA.
 * User: Claud
 * Date: 02-06-14
 * Time: 03:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class KDNullNode extends KDLeaf {
    public KDNullNode(KDPoint point) {
        super(point);
    }

    @Override
    public double distance(KDPoint q) {
        return Double.POSITIVE_INFINITY;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
