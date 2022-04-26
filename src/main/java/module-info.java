module dev.christopheredwards.assistext {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens dev.christopheredwards.assistext to javafx.fxml;
    exports dev.christopheredwards.assistext;
}