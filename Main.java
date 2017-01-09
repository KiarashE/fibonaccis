import java.math.BigInteger;
import java.util.HashMap;

public class Main {

    private static HashMap<BigInteger, BigInteger> stash = new HashMap<>();
    private static BigInteger ZERO = BigInteger.ZERO;
    private static BigInteger ONE  = BigInteger.ONE;

    public static void main(String[] args) {


        /**
         * Either assign value as parameter to the main method "java Main x y z..." for
         * list of results or assign variable n in the code for single result
         */

        int n = 6;

        if(args.length > 0){
            for(int i = 0; i < args.length; i++){
                System.out.println(args[i] + " - " + getValue(Integer.parseInt(args[i])));
            }
        } else {
            System.out.println(getValue(n));
        }

    }
    
    public static BigInteger getValue(int n){
        return getBigValue(BigInteger.valueOf(n));
    }

    public static BigInteger getBigValue(BigInteger n) {
        if (n.equals(ZERO))
            return ZERO;
        if (n.equals(ONE))
            return ONE;
        if (stash.containsKey(n))
            return stash.get(n);

        BigInteger n2 = n.shiftRight(1);
        BigInteger n2Value = getBigValue(n2);
        if (n.testBit(0)) {
            BigInteger n2PlusOne = n2.add(ONE);
            BigInteger n3Value = getBigValue(n2PlusOne);
            BigInteger n2Square = n2Value.pow(2);
            BigInteger n3Square = n3Value.pow(2);
            BigInteger outcome = n2Square.add(n3Square);
            stash.put(n, outcome);
            return outcome;
        }
        else {
            BigInteger n2MinusOne = n2.subtract(ONE);
            BigInteger n3Value = getBigValue(n2MinusOne);
            BigInteger n3Multiplied2 = n3Value.add(n3Value);
            BigInteger n2PlusN3 = n2Value.add(n3Multiplied2);
            BigInteger outcome = n2Value.multiply(n2PlusN3);
            stash.put(n, outcome);
            return outcome;
        }
    }
}