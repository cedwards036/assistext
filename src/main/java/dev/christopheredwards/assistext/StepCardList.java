package dev.christopheredwards.assistext;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StepCardList extends VBox {

    private int size;

    public void addCard(StepCard card) {
        size += 1;
        HBox cardHolder = new HBox();
        cardHolder.setAlignment(Pos.CENTER_LEFT);
        cardHolder.getChildren().add(new Text(size + "."));
        cardHolder.getChildren().add(card);
        getChildren().add(cardHolder);
    }
}
