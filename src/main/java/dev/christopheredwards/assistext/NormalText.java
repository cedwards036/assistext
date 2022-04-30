package dev.christopheredwards.assistext;

import javafx.scene.text.Text;

public class NormalText extends Text {

    public NormalText() {
        super();
        initialize();
    }

    public NormalText(String value) {
        super(value);
        initialize();
    }

    private void initialize() {
        getStyleClass().add("main-text-color");
    }
}
