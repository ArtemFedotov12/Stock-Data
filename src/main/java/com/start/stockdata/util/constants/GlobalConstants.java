package com.start.stockdata.util.constants;

import io.jsonwebtoken.SignatureAlgorithm;

public class GlobalConstants {
    public static final String HEADER_STRING = "Authorization";
    public static final long JWT_ACCESS_TOKEN_VALIDITY_MILLISECONDS = 18_000_000;// 5 hours
    public static final String SIGNING_KEY = "dummy_secret_key1";
    public static final String SIGNATURE_ALGORITHM = "HS256";
    public static final String TOKEN_PREFIX_WITH_SPACE = "Bearer ";
    public static final String AUTHORITIES_KEY = "scopes";
}