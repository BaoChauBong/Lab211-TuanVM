
public class Matrix {

    private int rows;
    private int columns;
    private int[][] matrix;

    public Matrix(int rows, int columns, int[][] matrix) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = matrix;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
}
