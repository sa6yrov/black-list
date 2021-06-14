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
