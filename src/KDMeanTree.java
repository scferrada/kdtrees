import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Claud
 * Date: 20-05-14
 * Time: 02:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class KDMeanTree extends KDTree {


    protected KDMeanTree(KDNode root) {
        super(root);
    }

    public KDMeanTree() {
        //To change body of created methods use File | Settings | File Templates.
    }

    @Override
    protected KDLine getLine(List<KDPoint> points, Axis axis) {
        return new KDLine(axis, calcMean(points,axis));  //To change body of implemented methods use File | Settings | File Templates.
    }

    private double calcMean(List<KDPoint> points,Axis axis){
        double min = Double.MAX_VALUE;
        double max = -Double.MAX_VALUE;
        for(KDPoint p: points){
            if(p.getCoord(axis)>max){
                max = p.getCoord(axis);
            }
            if(p.getCoord(axis)<min){
                min = p.getCoord(axis);
            }

        }
        return (max+min)/2;
    }



}
