/**
 * Created with IntelliJ IDEA.
 * User: Claud
 * Date: 20-05-14
 * Time: 02:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class KDLine {
    Axis axis;
    double pos;

    public Axis getAxis() {
        return axis;
    }

    public double getPos() {
        return pos;
    }

    public KDLine(Axis axis, double pos) {
        this.axis = axis;
        this.pos = pos;
    }

    public double distance(KDPoint q) {
        return Math.abs(q.getCoord(axis)-pos);
    }
}
