package ru.spider.resume.helpers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupHelper {

    public static Document getDocument(String urlFormat, String userAgent, int timeout, String searchString, int page) throws IOException {
        String url = String.format(urlFormat, searchString, page + 1);
        Document doc;
        try
        {

            doc = Jsoup.connect(url).userAgent(userAgent).timeout(timeout).get();

            return doc;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static Document getDocument(String urlFormat, String userAgent, int timeout) throws IOException {
        return getDocument(urlFormat, userAgent, timeout, "", 0);
    }
}
