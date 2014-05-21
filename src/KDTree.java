import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastián
 * Date: 20-05-14
 * Time: 01:40 PM
 * To change this template use File | Settings | File Templates.
 */

public abstract class KDTree {
    KDNode root;

    protected KDTree(KDNode root) {
        this.root = root;
    }

    public KDNode constructKdtree(List<KDPoint> points, Axis axis){
        if(points.size() == 1 ){
            return new KDLeaf(points.get(0));
        }

        KDLine line = getLine(points, axis) ;
        List<List<KDPoint>> partition = makePartition(points,line);

        return new KDInternalNode(line,
                                  constructKdtree(partition.get(0),axis.negated()),
                                  constructKdtree(partition.get(1),axis.negated()));

    }

    private List<List<KDPoint>> makePartition(List<KDPoint> points, KDLine line) {
        List<KDPoint> low = new LinkedList<KDPoint>();
        List<KDPoint> high = new LinkedList<KDPoint>();

        for(KDPoint p: points){
            // point in line is considered "low"
            if(p.getCoord(line.getAxis()) <= line.getPos() )
                low.add(p);
            else
                high.add(p);
        }
        List<List<KDPoint>> part = new LinkedList<List<KDPoint>>();
        part.add(low);
        part.add(high);
        return part;
    }

    protected abstract KDLine getLine(List<KDPoint> points, Axis axis);

    public KDPoint closestNeighbor(KDPoint q){

        KDLeaf currentBest = root.searchNeighbor(q);
        double currentDistance = currentBest.distance(q);

        KDNode actual = currentBest.getParent();
        KDNode prev = currentBest;

        while(actual.getParent()!= null){
            KDLeaf temp = actual.anotherSearch(prev, currentDistance,q);
            if(temp.distance(q)<currentDistance){
                currentBest = temp;
                currentDistance = currentBest.distance(q);
            }
            prev = actual;
            actual = actual.getParent();
        }
        return currentBest.getPoint();
    }
}
