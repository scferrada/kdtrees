import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastián
 * Date: 02-06-14
 * Time: 08:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class KDExperiment {
    static private final int C = 5;

    static public void main(String[] args){
        for(int i=10; i<=20; i++){
            int N = (int) Math.pow(2, i);
            List<KDPoint> randomPoints = null;
            List<KDPoint> lowDiscrepancyPoints = null;
            KDTree randomMeanTree = null, randomMedianTree = null, lowDMeanTree = null, lowDMedianTree = null;
            //Tree Construction
            boolean b = true;
            while(b){ //change to while error is higher than 5%
                randomPoints = KDPointGenerator.randomPoints(C, N);
                lowDiscrepancyPoints = KDPointGenerator.lowDiscrepancyPoints(C, N);

                //Measure time for every construction
                randomMeanTree = KDTreeFactory.makeMeanTreeWith(randomPoints);
                randomMedianTree = KDTreeFactory.makeMedianTreeWith(randomPoints);
                lowDMeanTree = KDTreeFactory.makeMeanTreeWith(lowDiscrepancyPoints);
                lowDMedianTree = KDTreeFactory.makeMedianTreeWith(lowDiscrepancyPoints);
                b = false;
                //calculate standard deviation
            }

            while(!b){ //change to while error is higher than 5%
                KDPoint query = KDPointGenerator.randomQueryPoint(C, N);

                //Measure time for every query
                randomMeanTree.closestNeighbor(query);
                randomMedianTree.closestNeighbor(query);
                lowDMeanTree.closestNeighbor(query);
                lowDMedianTree.closestNeighbor(query);

                b = !b;
                //calculate standard deviation
            }
        }
    }
}
