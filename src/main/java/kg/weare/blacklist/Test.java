package kg.weare.blacklist;

import kg.weare.blacklist.service.impl.DocumentParseServiceImpl;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {

        DocumentParseServiceImpl documentParseService = new DocumentParseServiceImpl();
//        System.out.println(documentParseService.getLinkToSvodKrDocument());
        documentParseService.getBadGuyList();
    }
}
