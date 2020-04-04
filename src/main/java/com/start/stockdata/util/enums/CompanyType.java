package com.start.stockdata.util.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
@Getter
public enum CompanyType {

    PRODUCT("Product"),
    TECH("Tech"),
    DAIRY("Dairy"),
    IT("IT");

    private final String value;
    private static final Map<String, CompanyType> valueMap;
    private static final Set<String> valueSet;

    static {
        Set<String> valueTempSet = new HashSet<>();
        valueTempSet.add(PRODUCT.getValue());
        valueTempSet.add(TECH.getValue());
        valueTempSet.add(DAIRY.getValue());
        valueTempSet.add(IT.getValue());
        valueSet = Collections.unmodifiableSet(valueTempSet);

        Map<String, CompanyType> companyTypeMap = new HashMap<>();
        for (CompanyType companyType: CompanyType.values()) {
            companyTypeMap.put(companyType.getValue(),companyType);
        }
        valueMap = Collections.unmodifiableMap(companyTypeMap);
    }

    public static CompanyType getCompanyTypeByValue(String value) {
        return valueMap.get(value);
    }

    public static boolean contains(String value) {
        return valueSet.contains(value);
    }

    public static Map<String, CompanyType> getValueMap() {
        return valueMap;
    }

    public static Set<String> getValueSet() {
        return valueSet;
    }
}
