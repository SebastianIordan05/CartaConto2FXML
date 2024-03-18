module com.cc2.cartaconto2fxml {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.cc2.cartaconto2fxml to javafx.fxml;
    exports com.cc2.cartaconto2fxml;
}
