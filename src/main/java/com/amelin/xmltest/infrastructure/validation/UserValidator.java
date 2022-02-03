package com.amelin.xmltest.infrastructure.validation;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;

public class UserValidator {
    private static final String LANGUAGE;
    private static final String FILENAME;
    private static final String SCHEMA_NAME;

    static {
        LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        FILENAME = "users.xml";
        SCHEMA_NAME = "users.xsd";
    }

    public static void validate() {
        SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream fileInputStream = classLoader.getResourceAsStream(FILENAME);
             InputStream xsdInputStream = classLoader.getResourceAsStream(SCHEMA_NAME)){
            Source file = new StreamSource(fileInputStream);
            Source xsd = new StreamSource(xsdInputStream);

            Schema schema = factory.newSchema(xsd);
            Validator validator = schema.newValidator();

            validator.setErrorHandler(new UserErrorHandler());
            validator.validate(file);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
