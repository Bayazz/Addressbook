package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Controller {

    public TextField searchTextfield;
    public Button searchButton;
    public Button addButton;
    public Button changeButton;
    public Button deleteButton;
    public Label numberOfContacts;
    public TableView tableAddressBook;
    public TableColumn<Person, String> columnName;
    public TableColumn<Person, String> columnNumber;
    private Person selectedPerson;

    private ControllerEdit controllerEdit;
    public void setControllerEdit (ControllerEdit newControllerEdir){
        this.controllerEdit = newControllerEdir;
    }


    public void initialize(){
        columnName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        columnNumber.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
        tableAddressBook.setItems(personObservableList);

    }

    public void showNumberOfContacts(){
        int count = 0;

        for (Person person: personObservableList){
            count =count + 1;
        }
        numberOfContacts.setText("Number of contacts: "+ String.valueOf(count));

    }


    public void showDialog(ActionEvent actionEvent) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("edit.fxml"));
            Parent root = fxmlLoader.load();

            ControllerEdit controllerEdit = fxmlLoader.getController();
            controllerEdit.setTableAdreesBook(tableAddressBook,selectedPerson, this);

            Stage stage = new Stage();

            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    ObservableList<Person> personObservableList = FXCollections.observableArrayList();


    public void deleteAction(ActionEvent actionEvent) {
        Person selectedPerson = (Person) tableAddressBook.getSelectionModel().getSelectedItem();
        tableAddressBook.getItems().remove(selectedPerson);
        showNumberOfContacts();
    }

    public void changeAction(ActionEvent actionEvent) {
        try {
            selectedPerson = (Person) tableAddressBook.getSelectionModel().getSelectedItem();
            if (!selectedPerson.getName().isEmpty()){
                showDialog(actionEvent);

            }

        } catch (Exception e){

        }



    }

    public void searchAction(ActionEvent actionEvent) {

        for (Person person: personObservableList){

            if (person.getName().equalsIgnoreCase(searchTextfield.getText())){

                Node source = (Node) actionEvent.getSource();
                Stage stage = (Stage) source.getScene().getWindow();

                showAlert(Alert.AlertType.CONFIRMATION, stage,"Search result","Success! Found  a match!");
            }else{
                Node source = (Node) actionEvent.getSource();
                Stage stage = (Stage) source.getScene().getWindow();

                showAlert(Alert.AlertType.CONFIRMATION, stage,"Search result","No match found");
            }

        }


    }
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


}
