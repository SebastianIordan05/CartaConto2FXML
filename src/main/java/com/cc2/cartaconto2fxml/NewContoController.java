/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cc2.cartaconto2fxml;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Intestatario;

/**
 *
 * @author seba2
 */
public class NewContoController {
    
    private Intestatario intestatario;

    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtDataDiNascita;
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
    private void switchToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        
        newIntestatario();

        Stage stage = (Stage) btnNewIntestatario.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    private void newIntestatario() {
        intestatario = new Intestatario(txtCodiceFiscale.getText(), txtSurname.getText(),
                txtName.getText(), txtCittaDiNascita.getText(), new Date(), txtIndirizzoAttuale.getText(),
                txtCapAttuale.getText(), txtCittaAttuale.getText(), txtProvinciaAttuale.getText(),
                txtCellulare.getText(), txtEmail.getText(), txtPassword.getText());
        
        App.intestatari.put(txtName.getText(), intestatario);
    }
}
