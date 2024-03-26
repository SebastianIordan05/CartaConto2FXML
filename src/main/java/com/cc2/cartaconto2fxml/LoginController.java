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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Intestatario;

/**
 *
 * @author seba2
 */
public class LoginController {

    private Intestatario i;

    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
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
        if (i != null) {
            newMovement.setIntestatario(i);
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
                return;
            }

            String code = txtUsername.getText();
            i = Intestatario.intestatari.get(code);

            if (i == null) {

                Alert intestatarioNotFound = new Alert(Alert.AlertType.CONFIRMATION, "No intestatario found with the name: " +
                        code + "\nDo you want to create a new one?");
                intestatarioNotFound.setHeaderText("Intestatario not found!");

                ButtonType btnOK = new ButtonType("Si");
                ButtonType btnNO = new ButtonType("No");
                ButtonType btnEXIT = new ButtonType("Exit");

                intestatarioNotFound.getButtonTypes().setAll(btnOK, btnNO, btnEXIT);

                intestatarioNotFound.showAndWait().ifPresentOrElse(result -> {
                    if (result == btnOK) {
                        System.out.println("You clicked OK");
                        try {
                            switchToRegister();
                        } catch (IOException ex) {}
                    } else if (result == btnNO) {
                        System.out.println("You clicked NO");
                    } else if (result == btnEXIT) {
                        Platform.exit();
                        System.out.println("You clicked EXIT");
                    }
                }, () -> {
                    System.out.println("Nessun pulsante è stato premuto");
                });

                txtUsername.setText("");
                txtPassword.setText("");
                return;
            }

            if (txtPassword.getText().equals(i.getPassword()) && txtUsername.getText().equals(i.getNome())) {
                switchToLogin();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Wrong username and/or password!").showAndWait();
                return;
            }

            return;
        } catch (NumberFormatException ex) {
        } catch (IllegalArgumentException ex) {
        }

        txtUsername.setText("");
        txtPassword.setText("");
    }
}
