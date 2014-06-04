/**
 * Created with IntelliJ IDEA.
 * User: Claud
 * Date: 04-06-14
 * Time: 01:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Halton
{
    protected static final float kOneOverThree = 1f / 3f;


    //Current sample point.
    protected double x, y;
    protected long base2, base3;

    /**
     * Advance to next point in the sequence. Returns the index of this point.
     */

    public long inc() {
        /////////////////////////////////////
        // base 2

        long oldBase2 = base2;
        base2++;
        long diff = base2 ^ oldBase2;

        // bottom bit always changes, higher bits
        // change less frequently.
        float s = 0.5f;

        // diff will be of the form 0*1+, i.e. one bits up until the last carry.
        // expected iterations = 1 + 0.5 + 0.25 + ... = 2
        do {
            if ((oldBase2 & 1) != 0) {
                x -= s;
            }
            else {
                x += s;
            }

            s *= 0.5f;

            diff = diff >> 1;
            oldBase2 = oldBase2 >> 1;
        } while (diff != 0);


        /////////////////////////////////////
        // base 3: use 2 bits for each base 3 digit.

        long mask = 0x3;  // also the max base 3 digit
        long add = 0x1;  // amount to add to force carry once digit==3
        s = kOneOverThree;

        base3++;

        // expected iterations: 1.5
        while (true) {
            if ((base3 & mask) == mask) {
                base3 += add;          // force carry into next 2-bit digit
                y -= 2 * s;

                mask = mask << 2;
                add = add << 2;

                s *= kOneOverThree;
            } else {
                y += s;     // we know digit n has gone from a to a + 1
                break;
            }
        }

        return base2; // return the index of this sequence point
    }


    public KDPoint getValues(){
        return new KDPoint(x,y);
    }


    public KDPoint getValues(int index) {
        return new KDPoint(getHalton(index, 2),
                getHalton(index, 3));
    }

    protected double getHalton(long n, int b) {
        double result = 0;
        double ip = 1 / b;
        double p = ip;

        while (n > 0) {
            result += (n % b) * p;
            n = n / b;
            p *= ip;
        }

        return result;
    }

    /**
     * Move back to first point in the sequence (i.e. the origin.)
     */

    public void reset() {
        x = y = 0f;
        base2 = base3 = 0;
    }
}