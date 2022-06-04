package com.cong.guis;

import com.cong.guis.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, String> firstNameColum;
    @FXML
    private TableColumn<Person, String> lastNameColum;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;

    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    private HelloApplication application;

    public PersonOverviewController() {
    }


    @FXML
    private void initialize() {
        firstNameColum.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColum.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        showPersonDetail(null);
        personTable.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showPersonDetail(newValue));
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex>=0){
            personTable.getItems().remove(selectedIndex);
        }else {
            //忽略错误
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("至少选择一个");
            alert.setContentText("不能删除没有选择的东西");
            alert.showAndWait();
        }

    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = application.showPersonEditDialog(tempPerson);
        if (okClicked) {
            application.getPersonObservableList().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = application.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetail(selectedPerson);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
            // Nothing selected.
        }
    }

    public void setApplication(HelloApplication application) {
        this.application = application;
        personTable.setItems(application.getPersonObservableList());
    }


    private void showPersonDetail(Person person) {
        if (null != person) {
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(person.getBirthday().format(DateTimeFormatter.ISO_LOCAL_DATE));
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }
}