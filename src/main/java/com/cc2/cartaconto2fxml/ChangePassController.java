/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class ChangePassController {

    private Intestatario intestatario;

    @FXML
    private Button btnChangePassword;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtOldPassword;
    @FXML
    private TextField txtNewPassowrd;
    @FXML
    private Button btnBackToLogin;

    @FXML
    private void switchToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnBackToLogin.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void changePass() throws IOException, Exception {
        try {
            if (txtUsername.getText().trim().length() == 0 && txtOldPassword.getText().trim().length() == 0) {
                new Alert(Alert.AlertType.INFORMATION, "Wrong username and/or oldPassword!").showAndWait();
                return;
            }

            String code = txtUsername.getText();
            intestatario = App.intestatari.get(code);

            if (intestatario == null) {
                new Alert(Alert.AlertType.INFORMATION, "No intestatario found with the name: " + code).showAndWait();
                txtUsername.setText("");
                txtOldPassword.setText("");
                txtNewPassowrd.setText("");
                return;
            }

            if (txtOldPassword.getText().equals(intestatario.getPassword()) && txtUsername.getText().equals(intestatario.getNome())) {
                intestatario.setPassword(txtNewPassowrd.getText());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Wrong username and/or oldPassword!").showAndWait();
                return;
            }

            return;
        } catch (NumberFormatException ex) {
        } catch (IllegalArgumentException ex) {
        }

        txtUsername.setText("");
        txtOldPassword.setText("");
        txtNewPassowrd.setText("");
    }
}
