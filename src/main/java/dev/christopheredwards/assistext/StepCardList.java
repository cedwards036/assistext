package dev.christopheredwards.assistext;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StepCardList extends VBox {

    private int size;

    public void addCard(StepCard card) {
        size += 1;
        HBox cardHolder = new HBox();
        cardHolder.getStyleClass().add("step-card");
        cardHolder.setAlignment(Pos.CENTER_LEFT);
        cardHolder.getChildren().add(new NormalText(size + "."));
        cardHolder.getChildren().add(card);
        getChildren().add(cardHolder);
    }
}
