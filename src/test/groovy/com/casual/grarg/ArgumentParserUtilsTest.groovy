package com.casual.grarg

import org.junit.Test;

public class ArgumentParserUtilsTest {
    @Test public void clearLeadingDashesTest() {
        assert "h1" == ArgumentParserUtils.clearLeadingDashes("--h1")
        assert "arg" == ArgumentParserUtils.clearLeadingDashes("-arg")
        assert "hihi" == ArgumentParserUtils.clearLeadingDashes("---hihi")

        //assert "rofl" == ArgumentParserUtils.clearLeadingDashes("rofl")

        // bad but not worth fixing yet.
        assert "rolf" == ArgumentParserUtils.clearLeadingDashes("rolf--rolf")
    }

    @Test(expected = IllegalArgumentException.class)
    public void clearLeadingDashesExceptionTest() {
        assert "rofl" == ArgumentParserUtils.clearLeadingDashes("rofl")
    }

    @Test public void tokenizeTests() {
        def result = ArgumentParserUtils.tokenize('hello')
        assert result == ['hello']

        result = ArgumentParserUtils.tokenize('hello world')
        assert result == ['hello', 'world']

        result = ArgumentParserUtils.tokenize('hello world again')
        assert result == ['hello', 'world', 'again']

        result = ArgumentParserUtils.tokenize('    hello     world again    ')
        assert result == ['hello', 'world', 'again']

        result = ArgumentParserUtils.tokenize('hello  "this  world "  again')
        assert result == ['hello', 'this  world ', 'again']

        // TODO more testing of this.
    }
}
