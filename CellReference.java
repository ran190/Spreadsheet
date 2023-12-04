public class CellReference implements Node {
    protected String reference;
    protected Spreadsheet spreadsheet; 

    public CellReference(String text) {
        this.reference = text;
    }

    public CellReference(String text, Spreadsheet spreadsheet) {
        this.reference = text;
        this.spreadsheet = spreadsheet;
    }

    public String toString() {
        return "Cell: "+ reference;
    }

    @Override
    public double getValue() {
        int[] intPlace = Manager.convert(reference);
        String content = spreadsheet.printCellContent(intPlace[0], intPlace[1]);
        try {
            // Attempt to convert content to a number
            return Double.parseDouble(content);
        } catch (NumberFormatException e) {
            // If conversion fails, return NaN
            return Double.NaN;
        }
    }
}
