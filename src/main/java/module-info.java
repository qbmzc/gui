module com.cong.guis {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.cong.guis to javafx.fxml;
    exports com.cong.guis;
}