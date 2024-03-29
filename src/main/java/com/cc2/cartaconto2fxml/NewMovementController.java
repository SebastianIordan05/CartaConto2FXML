/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cc2.cartaconto2fxml;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Conto;
import model.Intestatario;
import model.Movimento;
import model.TipoMovimento;

/**
 *
 * @author seba2
 */
public class NewMovementController implements Initializable {

    private Intestatario i;
    private Conto c;
    private TipoMovimento tm;

    private Object selectedItem;
    private LocalDate date;

    @FXML
    private ChoiceBox<String> lstTipoMovimento;
    @FXML
    private TextField txtImporto;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button btnEffettuaBonifico;
    @FXML
    private Button btnBackToLogin;
    @FXML
    private Button btnBackToRegister;
    @FXML
    private TextField txtCausale;
    @FXML
    private Label lblSaldo;
    @FXML
    private Button btnCheckMovements;
    @FXML
    private Label lblIban;

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
    private void switchToRegister() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newConto.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnBackToRegister.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToMovements() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("movements.fxml"));
        Parent root = loader.load();

        MovementsController movements = loader.getController();
        movements.setIntestatario(i);
        movements.setConto(c);
        movements.setText();

        Stage stage = (Stage) btnBackToRegister.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void effettuaMovimento() throws Exception {
        movimento();
        c.registraOperazione(tm, Double.parseDouble(txtImporto.getText()), tm.getDescrizione(), date);

        System.out.println("importo to double: " + Double.valueOf(txtImporto.getText()));
        System.out.println(c.toString());

        System.out.println("saldo: " + c.calcolaSaldo() + ", IBAN: " + c.getCodiceIBAN() + ", nome: " + i.getNome() + ", in data: " + date);

        setLabel();
        txtImporto.setText("");
        txtCausale.setText("");
    }

    @FXML
    private void checkMovements() throws IOException, Exception {
        switchToMovements();
    }

    @FXML
    private void setDate() {
        date = datePicker.getValue();
    }

    @FXML
    private void setTipoMovimento() {
        selectedItem = lstTipoMovimento.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem: " + selectedItem);

    }

    private void movimento() {
        if (tm != null) {
            if (txtImporto.getText().trim().length() != 0 && txtCausale.getText().trim().length() != 0 && selectedItem != null && date != null) {
                tm.setCodice(selectedItem.toString());
                if ("versamento".equals(tm.getCodice())) {
                    tm.setSegnoOperazione('+');
                    System.out.println("segno: " + tm.getSegnoOperazione());
                } else if ("prelievo".equals(tm.getCodice()) || "bonifico ordinario".equals(tm.getCodice()) || "bonifico istantaneo".equals(tm.getCodice())) {
                    tm.setSegnoOperazione('-');
                    System.out.println("segno: " + tm.getSegnoOperazione());
                }
                if ("bonifico istantaneo".equals(tm.getCodice())) {
                    tm.setCosto(2.5); // 2,5 euro
                    System.out.println("costo: " + tm.getCosto());
                }
                tm.setDescrizione(txtCausale.getText());
                System.out.println(tm.toString());
            } else {
                new Alert(Alert.AlertType.ERROR, "Wrong importo, causale, tipo movimento and data!").showAndWait();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Movimento is null!").showAndWait();
            Platform.exit();
        }
    }

    public void setIntestatario(Intestatario i) throws Exception {
        this.i = i;

        String code = i.getNome();
        c = Conto.conti.get(code);

        if (c == null) {
            c = new Conto(generateIBAN(), i);
            Conto.conti.put(i.getNome(), c);

            new Alert(Alert.AlertType.INFORMATION, "New conto created for " + i.getNome()
                    + ", iban: " + c.getCodiceIBAN()).showAndWait();
        }

        lblIban.setText(c.getCodiceIBAN());
        setLabel();
    }

    private String generateIBAN() {
        StringBuilder ibanBuilder = new StringBuilder("IT");
        Random random = new Random();
        int indiceCasuale = random.nextInt(26);

        for (int j = 0; j < 2; j++) {
            ibanBuilder.append(random.nextInt(10));
        }
        
        char letteraCasuale = (char) ('A' + indiceCasuale);
        ibanBuilder.append(letteraCasuale);
        
        for (int j = 0; j < 22; j++) {
            ibanBuilder.append(random.nextInt(10));
        }
        
        System.out.println(ibanBuilder);
        return ibanBuilder.toString();
    }

    private void setLabel() throws Exception {
        lblSaldo.setText(c.calcolaSaldo() + " $");
    }

    private void initializeChoiceBox() {
        lstTipoMovimento.getItems().addAll("versamento", "prelievo", "bonifico ordinario", "bonifico istantaneo");
    }

    private void initializeDatePicker() {
        datePicker.setValue(LocalDate.now());
        date = datePicker.getValue();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeChoiceBox();
        initializeDatePicker();
        tm = new TipoMovimento();
    }
}
