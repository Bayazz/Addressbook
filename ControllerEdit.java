package sample;


import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ControllerEdit {
    public TextField nameTextfield;
    public TextField numberTextfield;
    public Button okButton;
    public Button cancelButton;
    private TableView tableAddressBook;
    private Person selectedPerson;
    private Controller controller;




    public void setTableAdreesBook(TableView addressBook, Person selectedPerson, Controller controller)
    {
        this.tableAddressBook = addressBook;
        this.selectedPerson = selectedPerson;
        this.controller = controller;
    }

    public void okOnAction(ActionEvent actionEvent) {


            if (nameTextfield.getText().isEmpty() || numberTextfield.getText().isEmpty()){
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            showAlert(Alert.AlertType.CONFIRMATION,stage,
                    "Form Error", "Please fill in all required fields " );
        }else if (selectedPerson==null){
            Person person = new Person(nameTextfield.getText(), numberTextfield.getText());
            tableAddressBook.getItems().add(person);
            controller.showNumberOfContacts();

            /*try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("sample.fxml"));
                Controller controller = fxmlLoader.getController();
                controller.showNumberOfContacts();

            } catch ( Exception e){
                System.out.println(e.getMessage());

            }*/

            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.hide();
        } else{
            tableAddressBook.getItems().remove(selectedPerson);
            Person person = new Person(nameTextfield.getText(), numberTextfield.getText());
            tableAddressBook.getItems().add(person);
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.hide();
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    private void showAlert(Alert.AlertType alertType, Window owner,String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public void initialize(){




    }


}
