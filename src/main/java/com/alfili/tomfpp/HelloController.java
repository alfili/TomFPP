package com.alfili.tomfpp;

import com.alfili.tomfpp.models.FreeProxyCz;
import com.alfili.tomfpp.models.FreeProxyWorld;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;


public class HelloController {

    private final HashSet<String> parsingResultSet = new HashSet<>();

    @FXML
    private TextArea resultTextArea;

    @FXML
    private Button saveResultButton;

    @FXML
    protected void onParseButtonClick() {

        resultTextArea.clear();
        checkResultForSave();

        try {
            ArrayList<String> freeProxyCzList = new FreeProxyCz().startParsing();
            ArrayList<String> freeProxyWorldList = new FreeProxyWorld().startParsing();

            this.parsingResultSet.addAll(freeProxyCzList);
            this.parsingResultSet.addAll(freeProxyWorldList);

            for(String link : this.parsingResultSet) {
                resultTextArea.appendText(link);
            }

            checkResultForSave();

            throw new Exception("lol");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Что-то пошло не так");
            alert.setHeaderText("Что-то пошло не так");
            alert.setContentText(e.getMessage());
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }

    }

    private void checkResultForSave() {
        saveResultButton.setDisable(resultTextArea.getText().isEmpty());
    }

    @FXML
    protected void onProxyShopProxy6LinkClick() {
        try {
            Desktop.getDesktop().browse(URI.create("https://proxy6.net/?r=585747"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void onSaveButtonClick() {
        Window stage = resultTextArea.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        fileChooser.setTitle("Сохранить результат");
        fileChooser.getExtensionFilters()
                .addAll(
                        new FileChooser.ExtensionFilter("Текстовый файл (*.txt)", "*.txt")
                );
        fileChooser.setInitialFileName("result " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss")) + ".txt");

        try {
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                try {
                    Files.write(Paths.get(file.getPath()), resultTextArea.getText().getBytes());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Что-то пошло не так");
            alert.setHeaderText("Что-то пошло не так");
            alert.setContentText(e.getMessage());
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }

    }
}