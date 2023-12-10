package com.alfili.tomfpp;

import com.alfili.tomfpp.models.FreeProxyCz;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class HelloController {

    private final ArrayList<String> parsingResultList = new ArrayList<>();

    @FXML
    private Label welcomeText;

    @FXML
    private TextArea resultTextArea;

    @FXML
    protected void onParseButtonClick() {

        ArrayList<String> freeProxyCzList = new FreeProxyCz().startParsing();

        this.parsingResultList.addAll(freeProxyCzList);

        for(String link : this.parsingResultList) {
            resultTextArea.appendText(link);
        }
    }
}