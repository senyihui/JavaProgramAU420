package MatrixOperation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    private int[][] matrix1;
    private int[][] matrix2;

    @FXML
    private TextField row_1;

    @FXML
    private TextField col_1;

    @FXML
    private TextField row_2;

    @FXML
    private TextField col_2;

    @FXML
    private TextArea matrix_1;

    @FXML
    private TextArea matrix_2;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_minus;

    @FXML
    private Button btn_multiply;

    @FXML
    private Label label_output;

    /**
     * action when "add" button is clicked
     * @param event
     */
    @FXML
    protected void addButtonAction(ActionEvent event) {
        initMatrix();
        validateAddMinus(matrix1, matrix2);
        int[][] result = addOperation(matrix1, matrix2);
        String output = display(result);
        label_output.setText(output);
    }

    /**
     * action when "minus" button is clicked
     * @param event
     */
    @FXML
    protected void minusButtonAction(ActionEvent event) {
        initMatrix();
        validateAddMinus(matrix1, matrix2);
        int[][] result = minusOperation(matrix1, matrix2);
        String output = display(result);
        label_output.setText(output);
    }

    /**
     * action when "multiply" button is clicked
     * @param event
     */
    @FXML
    protected void multiplyButtonAction(ActionEvent event) {
        initMatrix();
        validateMultiply(matrix1, matrix2);
        int[][] result = multiplyOperation(matrix1, matrix2);
        String output = display(result);
        label_output.setText(output);
    }

    /**
     * edit output
     * @param result
     * @return
     */
    private String display(int[][] result) {
        int row = result.length;
        int col = result[0].length;
        StringBuilder output = new StringBuilder();

        for (int rIndex = 0; rIndex < row; rIndex++) {
            for (int cIndex = 0; cIndex < col; cIndex++) {
                output.append(result[rIndex][cIndex]).append(" ");
            }
            output.append("\n");
        }

        return output.toString();
    }

    /**
     * process input and init matrix
     */
    private void initMatrix() {
        String mtrixInput1 = matrix_1.getText();
        String[] processed1 = mtrixInput1.split("\n");
        int row1 = Integer.parseInt(row_1.getText());
        int col1 = Integer.parseInt(col_1.getText());

        String mtrixInput2 = matrix_2.getText();
        String[] processed2 = mtrixInput2.split("\n");
        int row2 = Integer.parseInt(row_2.getText());
        int col2 = Integer.parseInt(col_2.getText());

        matrix1 = new int[row1][col1];
        matrix2 = new int[row2][col2];

        // init matrix1
        for (int rIndex = 0; rIndex < row1; rIndex++) {
            String[] rowString = processed1[rIndex].split(" ");
            for (int cIndex = 0; cIndex < col1; cIndex++) {
                matrix1[rIndex][cIndex] = Integer.parseInt(rowString[cIndex]);
            }
        }

        // init matrix2
        for (int rIndex = 0; rIndex < row2; rIndex++) {
            String[] rowString = processed2[rIndex].split(" ");
            for (int cIndex = 0; cIndex < col2; cIndex++) {
                matrix2[rIndex][cIndex] = Integer.parseInt(rowString[cIndex]);
            }
        }
    }

    /**
     * Matrix Add Operation
     * @param matrix1 matrix_1
     * @param matrix2 matrix_2
     * @return result label_output
     */
    private int[][] addOperation(int[][] matrix1, int[][] matrix2) {
        int row = matrix1.length;
        int col = matrix1[0].length;
        int[][] result = new int[row][col];

        for (int rIndex = 0; rIndex < row; rIndex++) {
            for (int cIndex = 0; cIndex < col; cIndex++) {
                result[rIndex][cIndex] = matrix1[rIndex][cIndex] + matrix2[rIndex][cIndex];
            }
        }
        return result;
    }

    /**
     * Matrix Minus Operation
     * @param matrix1 matrix_1
     * @param matrix2 matrix_2
     * @return result label_output
     */
    private int[][] minusOperation(int[][] matrix1, int[][] matrix2) {
        int row = matrix1.length;
        int col = matrix1[0].length;
        int[][] result = new int[row][col];

        for (int rIndex = 0; rIndex < row; rIndex++) {
            for (int cIndex = 0; cIndex < col; cIndex++) {
                result[rIndex][cIndex] = matrix1[rIndex][cIndex] - matrix2[rIndex][cIndex];
            }
        }
        return result;
    }

    /**
     * Matrix Multiply Operation
     * @param matrix1 matrix_1
     * @param matrix2 matrix_2
     * @return result label_output
     */
    private int[][] multiplyOperation(int[][] matrix1, int[][] matrix2) {
        int row = matrix1.length;
        int col = matrix2[0].length;
        int[][] result = new int[row][col];

        for (int rIndex = 0; rIndex < row; rIndex++) {
            for (int cIndex = 0; cIndex < col; cIndex++) {
                int sum = 0;
                for (int colOfMatrix1 = 0, rowOfMatrix2 = 0; colOfMatrix1 < matrix1[0].length; colOfMatrix1++, rowOfMatrix2++) {
                    int elem1 = matrix1[rIndex][colOfMatrix1];
                    int elem2 = matrix2[rowOfMatrix2][cIndex];
                    sum += elem1 * elem2;
                }
                result[rIndex][cIndex] = sum;
            }
        }
        return result;
    }

    /**
     * Matrix should have the same number of rows and cols
     * @param matrix1
     * @param matrix2
     */
    private void validateAddMinus(int[][] matrix1, int[][] matrix2) {
        if (matrix1.length != matrix2.length ||
                matrix1[0].length != matrix2.length) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Validation Error");
            alert.setContentText("Matrix should have the same number of rows and cols!");
            alert.showAndWait();
        }
    }

    /**
     * The col of Matrix1 should be the same as the row of Matrix2
     * @param matrix1
     * @param matrix2
     */
    private void validateMultiply(int[][] matrix1, int[][] matrix2) {
        if (matrix1[0].length != matrix2.length) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Validation Error");
            alert.setContentText("The col of Matrix1 should be the same as the row of Matrix2!");
            alert.showAndWait();
        }
    }

}
