package com.cc2.cartaconto2fxml;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Intestatario;

/**
 *
 * @author seba2
 */
public class LoginController {

    private Intestatario intestatario;

    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnChangePass;

    @FXML
    private void switchToRegister() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newConto.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnRegister.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void switchToChangePass() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("changepass.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnChangePass.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void switchToLogin() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newmovement.fxml"));
        Parent root = loader.load();

        NewMovementController newMovement = loader.getController();
        newMovement = loader.getController();
        if (intestatario != null) {
            newMovement.setIntestatario(intestatario);
        }
        
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void login() throws IOException, Exception {
        try {
            if (txtUsername.getText().trim().length() == 0 && txtPassword.getText().trim().length() == 0) {
                new Alert(Alert.AlertType.INFORMATION, "Wrong username and/or password!").showAndWait();
            }

            String code = txtUsername.getText();
            intestatario = App.intestatari.get(code);

            if (intestatario == null) {
                new Alert(Alert.AlertType.INFORMATION, "No intestatario found with the name: " + code).showAndWait();
                txtUsername.setText("");
                txtPassword.setText((""));
                return;
            }

            if (txtPassword.getText().equals(intestatario.getPassword()) && txtUsername.getText().equals(intestatario.getNome())) {
                switchToLogin();
            }

            return;
        } catch (NumberFormatException ex) {
        } catch (IllegalArgumentException ex) {}

        txtUsername.setText("");
        txtPassword.setText("");
    }
}
