package com.start.stockdata.rest.controller.bidorbuy;


public interface FeedValidationService {

    FeedValidationResultDto getFeedValidationResult(String url, String feedType);

}