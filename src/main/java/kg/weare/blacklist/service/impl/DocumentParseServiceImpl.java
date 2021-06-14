package kg.weare.blacklist.service.impl;

import kg.weare.blacklist.model.BadGuyModel;
import kg.weare.blacklist.service.DocumentParseService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Service
public class DocumentParseServiceImpl implements DocumentParseService {




    @Override
    public List<BadGuyModel> getBadGuyList() throws IOException, ParserConfigurationException, SAXException, TransformerException {
        URL urlForDocument = new URL(Objects.requireNonNull(getLinkToSvodKrDocument()));

        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        org.w3c.dom.Document xmlDocument = documentBuilder.parse(new InputSource(urlForDocument.openStream()));


        return null;
    }

    public String getLinkToSvodKrDocument(){
        try {
            Document svodKrPage = Jsoup.connect("https://fiu.gov.kg/sked/9").get();
            Element divSkedView = svodKrPage.select("div.sked-view").first();
            Element paragraph = divSkedView.select("p").get(2);
            return paragraph.select("a").get(1).attr("href");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
