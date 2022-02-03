package com.amelin.xmltest.infrastructure;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ConsoleUserHandler extends DefaultHandler {
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String tagData = qName + " ";

        for (int i = 0; i < attributes.getLength(); i++) {
            tagData += " " + attributes.getQName(i) + "=" + attributes.getValue(i);
        }
        System.out.println(tagData);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.print(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.print(" " + qName);
    }

    @Override
    public void endDocument() {
        System.out.println("\nParsing ended");
    }
}
