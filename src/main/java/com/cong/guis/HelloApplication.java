package com.cong.guis;

import com.cong.guis.model.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class HelloApplication extends Application {

    private Stage stage;

    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private ObservableList<Person> personObservableList = FXCollections.observableArrayList();

    public HelloApplication() {
        personObservableList.add(new Person("张", "飞"));
        personObservableList.add(new Person("关", "羽"));
        personObservableList.add(new Person("刘", "备"));
        personObservableList.add(new Person("曹", "操"));
        personObservableList.add(new Person("孙", "权"));
        personObservableList.add(new Person("貂", "婵"));
        personObservableList.add(new Person("大", "乔"));
        personObservableList.add(new Person("小", "乔"));
        personObservableList.add(new Person("孙", "尚香"));
    }

    public ObservableList<Person> getPersonObservableList() {
        return personObservableList;
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PersonOverview.fxml"));
        AnchorPane borderPane = fxmlLoader.load();
        Scene scene = new Scene(borderPane, 600, 400);
        stage.setTitle("AddressApp");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("/images/app.png"))));
        PersonOverviewController controller = fxmlLoader.getController();
        controller.setApplication(this);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}