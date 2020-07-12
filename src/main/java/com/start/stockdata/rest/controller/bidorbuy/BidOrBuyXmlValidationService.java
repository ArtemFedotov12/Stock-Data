package com.start.stockdata.rest.controller.bidorbuy;


import java.io.File;
import java.util.*;

public interface BidOrBuyXmlValidationService {

    List<AttributeValidationError> validateXml(File xmlFile);

}