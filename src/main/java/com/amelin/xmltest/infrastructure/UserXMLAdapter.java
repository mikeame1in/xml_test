package com.amelin.xmltest.infrastructure;

import com.amelin.xmltest.infrastructure.validation.UserErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;

public class UserXMLAdapter {
    private static final String FILENAME;
    private static final Source SOURCE;
    private static final InputSource INPUT_SOURCE;

    static {
        FILENAME = "users.xml";

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(FILENAME)) {
            SOURCE = new StreamSource(inputStream);
            INPUT_SOURCE = new InputSource(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public void getAll() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();

            reader.setContentHandler(new ConsoleUserHandler());
            reader.setErrorHandler(new UserErrorHandler());

            reader.parse(INPUT_SOURCE);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
