package dev.christopheredwards.assistext;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.getIcons().add(new Image(String.valueOf(Application.class.getResource("assistext_logo.png"))));
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("run-pipeline-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        scene.getStylesheets().add(String.valueOf(Application.class.getResource("main.css")));
        stage.setTitle("AssisText");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}