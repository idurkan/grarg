package com.casual.grarg

class ArgumentParserUtils {

    public static String clearLeadingDashes(String s) {
        int dashIdx = s.lastIndexOf('-')

        if (dashIdx < 0) {
            throw new IllegalArgumentException("A flag must start with dash(es)")
        }

        return s[dashIdx+1 .. s.length()-1]
    }

    /**
     * Split the input into a series of tokens.  Tokens are separated by spaces, unless double
     * or single quotes appear.  If a single quote appears, all characters until the
     * next single quote are considered part of a single token, including whitespace.  Likewise
     * for double-quotes.
     * @param data
     * @return
     */
    public static List<String> tokenize(String data) {
        List<String> tokens = []

        def curToken = new StringBuilder()
        Character quoteChar = null
        String quoteChars = /'"/

        for (int i = 0; i < data.length(); ++i) {
            Character cur = data[i]

            if (quoteChar) {
                // non-null quote char: collect characters into token until same quote char seen again.
                if (data[i] != quoteChar) {
                    curToken.append(cur)
                } else {
                    // saw same quote character again
                    tokens << curToken.toString()
                    curToken.setLength(0) // reset the StringBuilder
                    quoteChar = null
                }
            } else {
                if (cur.isWhitespace()) {
                    // whitespace: end current token if chars have been collected
                    if (curToken.length() > 0) {
                        tokens << curToken.toString()
                        curToken.setLength(0)
                    }
                } else if (quoteChars.contains((String)cur)) {
                    // quote: end current token if chars have been collected and go into quote mode.
                    if (curToken.length() > 0) {
                        tokens << curToken.toString()
                        curToken.setLength(0)
                    }

                    quoteChar = cur
                } else {
                    curToken.append(cur)
                }
            }
        }

        // leftover chars in curToken represent another token
        if (curToken.length() > 0) {
            tokens << curToken.toString()
        }

        return tokens
    }

}
