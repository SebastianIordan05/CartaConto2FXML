package com.cc2.cartaconto2fxml;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 *
 * @author seba2
 */
public class LoginController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
