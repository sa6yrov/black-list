package kg.weare.blacklist.service.impl;

import kg.weare.blacklist.entity.BadGuyEntity;
import kg.weare.blacklist.model.BadGuyModel;
import kg.weare.blacklist.service.DocumentParseService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class DocumentParseServiceImpl implements DocumentParseService {

    @Override
    public List<BadGuyModel> getBadGuyList() throws IOException, ParserConfigurationException, SAXException, TransformerException {
        List<BadGuyModel> badGuyModelList = new ArrayList<>();

        URL urlForDocument = new URL(Objects.requireNonNull(getLinkToSvodKrDocument()));
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource inputSource = new InputSource(urlForDocument.openStream());
        inputSource.setEncoding("windows-1251");
        inputSource.setEncoding("windows-1252");
        org.w3c.dom.Document xmlDocument = documentBuilder.parse(inputSource);
        xmlDocument.getDocumentElement().normalize();

        DOMSource domSource = new DOMSource(xmlDocument);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);
        System.out.println("XML IN String format is: \n" + writer.toString());

        NodeList kyrgyzPhysicPersonsList = xmlDocument.getElementsByTagName("KyrgyzPhysicPerson");
        for (int i = 0; i < kyrgyzPhysicPersonsList.getLength(); i++) {
            Node kyrgyzPhysicNode = kyrgyzPhysicPersonsList.item(i);
            String surname = "";
            String name = "";
            String patronymic = "";
            if(kyrgyzPhysicNode.getNodeType() == Node.ELEMENT_NODE){
                org.w3c.dom.Element element = (org.w3c.dom.Element) kyrgyzPhysicNode;
                surname = element.getElementsByTagName("Surname").item(0).getTextContent();
                name = element.getElementsByTagName("Name").item(0).getTextContent();
                patronymic = element.getElementsByTagName("Patronomic").item(0) != null ?  element.getElementsByTagName("Patronomic").item(0).getTextContent() : "Я ЗДЕСЬ ПАТРО" ;
                StringBuilder fullNameBuilder = new StringBuilder();
                if(!surname.equals("") || !surname.equals("нет данных")) fullNameBuilder.append(surname);
                if(!name.equals("") || !name.equals("нет данных")) fullNameBuilder.append(" ").append(name);
                if(!patronymic.equals("") || !patronymic.equals("нет данных")) fullNameBuilder.append(" ").append(patronymic);
                badGuyModelList.add(BadGuyModel.builder()
                        .fullName(fullNameBuilder.toString())
                        .birthDate(element.getElementsByTagName("DataBirth").item(0).getTextContent())
                        .birthPlace(element.getElementsByTagName("PlaceBirth").item(0) != null ? element.getElementsByTagName("PlaceBirth").item(0).getTextContent() : "Я ЗДЕСЬ")
                        .inclusionReason(element.getElementsByTagName("BasicInclusion").item(0).getTextContent())
                        .badGuyLvl(element.getElementsByTagName("CategoryPerson").item(0).getTextContent())
                        .documentType("kg")
                        .build());
            }


        }
//        for (BadGuyModel badGuyModel : badGuyModelList) {
//            System.out.println(badGuyModel);
//        }
        return badGuyModelList;
    }

    public String getLinkToSvodKrDocument() {
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
