package com.alfili.tomfpp.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class FreeProxyCz {
    Document doc;
    public FreeProxyCz() {
        try {
            doc = Jsoup.connect("http://free-proxy.cz/ru/proxylist/country/all/https/ping/level1/1").get();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private int getPagesCount() {
        List<Element> pagination = doc.getElementsByClass("paginator").getFirst().children();
        return Integer.parseInt(pagination.get(pagination.size() - 2).text());
    }

    public ArrayList<String> startParsing() {
        List<Element> elementsList = Objects.requireNonNull(doc.getElementById("proxy_list")).getElementsByTag("tbody").getFirst().children();

        ArrayList<String> result = new ArrayList<>();

        for(Element el : elementsList) {
            String ipEncodedTextHtml = Objects.requireNonNull(Objects.requireNonNull(el.firstElementChild()).lastElementChild()).html();
            String ipEncodedTextResult = ipEncodedTextHtml.substring(ipEncodedTextHtml.indexOf("\"") + 1, ipEncodedTextHtml.lastIndexOf("\""));

            try {
                String ip = new String(Base64.getDecoder().decode(ipEncodedTextResult), StandardCharsets.UTF_8);
                String port = el.child(1).text();

                result.add(ip + ":" + port + "\n");
            }  catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println("Тут не IP:PORT! Пропускаю.");
            }
        }

        return result;
    }
}
