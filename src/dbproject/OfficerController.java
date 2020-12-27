/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject;

import classes.AppointmentOfficer;
import classes.Officer;
import classes.Patient;
import controldb.Database;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author aslis
 */
public class OfficerController implements Initializable {
    
    Database db;
    
    ObservableList<AppointmentOfficer> appointmentsList;
    ObservableList<Patient> patientList;
    
    Officer currentOfficer;
    
    @FXML
    private TableView<AppointmentOfficer> appTable = new TableView<>();
    
    @FXML
    private Button listDates; 
    @FXML
    private TableView<Patient> diseaseTable  = new TableView<>();
    @FXML
    private Button diaButton;
    @FXML
    private Button alzButton;
    @FXML
    private Button tensButton;
    @FXML
    private Button pyhButton;
    @FXML
    private Button obButton;
    @FXML
    private Button deleteUser;
    
    void setOfficer(Officer officer){
        this.currentOfficer = officer;
    }
    void setOfficersList(ArrayList<AppointmentOfficer> officersList){
        this.appointmentsList = FXCollections.observableArrayList(officersList);
    }
    void setPatientList(ArrayList<Patient> patientsList){
        this.patientList =FXCollections.observableArrayList(patientsList);
    }
      @FXML
    private void listDiaAction(ActionEvent event) {
        db =  new Database();
        db.baglan();
        setPatientList(db.currentDiseaseList(currentOfficer.getId(),"diyabet_users"));
        diseaseTable.setItems(patientList);
    }
      
    @FXML
    private void listAlzButton(ActionEvent event) {
        db =  new Database();
        db.baglan();
        setPatientList(db.currentDiseaseList(currentOfficer.getId(),"alzheimer_users"));
        diseaseTable.setItems(patientList);
    }

    @FXML
    private void listTensButton(ActionEvent event) {
        db =  new Database();
        db.baglan();
        setPatientList(db.currentDiseaseList(currentOfficer.getId(),"tansiyon_users"));
        diseaseTable.setItems(patientList);
    }

    @FXML
    private void listPhyButton(ActionEvent event) {
        db =  new Database();
        db.baglan();
        setPatientList(db.currentDiseaseList(currentOfficer.getId(),"bedensel_engel_users"));
        diseaseTable.setItems(patientList);
    }

    @FXML
    private void listObButton(ActionEvent event) {
        db =  new Database();
        db.baglan();
        setPatientList(db.currentDiseaseList(currentOfficer.getId(),"obezite_users"));
        diseaseTable.setItems(patientList);
    }

 
    @FXML
    private void listDateAction(ActionEvent event) {
        db =  new Database();
        db.baglan();
        setOfficersList(db.currentAppointments(currentOfficer.getId()));
        appTable.setItems(appointmentsList);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        db =  new Database();
        db.baglan();
        
        appTable.setEditable(true);
        
        TableColumn<AppointmentOfficer, String> nameCol = new TableColumn<AppointmentOfficer, String>("Ad");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("isim"));
        nameCol.setPrefWidth(90);
        
        TableColumn<AppointmentOfficer, String> surnameCol = new TableColumn<AppointmentOfficer, String>("Soyad");
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("soyisim"));
        surnameCol.setPrefWidth(90);
        
        TableColumn<AppointmentOfficer, String> roleCol = new TableColumn<AppointmentOfficer, String>("Adres");
        roleCol.setCellValueFactory(new PropertyValueFactory<>("adres"));
        roleCol.setPrefWidth(90);
        
        TableColumn<AppointmentOfficer, String> startCol= new TableColumn<AppointmentOfficer, String>("Başlangıç");
        startCol.setCellValueFactory(new PropertyValueFactory<>("start_hour"));
        startCol.setPrefWidth(90);
        
        TableColumn<AppointmentOfficer, String> endCol = new TableColumn<AppointmentOfficer, String>("Bitiş");
        endCol.setCellValueFactory(new PropertyValueFactory<>("finish_hour"));
        endCol.setPrefWidth(90);
        
        appTable.getColumns().addAll(nameCol, surnameCol, roleCol, startCol, endCol);  
        
        diseaseTable.setEditable(true);
          
        TableColumn<Patient, String> nameCol1 = new TableColumn<Patient, String>("TC Kimlik No");
        nameCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol1.setPrefWidth(160);
        TableColumn<Patient, String> roleCol1 = new TableColumn<Patient, String>("Ad");
        roleCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        roleCol1.setPrefWidth(160);
        TableColumn<Patient, String> surnameCol1 = new TableColumn<Patient, String>("Soyad");
        surnameCol1.setCellValueFactory(new PropertyValueFactory<>("surname"));
        surnameCol1.setPrefWidth(160);
        diseaseTable.getColumns().addAll(nameCol1, roleCol1,surnameCol1);
        }    

    @FXML
    private void deleteUserAction(ActionEvent event) {
        db.deleteUser(currentOfficer.getId(),true);
    }
    
}

