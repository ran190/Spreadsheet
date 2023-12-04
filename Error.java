public class Error implements Node {
    protected String detail;

    public Error(String detail) {
        this.detail = detail;
    }

    public String toString() {
        return "ERROR: "+ detail;
    }

    @Override
    public double getValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValue'");
    }
}
