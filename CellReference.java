public class CellReference implements Node {
    protected String reference;

    public CellReference(String text) {
        this.reference = text;
    }

    public String toString() {
        return "Cell: "+ reference;
    }

    @Override
    public double getValue() {
        int[] intPlace = Manager.convert(reference);
        String content = Spreadsheet.printCellContent(intPlace[0], intPlace[1]);
        try {
            // Attempt to convert content to a number
            return Double.parseDouble(content);
        } catch (NumberFormatException e) {
            // If conversion fails, return NaN
            return Double.NaN;
        }
    }
}
