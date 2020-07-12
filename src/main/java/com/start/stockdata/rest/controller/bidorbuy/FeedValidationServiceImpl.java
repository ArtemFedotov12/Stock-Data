package com.start.stockdata.rest.controller.bidorbuy;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.start.stockdata.rest.controller.bidorbuy.ValidationStatus.FAILURE;
import static com.start.stockdata.rest.controller.bidorbuy.ValidationStatus.SUCCESS;
import static java.io.File.createTempFile;
import static org.apache.commons.io.FileUtils.copyURLToFile;

@Service
public class FeedValidationServiceImpl implements FeedValidationService {

    private final BidOrBuyXmlValidationService bidOrBuyXmlValidationService;

    public FeedValidationServiceImpl(BidOrBuyXmlValidationService bidOrBuyXmlValidationService) {
        this.bidOrBuyXmlValidationService = bidOrBuyXmlValidationService;
    }

    @Override
    public FeedValidationResultDto getFeedValidationResult(String url, String feedType) {
        if (feedType.equals("bidorbuy")) {
            File file = validateWithUrl(url);
            List<AttributeValidationError> errors = bidOrBuyXmlValidationService.validateXml(file);

            ValidationStatus status = errors.isEmpty() ? SUCCESS : FAILURE;

            return FeedValidationResultDto.builder()
                    .url(url)
                    .validationStatus(status)
                    .validationErrors(errors)
                    .build();

        }
        return null;
    }

    public File validateWithUrl(String uri) {
        File file = null;
        try {
            file = createTempFile("tempFile", null);
            copyURLToFile(new URL(uri), file);
        } catch (IOException e) {
            System.out.println("BAD REQUEST");
        }
        return file;
    }

}
