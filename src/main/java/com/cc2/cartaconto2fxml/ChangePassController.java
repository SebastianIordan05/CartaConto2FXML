/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cc2.cartaconto2fxml;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Intestatario;

/**
 *
 * @author seba2
 */
public class ChangePassController {

    private Intestatario i;

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

        Stage stage = (Stage) txtUsername.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToRegister() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newConto.fxml"));
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
                new Alert(Alert.AlertType.ERROR, "Wrong username and/or oldPassword!").showAndWait();
                return;
            }

            String code = txtUsername.getText();
            i = Intestatario.intestatari.get(code);

            if (i == null) {
                Alert noIntestatarioFound = new Alert(Alert.AlertType.CONFIRMATION, "No intestatario found with the name: "
                        + code + "\nDo you want to create a new one?");
                noIntestatarioFound.setHeaderText("Intestatario not found!");

                ButtonType btnOK = new ButtonType("Si");
                ButtonType btnNO = new ButtonType("No");
                ButtonType btnEXIT = new ButtonType("Exit");

                noIntestatarioFound.getButtonTypes().setAll(btnOK, btnNO, btnEXIT);

                noIntestatarioFound.showAndWait().ifPresentOrElse(result -> {
                    if (result == btnOK) {
                        System.out.println("You clicked OK");
                        try {
                            switchToRegister();
                        } catch (IOException ex) {
                        }
                    } else if (result == btnNO) {
                        System.out.println("You clicked NO");
                    } else if (result == btnEXIT) {
                        Platform.exit();
                        System.out.println("You clicked EXIT");
                    }
                }, () -> {
                    System.out.println("No button was clicked");
                });

                txtUsername.setText("");
                txtOldPassword.setText("");
                txtNewPassowrd.setText("");
                return;
            }

            if (txtOldPassword.getText().equals(i.getPassword()) && txtUsername.getText().equals(i.getNome())) {
                if (txtOldPassword.getText().equals(txtNewPassowrd.getText())) {
                    i.setPassword(txtNewPassowrd.getText());
                } else {
                    new Alert(Alert.AlertType.ERROR, "NewPassword == OldPpassword!").showAndWait();
                    return;
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Wrong username and/or oldPassword!").showAndWait();
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
