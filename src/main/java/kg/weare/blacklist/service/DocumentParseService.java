package kg.weare.blacklist.service;

import kg.weare.blacklist.model.BadGuyModel;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface DocumentParseService {
    List<BadGuyModel> getBadGuyList() throws IOException, ParserConfigurationException, SAXException, TransformerException;
}
