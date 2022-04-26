package dev.christopheredwards.assistext;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Controller {

    @FXML
    private TextArea inputText;

    @FXML
    private TextArea outputText;

    @FXML
    public void initialize() {
        inputText.textProperty().addListener(((observable, oldValue, newValue) -> {
            outputText.setText(newValue);
        }));

        outputText.setEditable(false);
    }
}