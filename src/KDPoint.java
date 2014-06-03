/**
 * Created with IntelliJ IDEA.
 * User: Claud
 * Date: 20-05-14
 * Time: 02:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class KDPoint {

    private double x;
    private double y;

    public KDPoint(){
        x = y = 0.0;
    }

    public KDPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance (KDPoint that){
        return Math.sqrt(Math.pow(this.x - that.x,2) + Math.pow(this.y - that.y,2) );
    }

    public double getCoord (Axis axis){
        switch (axis){
            case X:
                return x;
            case Y:
                return y;
            default:
                return 0.0;
        }
    }

    @Override
    public String toString() {
        return x +","+ y;
    }
}
