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
    }
    public KDInternalNode(KDLine line) {
        this.line = line;
    }

    public KDInternalNode(Axis axis, double pos ){
        this.line = new KDLine(axis, pos);

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
}
