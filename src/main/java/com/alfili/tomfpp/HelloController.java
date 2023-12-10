package com.alfili.tomfpp;

import com.alfili.tomfpp.models.FreeProxyCz;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;


public class HelloController {

    private final ArrayList<String> parsingResultList = new ArrayList<>();

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

    @FXML
    protected void onProxyShopProxy6LinkClick() {
        try {
            Desktop.getDesktop().browse(URI.create("https://proxy6.net/?r=585747"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}