/**
 * Created with IntelliJ IDEA.
 * User: Claud
 * Date: 20-05-14
 * Time: 02:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class KDInternalNode extends KDNode {
    KDNode left, right;
    KDLine line;

    public KDInternalNode(KDLine line, KDNode left, KDNode right){
        this.line = line;
        this.left = left;
        this.right = right;
        left.parent = this;
        right.parent = this;
    }
    public KDInternalNode(KDLine line) {
        this.line = line;
    }

    public KDInternalNode(Axis axis, double pos ){
        this.line = new KDLine(axis, pos);

    }

    public KDLine getLine() {
        return line;
    }

    public KDNode getRight() {
        return right;
    }

    public void setRight(KDNode right) {
        this.right = right;
    }

    public KDNode getLeft() {
        return left;
    }

    public void setLeft(KDNode left) {
        this.left = left;
    }

    @Override
    public KDLeaf searchNeighbor(KDPoint q) {
        if(q.getCoord(line.getAxis())<= line.getPos()){
            return left.searchNeighbor(q);
        }
        else
          return right.searchNeighbor(q);//To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double distance(KDPoint q) {
        return line.distance(q);
    }

    @Override
    public KDLeaf anotherSearch(KDNode aChild, double currentDistance, KDPoint q) {
        KDLeaf bestLeft = new KDLeaf(new KDPoint(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)),
               bestRight = new KDLeaf(new KDPoint(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY));
        if(left!=aChild && left.intersects(q,currentDistance)){
            bestLeft = left.anotherSearch(aChild, currentDistance, q);
        }
        if(right!=aChild && right.intersects(q, currentDistance)){
            bestRight = right.anotherSearch(aChild, currentDistance, q);
        }
        return (bestLeft.distance(q)>bestRight.distance(q))? bestRight:bestLeft;
    }

    @Override
    public boolean intersects(KDPoint q, double currentDistance) {
        //this l10
        if(parent.parent == null){
            if(this == parent.right)
                return q.getX()+currentDistance >= parent.line.getPos();
            else
                return q.getX()-currentDistance <= parent.line.getPos();

        }
        double eje1 = parent.line.getPos();
        Axis aeje1 = line.axis.negated();
        double eje2 = parent.parent.line.getPos();
        KDPoint vertex = new KDPoint((aeje1 == Axis.X)?eje1:eje2, (aeje1 == Axis.Y)?eje1:eje2);

        if(vertex.distance(q)<=currentDistance)
            return true;

        boolean imHigh = this == parent.right;
        boolean fatherIsHigh = parent == parent.parent.right;

        // si llega a fallar, el return usar if/else respecto al axis
        if(imHigh){
            if(fatherIsHigh){
               return (q.getCoord(aeje1)+currentDistance >= eje1
                       && q.getCoord(line.getAxis())-currentDistance >= eje2) ||
                       (q.getCoord(line.getAxis())+currentDistance >= eje2
                       && q.getCoord(aeje1)-currentDistance >= eje1);
            }

            return (q.getCoord(aeje1)-currentDistance <= eje1
                    && q.getCoord(line.getAxis())-currentDistance >= eje2) ||
                    (q.getCoord(line.getAxis())-currentDistance <= eje2
                    && q.getCoord(aeje1)-currentDistance >= eje1);

        }else{
            if(fatherIsHigh){
                return (q.getCoord(aeje1)+currentDistance >= eje1
                        && q.getCoord(line.getAxis())+currentDistance <= eje2) ||
                        (q.getCoord(line.getAxis())+currentDistance >= eje2
                        && q.getCoord(aeje1)+currentDistance <= eje1);
            }
            return (q.getCoord(aeje1)-currentDistance <= eje1
                    && q.getCoord(line.getAxis())+currentDistance <= eje2) ||
                    (q.getCoord(line.getAxis())-currentDistance <= eje2
                    && q.getCoord(aeje1)+currentDistance <= eje1);
        }
    }

    @Override
    public int height() {
        return 1 +  Math.max(left.height(), right.height());
    }

    @Override
    public int usedSpace() {
        return space() + left.usedSpace() + right.usedSpace();

    }

    private int space() {
        return 41;
    }
}
