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
    private TipoMovimento m;

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
    private void effettuaMovimento() throws Exception {
        movimento();
        c.registraOperazione(m, Double.parseDouble(txtImporto.getText()), m.getDescrizione(), date);

        System.out.println("importo to double: " + Double.valueOf(txtImporto.getText()));
        System.out.println(c.toString());

        System.out.println("saldo: " + c.calcolaSaldo() + ", IBAN: " + c.getCodiceIBAN() + ", nome: " + i.getNome() + ", in data: " + date);

        setLabel();
        txtImporto.setText("");
        txtCausale.setText("");
    }

    @FXML
    private void checkMovements() {
        new Alert(Alert.AlertType.INFORMATION, check()).showAndWait();
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
        if (m != null) {
            if (txtImporto.getText().trim().length() != 0 && txtCausale.getText().trim().length() != 0 && selectedItem != null && date != null) {
                m.setCodice(selectedItem.toString());
                if ("versamento".equals(m.getCodice())) {
                    m.setSegnoOperazione('+');
                    System.out.println("segno: " + m.getSegnoOperazione());
                } else if ("prelievo".equals(m.getCodice()) || "bonifico ordinario".equals(m.getCodice()) || "bonifico istantaneo".equals(m.getCodice())) {
                    m.setSegnoOperazione('-');
                    System.out.println("segno: " + m.getSegnoOperazione());
                }
                if ("bonifico istantaneo".equals(m.getCodice())) {
                    m.setCosto(2.5); // 2,5 euro
                    System.out.println("costo: " + m.getCosto());
                }
                m.setDescrizione(txtCausale.getText());
                System.out.println(m.toString());
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

        for (int i = 0; i < 25; i++) {
            ibanBuilder.append(random.nextInt(10));
        }

        return ibanBuilder.toString();
    }

    private String check() {
        StringBuilder str = new StringBuilder();
        List<Movimento> operazioni = c.elencoOperazioni();
        str.append("- Elenco operazioni del conto: \n\n");
        for (Movimento movement : operazioni) {
            str.append("    ").append(movement.getNumeroProgressivo()).append(": \n")
                    .append("        ").append("Causale: ").append(movement.getDescrizione()).append("\n")
                    .append("        ").append("Data operazione: ").append(movement.getDataOperazione()).append("\n")
                    .append("        ").append("Importo: ").append(movement.getImporto()).append(" $").append("\n")
                    .append("        ").append("Costo operazione: ").append(movement.getCostoOperazione()).append(" $").append("\n")
                    .append("        ").append("Tipo movimento: ").append(movement.getTipoOperazione().getCodice()).append("\n")
                    .append("        ").append("Segno movimento: ").append(movement.getTipoOperazione().getSegnoOperazione()).append("\n");
        }
        str.append("\n").append("- Saldo totale del conto: ").append(c.calcolaSaldo()).append(" $").append("\n");

        return str.toString();
    }

    private void setLabel() throws Exception {
//        saldo = Double.toString(c.calcolaSaldo());
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
        m = new TipoMovimento();
    }
}
