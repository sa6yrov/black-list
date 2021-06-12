package kg.weare.blacklist.service;

import kg.weare.blacklist.model.BadGuyModel;

import java.net.MalformedURLException;
import java.util.List;

public interface DocumentParseService {
    List<BadGuyModel> getBadGuyList() throws MalformedURLException;
}
