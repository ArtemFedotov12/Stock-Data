package com.start.stockdata.rest.controller.bidorbuy;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.start.stockdata.util.constants.UriPath.COMPANY_TYPE_PATH;

@RestController
@Api
public class TradefeedController {

    private final FeedValidationService feedValidationService;

    public TradefeedController(FeedValidationService feedValidationService) {
        this.feedValidationService = feedValidationService;
    }

    @GetMapping("/validation-results")
    public ResponseEntity<FeedValidationResultDto> bidorBuyValidation(@RequestParam("url") String url,
                                                                      @RequestParam("feedType") String feedType) {

        return new ResponseEntity<>(feedValidationService.getFeedValidationResult(url, feedType), HttpStatus.OK);
    }
}
