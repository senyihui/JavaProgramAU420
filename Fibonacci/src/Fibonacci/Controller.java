package Fibonacci;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    @FXML
    private Button Btn;

    @FXML
    private TextField inputField;

    @FXML
    private Label label;

    /**
     * action when "Calculate" button is clicked
     * @param event
     */
    @FXML
    protected void handleCalculateButtonAction(ActionEvent event) {
        long input;
        try {
            input = Integer.parseInt(inputField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Not a Number");
            alert.setContentText("Input must be a number!");
            alert.showAndWait();
            clearLabel();
            return;
        }
        if (input < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Range Error");
            alert.setContentText("Input must be larger than 0!");
            alert.showAndWait();
            clearLabel();
        } else {
            label.setText(String.valueOf(Fibonacci(input)));
        }

    }

    /**
     * Calculate with DP algorithm
     * use reminder to avoid OUT OF RANGE
     * @param num type long
     * @return
     */
    private int Fibonacci(long num) {
        int a = 0, b = 1, sum;
        for(int i = 0; i < num; i++){
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }

    private void clearLabel() {
        label.setText("");
    }
}
