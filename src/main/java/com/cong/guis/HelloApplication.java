package com.cong.guis;

import com.cong.guis.model.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {

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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PersonOverview.fxml"));
        AnchorPane borderPane = fxmlLoader.load();
        Scene scene = new Scene(borderPane, 600, 400);
        stage.setTitle("AddressApp");
        stage.setScene(scene);
        PersonOverviewController controller = fxmlLoader.getController();
        controller.setApplication(this);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}