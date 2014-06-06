import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastián
 * Date: 05-06-14
 * Time: 12:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class KDMeanSecMemTree extends KDMeanTree {

    private String filename;

    public KDMeanSecMemTree(String filename) {
        this.filename = filename;
    }

    @Override
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


}

