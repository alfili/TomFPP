package com.alfili.tomfpp.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FreeProxyWorld {

    Document doc;

    public FreeProxyWorld() {
        try {
            doc = Jsoup.connect("https://www.freeproxy.world/?type=https&anonymity=4&country=&speed=&port=&page=1").get();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> startParsing() {

        ArrayList<String> result = new ArrayList<>();

        List<Element> elementsList = Objects.requireNonNull(doc.getElementsByClass("layui-table").getFirst()).getElementsByTag("tbody").getFirst().children();

        for (Element el : elementsList) {
            if(!el.text().equals(" ")) {
                try {
                    String ip = el.getElementsByTag("td").get(0).text();;
                    String port = el.child(1).text();

                    result.add(ip + ":" + port + "\n");
                } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                    System.out.println("Тут не IP:PORT! Пропускаю.");
                }
            }
        }


        return result;
    }

}
