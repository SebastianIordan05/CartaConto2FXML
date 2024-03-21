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

    private Intestatario intestatario;
    private Conto conto;
    private TipoMovimento movimento;

    private Object selectedItem;
    private LocalDate date;

    @FXML
    private ChoiceBox lstTipoMovimento;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnBackToRegister.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void movimento() {
        if (movimento != null) {
            if (txtImporto.getText().trim().length() != 0 && txtCausale.getText().trim().length() != 0 && selectedItem != null && date != null) {
                movimento.setCodice(selectedItem.toString());
                if ("versalmento".equals(movimento.getCodice())) {
                    movimento.setSegnoOperazione("+");
                } else if ("prelievo".equals(movimento.getCodice()) || "bonifico ordinario".equals(movimento.getCodice()) || "bonifico istantaneo".equals(movimento.getCodice())) {
                    movimento.setSegnoOperazione("-");
                }
                if ("bonifico istantaneo".equals(movimento.getCodice())) {
                    movimento.setCosto(2.5); // 2,5 euro 
                }
                movimento.setDescrizione(txtCausale.getText());
                System.out.println(movimento.toString());
            } else {
                Alert badArgument = new Alert(Alert.AlertType.ERROR, "Wrong importo or causale!");
                badArgument.showAndWait();
            }
        } else {
            Alert nullMovimento = new Alert(Alert.AlertType.ERROR, "Movimento is null!");
            nullMovimento.showAndWait();
            Platform.exit();
        }
    }

    private void initializeChoiceBox() {
        for (int i = 1; i < 5; i++) {
            switch (i) {
                case 1 ->
                    lstTipoMovimento.getItems().add("versalmento");
                case 2 ->
                    lstTipoMovimento.getItems().add("prelievo");
                case 3 ->
                    lstTipoMovimento.getItems().add("bonifico ordinario");
                case 4 ->
                    lstTipoMovimento.getItems().add("bonifico istantaneo");
            }
        }
    }

    public void setIntestatario(Intestatario intestatario) {
        this.intestatario = intestatario;
//        System.out.println("Intestatario impostato nel controller NewMovementController: " + intestatario);

        String code = intestatario.getNome();
        conto = App.conti.get(code);

        if (conto == null) {
            conto = new Conto(generateIBAN(), intestatario);
            App.conti.put(intestatario.getNome(), conto);
            
            Alert newContoCreated = new Alert(Alert.AlertType.INFORMATION, "New conto created for " + intestatario.getNome() +
                    " with the iban: " + conto.getCodiceIBAN() + "!");
            newContoCreated.showAndWait();
        }

        lblIban.setText(conto.getCodiceIBAN());
    }

    private String generateIBAN() {
        StringBuilder ibanBuilder = new StringBuilder("IT");
        Random random = new Random();

        for (int i = 0; i < 25; i++) {
            ibanBuilder.append(random.nextInt(10));
        }

        return ibanBuilder.toString();
    }

    private String check() throws Exception {
        StringBuilder str = new StringBuilder();
        List<Movimento> operazioni = conto.elencoOperazioni();
        str.append("Elenco operazioni del conto: \n");
        for (Movimento movimento : operazioni) {
            str.append(movimento.getNumeroProgressivo()).append(": ").append(movimento.getDescrizione()).append(", Data operazione: ").append(movimento.getDataOperazione()).append(", Importo: ").append(movimento.getImporto()).append(", Costo operazione: ").append(movimento.getCostoOperazione());
            str.append("\n");
        }
        str.append("Saldo totale del conto: ").append(conto.calcolaSaldo()).append("\n");
        
        return str.toString();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeChoiceBox();

        movimento = new TipoMovimento();
        lstTipoMovimento.setValue("tipo movimento");

        lstTipoMovimento.setOnAction((event) -> {
            selectedItem = lstTipoMovimento.getSelectionModel().getSelectedItem();
            System.out.println("selectedItem: " + selectedItem);
        });
        
        datePicker.setOnAction((event) -> {
            date = datePicker.getValue();
        });

        btnEffettuaBonifico.setOnAction((event) -> {
            movimento();
            conto.registraOperazione(movimento, Integer.parseInt(txtImporto.getText()), movimento.getDescrizione(), date);
            System.out.println(conto.toString());

            conto.setSaldo(Integer.parseInt(txtImporto.getText()));
//            lblSaldo.setText(conto.getSaldo());
            System.out.println("saldo: " + conto.getSaldo() + ", IBAN: " + conto.getCodiceIBAN() + ", nome: " + intestatario.getNome() + ", in data: " + date);

            txtImporto.setText("");
            txtCausale.setText("");
        });
        
        btnCheckMovements.setOnAction((event) -> {
            Alert movements;
            try {
                movements = new Alert(Alert.AlertType.INFORMATION, check());
                movements.showAndWait();
            } catch (Exception ex) {}
        });
    }
}
