package dev.christopheredwards.assistext;

import javafx.scene.layout.VBox;

public abstract class StepCard extends VBox {

    private final VBox attributes;

    public StepCard(String title) {
        super();
        attributes = new VBox();
        attributes.getStyleClass().add("step-card-attributes");
        NormalText titleText = new NormalText(title);
        titleText.getStyleClass().add("step-card-title");
        getChildren().add(titleText);
        getChildren().add(attributes);
    }

    public void addKeyValueAttribute(String key, String value) {
        attributes.getChildren().add(new NormalText(key + ": \"" + value + "\""));
    }

    public void addValueAttribute(String attribute) {
        attributes.getChildren().add(new NormalText(attribute));
    }

}
