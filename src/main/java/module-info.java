module com.cc2.cartaconto2fxml {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.cc2.cartaconto2fxml to javafx.fxml;
    exports com.cc2.cartaconto2fxml;
}
