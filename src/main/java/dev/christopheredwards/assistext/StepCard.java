package dev.christopheredwards.assistext;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class StepCard extends VBox {

    private final VBox attributes;

    public StepCard(String title) {
        super();
        attributes = new VBox();
        getChildren().add(new Text(title));
        getChildren().add(attributes);
    }

    public void addKeyValueAttribute(String key, String value) {
        attributes.getChildren().add(new Text(key + ": \"" + value + "\""));
    }

    public void addValueAttribute(String attribute) {
        attributes.getChildren().add(new Text(attribute));
    }

}
