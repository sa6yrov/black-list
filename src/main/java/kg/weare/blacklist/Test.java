package kg.weare.blacklist;

import kg.weare.blacklist.service.impl.DocumentParseServiceImpl;

public class Test {
    public static void main(String[] args) {

        DocumentParseServiceImpl documentParseService = new DocumentParseServiceImpl();
        System.out.println(documentParseService.getLinkToSvodKrDocument());
    }
}
