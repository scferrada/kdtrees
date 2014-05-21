/**
 * Created with IntelliJ IDEA.
 * User: Claud
 * Date: 20-05-14
 * Time: 02:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class KDLeaf extends KDNode {
    private KDPoint point;



    public KDLeaf(KDPoint point) {
        this.point = point;
    }

    @Override
    public KDNode searchNeighbor(KDPoint q) {

        return this;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double distance(KDPoint q) {
        return point.distance(q);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public KDNode anotherSearch(KDNode currentBest, double currentDistance, KDPoint q){
        if( point.distance(q)<currentDistance){
            return this;
        }
        return currentBest;
    }

    @Override
    public boolean intersects(KDPoint q, double currentDistance) {
        return point.distance(q)<=currentDistance;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public KDPoint getPoint() {
        return point;
    }
}
