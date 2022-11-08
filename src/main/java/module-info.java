module be.ugent.oplossing {
    requires javafx.controls;
    requires javafx.fxml;


    opens be.ugent.oplossing to javafx.fxml;
    exports be.ugent.oplossing;
    exports be.ugent.oplossing.show;
    opens be.ugent.oplossing.show to javafx.fxml;
}
