/**
 * Created with IntelliJ IDEA.
 * User: Claud
 * Date: 20-05-14
 * Time: 02:05 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Axis {
    X,Y ;

    public Axis negated(){
        switch (this){
            case X:
                return Y;
            case Y:
                return X;
            default:
                return null;
        }

    }




}


