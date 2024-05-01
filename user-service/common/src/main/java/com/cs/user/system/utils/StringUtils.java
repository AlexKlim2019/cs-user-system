package com.cs.user.system.utils;

import static com.cs.user.system.constants.DomainConstants.MESSAGE_DELIMITER;

public class StringUtils {
    public static String concatenate(String ... strings) {
        return String.join(MESSAGE_DELIMITER, strings);
    }
}
