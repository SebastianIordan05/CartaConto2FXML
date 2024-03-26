/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cc2.cartaconto2fxml;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Intestatario;

/**
 *
 * @author seba2
 */
public class NewContoController implements Initializable {

    private Intestatario i;
    private LocalDate date;

    @FXML
    private TextField txtSurname;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCodiceFiscale;
    @FXML
    private TextField txtCittaDiNascita;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtIndirizzoAttuale;
    @FXML
    private TextField txtCapAttuale;
    @FXML
    private TextField txtCittaAttuale;
    @FXML
    private TextField txtProvinciaAttuale;
    @FXML
    private TextField txtCellulare;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnNewIntestatario;
    @FXML
    private Button btnBackToLogin;

    @FXML
    private void switchToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        newIntestatario();

        Stage stage = (Stage) btnNewIntestatario.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void switchToLogin2() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnBackToLogin.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static boolean controllaEmail(String email) {
        String pattern = "[a-zA-Z]+\\.[a-zA-Z]+@edu\\.iisleviponti\\.it";

        return email.matches(pattern);
    }

//    public static boolean controllaNumeroTelefono(String numero) {
//        String pattern = "^\\+(?:[0-9] ?){6,14}[0-9]$";
//
//        Pattern p = Pattern.compile(pattern);
//        Matcher m = p.matcher(numero);
//
//        return m.matches();
//    }
    
    public static boolean controllaCodiceFiscale(String codiceFiscale) {
        if (codiceFiscale.length() != 16) {
            return false;
        }

        String cf = "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$";

        return codiceFiscale.matches(cf);
    }

    public static boolean controllaCAP(String cap) {
        if (cap.length() != 5) {
            return false;
        }

        for (char c : cap.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    @FXML
    private void setDate() {
        date = datePicker.getValue();
    }

    private void newIntestatario() {
        if (txtCodiceFiscale.getText().trim().length() == 0 && txtSurname.getText().trim().length() == 0
                && txtName.getText().trim().length() == 0 && txtCittaDiNascita.getText().trim().length() == 0 && date == null
                && txtIndirizzoAttuale.getText().trim().length() == 0 && txtCapAttuale.getText().trim().length() == 0
                && txtCittaAttuale.getText().trim().length() == 0 && txtProvinciaAttuale.getText().trim().length() == 0
                && txtCellulare.getText().trim().length() == 0 && txtEmail.getText().trim().length() == 0
                && txtPassword.getText().trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Wrong username and/or password!").showAndWait();
        } else {
            i = new Intestatario(txtCodiceFiscale.getText(), txtSurname.getText(),
                    txtName.getText(), txtCittaDiNascita.getText(), date, txtIndirizzoAttuale.getText(),
                    txtCapAttuale.getText(), txtCittaAttuale.getText(), txtProvinciaAttuale.getText(),
                    txtCellulare.getText(), txtEmail.getText(), txtPassword.getText());

            Intestatario.intestatari.put(txtName.getText(), i);
        }

    }

    private void initializeDatePicker() {
        datePicker.setValue(LocalDate.now());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeDatePicker();
    }
}
