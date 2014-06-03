import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Claud
 * Date: 20-05-14
 * Time: 04:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class KDTest {
    static public void main (String [] args) throws Exception{
        Axis a = Axis.X;
        //System.out.println(a.negated());

        KDPoint p1 = new KDPoint(1,1);
        KDPoint p2 = new KDPoint(2,4);
        KDPoint p3 = new KDPoint(4,2);
        KDPoint p4 = new KDPoint(3,3);
        KDPoint p5 = new KDPoint(5,1);


        List<KDPoint> points = new ArrayList<KDPoint>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        KDMedianTree medTree = new KDMedianTree(new KDMedianTree().constructKdtree(points, Axis.X));
        KDMeanTree meanTree = new KDMeanTree(new KDMeanTree().constructKdtree(points, Axis.X));
        KDTreeView.printNode(meanTree.getRoot());
        KDTreeView.printNode(medTree.getRoot());

        System.out.println(medTree.closestNeighbor(new KDPoint(5.1,1.6)));
        System.out.println(meanTree.closestNeighbor(new KDPoint(5.1,1.6)));
    }

}
