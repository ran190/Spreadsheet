public class NumberConstant implements Node {
    protected double value;

    public NumberConstant(double n) {
        value = n;
    }

    public String toString() {
        return ""+value;
    }

    public double getValue(){
        return value;
    }
}
