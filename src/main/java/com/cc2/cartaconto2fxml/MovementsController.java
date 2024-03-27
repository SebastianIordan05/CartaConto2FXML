/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.cc2.cartaconto2fxml;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Conto;
import model.Intestatario;
import model.Movimento;

/**
 * FXML Controller class
 *
 * @author seba2
 */
public class MovementsController {

    private Intestatario i;
    private Conto c;

    @FXML
    private TextArea txtaMovements;
    @FXML
    private Button btnNewMovements;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnLogin;
    @FXML
    private Label lblSaldo;

    @FXML
    private void switchToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) txtaMovements.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToRegister() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newConto.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) txtaMovements.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToNewMovements() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newmovement.fxml"));
        Parent root = loader.load();

        NewMovementController nm = loader.getController();
        nm.setIntestatario(i);

        Stage stage = (Stage) txtaMovements.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setIntestatario(Intestatario i) {
        this.i = i;
    }

    public void setConto(Conto c) {
        this.c = c;
    }

    private String check() throws Exception {
        StringBuilder str = new StringBuilder();
        List<Movimento> operazioni = c.elencoOperazioni();

        for (Movimento movement : operazioni) {
            str.append("").append(movement.getNumeroProgressivo()).append(": \n")
                    .append("    ").append("Causale: ").append(movement.getDescrizione()).append("\n")
                    .append("    ").append("Data operazione: ").append(movement.getDataOperazione()).append("\n")
                    .append("    ").append("Importo: ").append(movement.getImporto()).append(" $").append("\n")
                    .append("    ").append("Costo operazione: ").append(movement.getCostoOperazione()).append(" $").append("\n")
                    .append("    ").append("Tipo movimento: ").append(movement.getTipoOperazione().getCodice()).append("\n")
                    .append("    ").append("Segno movimento: ").append(movement.getTipoOperazione().getSegnoOperazione()).append("\n");
        }

        setLabel();
        return str.toString();
    }

    private void setLabel() throws Exception {
        lblSaldo.setText(c.calcolaSaldo() + " $");
    }

    public void setText() throws Exception {
        txtaMovements.setText(check());
    }
}
