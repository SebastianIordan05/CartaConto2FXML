package com.cc2.cartaconto2fxml;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 *
 * @author seba2
 */
public class RegisterController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}