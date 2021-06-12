package kg.weare.blacklist.service.impl;

import kg.weare.blacklist.model.BadGuyModel;
import kg.weare.blacklist.service.DocumentParseService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@Service
public class DocumentParseServiceImpl implements DocumentParseService {




    @Override
    public List<BadGuyModel> getBadGuyList() throws MalformedURLException {
        URL url = new URL(Objects.requireNonNull(getLinkToSvodKrDocument()));
        return null;
    }

    public String getLinkToSvodKrDocument(){
        try {
            Document svodKrPage = Jsoup.connect("https://fiu.gov.kg/sked/9").get();
            Elements body = svodKrPage.select("body").first().children();
//            System.out.println(body.html());
            Element divWrap = body.select("div.wrap").first();
            Element divContainer = divWrap.select("div.container").first();
            Element divWrapContent = divContainer.select("div").first();
            Element divSked = divWrapContent.select("div").first();
            System.out.println(divSked.html());
            Element paragraph = divSked.select("p").first();
//            System.out.println(paragraph.html());
            String linkForSvodKrDoc = paragraph.select("a").get(1).attr("href");

            return linkForSvodKrDoc;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
