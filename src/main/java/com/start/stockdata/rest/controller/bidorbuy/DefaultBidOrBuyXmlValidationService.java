package com.start.stockdata.rest.controller.bidorbuy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DefaultBidOrBuyXmlValidationService implements BidOrBuyXmlValidationService {

    public List<AttributeValidationError> validateXml(File xmlFile) {
        List<AttributeValidationError> errors = new ArrayList<>();
        final List<SAXParseException> saxParserExceptions = new ArrayList<>();

        try {
            File xsdFile = ResourceUtils.getFile("classpath:xsd/bidorbuy.xsd");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            setDocumentBuilderErrorHandler(saxParserExceptions, documentBuilder);

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            setValidatorErrorHandler(saxParserExceptions, validator);

            //get all exceptions according to xsd scheme
            validateXmlFile(saxParserExceptions, xmlFile, validator);
            //xml file must start and end within the same tag
            checkDocumentStructure(saxParserExceptions, errors);
            //check if all xml tags are closed
            Document document = checkTags(documentBuilder, xmlFile, errors);
            if (document == null) {
                return errors;
            }
            checkSchemeVersionTag(saxParserExceptions, errors);
            checkExportCreatedTag(saxParserExceptions, errors);
            checkOccurrenceFrequency(saxParserExceptions, errors);
            checkInitialXmlTag(saxParserExceptions, errors);
            checkDocumentFormatting(saxParserExceptions, errors);
            checkIfFileIsEmpty(saxParserExceptions, errors);
            checkMandatoryTags(saxParserExceptions, errors);

            checkUserIdTag(saxParserExceptions, errors, document);


            System.out.println();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            //TO DO throw custom exception
            System.out.println();
        }
        return errors;
    }

    private void checkUserIdTag(List<SAXParseException> saxParserExceptions, List<AttributeValidationError> errors, Document document) {
        document.getDocumentElement().normalize();
        System.out.println();
    }

    private void checkMandatoryTags(List<SAXParseException> saxParserExceptions, List<AttributeValidationError> errors) {
        List<SAXParseException> mandatoryTagExceptions = saxParserExceptions
                .stream()
                .filter(item -> item.getMessage().matches("cvc-complex-type.2.4.b: The content of element '(.*?)' is not complete. One of '(.*?)' is expected."))
                .collect(Collectors.toList());

        if (!mandatoryTagExceptions.isEmpty()) {
            mandatoryTagExceptions.forEach(exception ->  {
                AttributeValidationError validationDto = new AttributeValidationError();
                String tagName = StringUtils.substringBetween(exception.getMessage(), "The content of element '", "'");
                String requiredTagName = StringUtils.substringBetween(exception.getMessage(), "One of '{", "}'");
                validationDto.setAttribute(tagName);
                validationDto.setCurrentValue(null);
                validationDto.setErrorText("The content of '" + tagName + "' tag is not complete");
                validationDto.setLine(exception.getLineNumber());
                validationDto.setRecommendation("Add '" + requiredTagName + "' tag inside '" + tagName + "' tag");
                errors.add(validationDto);
            });

        }

        System.out.println();
    }

    private void checkIfFileIsEmpty(List<SAXParseException> saxParserExceptions, List<AttributeValidationError> errors) {
        Optional<SAXParseException> emptyFileException = saxParserExceptions
                .stream()
                .filter(item -> item.getMessage().matches("Premature end of file."))
                .findFirst();
        if (emptyFileException.isPresent()) {
            SAXParseException saxParseException = emptyFileException.get();
            AttributeValidationError attributeValidationError = new AttributeValidationError();
            attributeValidationError.setAttribute(null);
            attributeValidationError.setCurrentValue(null);
            attributeValidationError.setErrorText("Premature end of file");
            attributeValidationError.setLine(saxParseException.getLineNumber());
            attributeValidationError.setRecommendation("XML document must start with 'ROOT' tag");
            errors.add(attributeValidationError);
        }
    }

    private void checkDocumentFormatting(List<SAXParseException> saxParserExceptions, List<AttributeValidationError> errors) {
        Optional<SAXParseException> documentFormattingException = saxParserExceptions
                .stream()
                .filter(item -> item.getMessage().matches("The markup in the document following the root element must be well-formed."))
                .findFirst();
        if (documentFormattingException.isPresent()) {
            SAXParseException saxParseException = documentFormattingException.get();
            AttributeValidationError attributeValidationError = new AttributeValidationError();
            attributeValidationError.setAttribute(null);
            attributeValidationError.setCurrentValue(null);
            attributeValidationError.setErrorText("XML document is invalid");
            attributeValidationError.setLine(saxParseException.getLineNumber());
            attributeValidationError.setRecommendation("The markup in the document following the root element must be well-formed. All tags must be inside main 'ROOT' tag");
            errors.add(attributeValidationError);
        }
    }

    private void checkInitialXmlTag(List<SAXParseException> saxParserExceptions, List<AttributeValidationError> errors) {
        Optional<SAXParseException> initialXmlTagException = saxParserExceptions
                .stream()
                .filter(item -> item.getMessage().matches("cvc-elt.1: Cannot find the declaration of element '(.*?)'."))
                .findFirst();
        if (initialXmlTagException.isPresent()) {
            SAXParseException saxParseException = initialXmlTagException.get();
            AttributeValidationError attributeValidationError = new AttributeValidationError();
            String tagName = StringUtils.substringBetween(saxParseException.getMessage(), "cvc-elt.1: Cannot find the declaration of element '", "'");
            attributeValidationError.setAttribute(tagName);
            attributeValidationError.setCurrentValue(null);
            attributeValidationError.setErrorText("XML document starts with '" + tagName + "' tag");
            attributeValidationError.setLine(saxParseException.getLineNumber());
            attributeValidationError.setRecommendation("Initial tag must be 'ROOT'");
            errors.add(attributeValidationError);
        }

        System.out.println();
    }

    private void checkOccurrenceFrequency(List<SAXParseException> saxParserExceptions, List<AttributeValidationError> errors) {
        List<SAXParseException> occurrenceFrequencyExceptions = saxParserExceptions
                .stream()
                .filter(item -> item.getMessage().matches("cvc-complex-type.2.4.d: Invalid content was found starting with element '(.*?)'. No child element is expected at this point."))
                .collect(Collectors.toList());
        if (!occurrenceFrequencyExceptions.isEmpty()) {
            occurrenceFrequencyExceptions.forEach(exception ->  {
                AttributeValidationError validationDto = new AttributeValidationError();
                String tagName = StringUtils.substringBetween(exception.getMessage(), "cvc-complex-type.2.4.d: Invalid content was found starting with element '", "'");
                validationDto.setAttribute(tagName);
                validationDto.setCurrentValue(null);
                validationDto.setErrorText("No tag '" + tagName + "' is expected at this point");
                validationDto.setLine(exception.getLineNumber());
                validationDto.setRecommendation("Remove '" + tagName + "' tag");
                errors.add(validationDto);
            });

        }
    }

    private void checkExportCreatedTag(List<SAXParseException> saxParserExceptions, List<AttributeValidationError> errors) {
        Optional<SAXParseException> exportCreatedTagException = saxParserExceptions
                .stream()
                .filter(item -> item.getMessage().matches("cvc-pattern-valid: Value '(.*?)' is not facet-valid with respect to pattern '(.*?)' for type '#AnonType_ExportCreatedVersionROOT'."))
                .findFirst();
        if (exportCreatedTagException.isPresent()) {
            SAXParseException saxParseException = exportCreatedTagException.get();
            AttributeValidationError attributeValidationError = new AttributeValidationError();
            attributeValidationError.setAttribute("ExportCreated");
            attributeValidationError.setCurrentValue(StringUtils.substringBetween(saxParseException.getMessage(), "cvc-pattern-valid: Value '", "'"));
            attributeValidationError.setErrorText("'ExportCreated' tag is invalid");
            attributeValidationError.setLine(saxParseException.getLineNumber());
            attributeValidationError.setRecommendation("'ExportCreated' tag should include time-zone information. Example: '2002-05-30T09:30:10-06:00'");
            errors.add(attributeValidationError);
        }
        System.out.println();
    }

    private void checkSchemeVersionTag(List<SAXParseException> saxExceptions, List<AttributeValidationError> errors) {
        Optional<SAXParseException> schemeVersionTagException = saxExceptions
                .stream()
                .filter(item -> item.getMessage().matches("cvc-enumeration-valid: Value '(.*?)' is not facet-valid with respect to enumeration '(.*?)'. It must be a value from the enumeration."))
                .findFirst();
        if (schemeVersionTagException.isPresent()) {
            SAXParseException saxParseException = schemeVersionTagException.get();
            AttributeValidationError attributeValidationError = new AttributeValidationError();
            String currentValue = StringUtils.substringBetween(saxParseException.getMessage(), "cvc-enumeration-valid: Value '", "'");
            String expectedValue = StringUtils.substringBetween(saxParseException.getMessage(), "'[", "]'");
            if (expectedValue.equals("1.0")) {
                attributeValidationError.setAttribute("SchemaVersion");
                attributeValidationError.setErrorText("'SchemeVersion' tag is invalid");
                attributeValidationError.setRecommendation("'SchemeVersion' tag must be '1.0'");
                attributeValidationError.setCurrentValue(currentValue);
                attributeValidationError.setLine(saxParseException.getLineNumber());
                errors.add(attributeValidationError);
            }
        }
    }

    private Document checkTags(DocumentBuilder documentBuilder, File xmlFile, List<AttributeValidationError> errors) throws IOException {
        final List<SAXException> saxExceptions = new ArrayList<>();
        Document document = null;
        try {
            document = documentBuilder.parse(xmlFile);
        } catch (SAXException e) {
            saxExceptions.add(e);
        }

        Optional<SAXException> notClosedTags = saxExceptions
                .stream()
                .filter(item -> item.getMessage().matches("The element type \"(.*?)\" must be terminated by the matching end-tag \"(.*?)\"."))
                .findFirst();
        if (notClosedTags.isPresent()) {
            SAXException saxException = notClosedTags.get();
            AttributeValidationError validationDto = new AttributeValidationError();
            String tagName = StringUtils.substringBetween(saxException.getMessage(), "The element type \"", "\"");
            validationDto.setAttribute(tagName);
            validationDto.setCurrentValue(null);
            validationDto.setErrorText("Tag \"" + tagName + "\" is not closed");
            errors.add(validationDto);
        }

        return document;
    }


    private void setDocumentBuilderErrorHandler(List<SAXParseException> exceptions, DocumentBuilder dBuilder) {
        dBuilder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                exceptions.add(exception);
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                exceptions.add(exception);
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                exceptions.add(exception);
            }
        });
    }

    private void setValidatorErrorHandler(List<SAXParseException> exceptions, Validator validator) {
        validator.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) {
                exceptions.add(exception);
            }

            @Override
            public void fatalError(SAXParseException exception) {
                exceptions.add(exception);
            }

            @Override
            public void error(SAXParseException exception) {
                exceptions.add(exception);
            }
        });
    }

    private void validateXmlFile(List<SAXParseException> saxParserExceptions, File xmlFile, Validator validator) throws SAXException, IOException {
        try {
            validator.validate(new StreamSource(xmlFile));
        } catch (SAXParseException e) {
            saxParserExceptions.add(e);
        }
    }

    private void checkDocumentStructure(List<SAXParseException> exceptions, List<AttributeValidationError> errors) {
        Optional<SAXParseException> documentStructureException = exceptions
                .stream()
                .filter(item -> item.getMessage().equals("XML document structures must start and end within the same entity."))
                .findFirst();

        if (documentStructureException.isPresent()) {
            SAXParseException saxParseException = documentStructureException.get();
            AttributeValidationError attributeValidationError = new AttributeValidationError();
            attributeValidationError.setAttribute(null);
            attributeValidationError.setCurrentValue(null);
            attributeValidationError.setErrorText("XML document doesn't end with the same tag");
            attributeValidationError.setRecommendation("XML document must start and end within the same tag");
            attributeValidationError.setLine(saxParseException.getLineNumber());
            errors.add(attributeValidationError);
        }
    }

}
