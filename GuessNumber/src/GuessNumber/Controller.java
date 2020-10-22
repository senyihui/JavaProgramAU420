package GuessNumber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.lang.annotation.Target;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button guessBtn;

    @FXML
    private Button restartBtn;

    @FXML
    private TextField inputField;

    @FXML
    private TextArea outputArea;

    // Variables
    private int TARGET;
    private String OUTPUT = "";
    private static final int BOUND = 2000;

    private static final String successMsg =
            "Congratulations. You guessed the number!\n";
    private static final String highMsg =
            "Too high. Try again.\n";
    private static final String lowMsg =
            "Too low. Try again.\n";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateTarget();
    }

    private void generateTarget() {
        Random random = new Random(System.currentTimeMillis());
        TARGET = random.nextInt(BOUND);
        System.out.println("Target: " + TARGET);
    }

    /**
     * action when "Guess" button is clicked
     * @param event
     */
    @FXML
    protected void handleGuessButtonAction(ActionEvent event) {
        int input;
        try {
            input = Integer.parseInt(inputField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Not a Number");
            alert.setContentText("Input must be a number between 0 and 2000!");
            alert.showAndWait();
            clearArea();
            return;
        }
        if (input < 0 || input > 2000) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Out of Range");
            alert.setContentText("Input must be a number between 0 and 2000!");
            alert.showAndWait();
        } else if (input < TARGET) {
            OUTPUT += lowMsg;
        } else if (input > TARGET) {
            OUTPUT += highMsg;
        } else {
            OUTPUT += successMsg;
        }
        outputArea.setText(OUTPUT);
    }

    /**
     * action when "Restart" button is clicked
     * @param event
     */
    @FXML
    protected void handleRestartButtonAction(ActionEvent event) {
        clearArea();
        generateTarget();
    }

    /**
     * clear text area and text field
     */
    private void clearArea() {
        OUTPUT = "";
        outputArea.setText(OUTPUT);
        inputField.setText("");
    }
}
