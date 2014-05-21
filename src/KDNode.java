/**
 * Created with IntelliJ IDEA.
 * User: Claud
 * Date: 20-05-14
 * Time: 02:01 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class KDNode {


    protected KDInternalNode parent;

    public KDNode getParent(){
        return parent;
    }

    public abstract KDNode searchNeighbor(KDPoint q);


    public abstract double distance(KDPoint q);

    public abstract KDNode anotherSearch(KDNode currentBest, double currentDistance, KDPoint q);

    public abstract boolean intersects(KDPoint q, double currentDistance);
}
