import java.util.ArrayList;
import java.util.Arrays;
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

    public KDMedianTree() {
        //To change body of created methods use File | Settings | File Templates.
    }

    @Override
    protected KDLine getLine(List<KDPoint> points, Axis axis) {
        return new KDLine(axis, calcMedian(points, axis));  //To change body of implemented methods use File | Settings | File Templates.
    }

    private double calcMedian(List<KDPoint> points, Axis axis) {
        if(points.size()<5){
            return median(points, axis);
        }
        int nbOfGroups = (int) Math.ceil(points.size()/5.0);
        ArrayList<Double> medians = new ArrayList<Double>();
        for(int i = 0; i<nbOfGroups; i++){
            medians.add(median(points.subList(i*5, Math.min((i+1)*5, points.size())), axis ));
        }
        double finalMedian = median(medians);
        return finalMedian;
    }

    private double median(List<KDPoint> kdPoints, Axis axis) {
        ArrayList<Double> coords = new ArrayList<Double>();
        for(KDPoint p : kdPoints)
            coords.add(p.getCoord(axis));

        return median(coords);
    }

    private double median(ArrayList numbers) {
        Double[] nums = new Double[numbers.size()];
        nums = (Double[]) numbers.toArray(nums);
        Arrays.sort(nums);
        if(nums.length%2 == 1)
            return nums[nums.length/2];
        else{
            int index = nums.length/2;
            return (nums[index-1]+nums[index])/2.0;
        }
    }

}
