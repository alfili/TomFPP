module com.alfili.tomfpp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires java.desktop;

    opens com.alfili.tomfpp to javafx.fxml;
    exports com.alfili.tomfpp;
}