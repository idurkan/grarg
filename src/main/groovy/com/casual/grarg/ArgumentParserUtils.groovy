package com.casual.grarg

class ArgumentParserUtils {

    public static String clearLeadingDashes(String s) {
        int dashIdx = s.lastIndexOf('-')

        if (dashIdx < 0) {
            throw new IllegalArgumentException("A flag must start with dash(es)")
        }

        return s[dashIdx+1 .. s.length()-1]
    }
}
