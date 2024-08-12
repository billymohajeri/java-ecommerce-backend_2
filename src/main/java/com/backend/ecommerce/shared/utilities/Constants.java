package com.backend.ecommerce.shared.utilities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public final static String SERVER_DATE_FORMAT = "dd-MM-yyyy";
    public final static String LOCAL_DATE_FORMAT = "yyyy-MM-dd";
    //Minimum eight characters, at least one letter and one number
    public final static String PASSWORD_REGEX = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}";
    public final static String PHONE_REGEX = "\\+\\d{5,}";
}
