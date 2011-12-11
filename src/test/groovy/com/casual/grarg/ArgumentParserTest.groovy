package com.casual.grarg

import org.junit.Test
import org.junit.Before;

public class ArgumentParserTest {

    ArgumentParser testParser

    @Before public void setup() {
        testParser = new ArgumentParser('Test Parser', 'foobar')
    }

    @Test public void constructorTest() {
        assert testParser.description == 'Test Parser'
        assert testParser.programName == 'foobar'
        assert testParser.usage == null
        assert testParser.epilogue == null
        assert testParser.addHelp
    }

    @Test public void addPositionalArgumentTest() {
        def posArg1 = testParser.addPositionalArgument([:], 'arg1')

        assert posArg1.name == 'arg1'
        assert posArg1.action == ActionType.STORE
        assert posArg1.nargs == '1'
        assert posArg1.defaultValue == null
        assert posArg1.choices == []
        assert posArg1.help == null
        assert posArg1.metavar == 'arg1'

        def posArg2 = testParser.addPositionalArgument(action:ActionType.APPEND, nargs:'3',
                'arg2')

        assert posArg2.name == 'arg2'
        assert posArg2.action == ActionType.APPEND
        assert posArg2.nargs == '3'
        assert posArg2.defaultValue == null
        assert posArg2.choices == []
        assert posArg2.help == null
        assert posArg2.metavar == 'arg2'

        def posArg3 = testParser.addPositionalArgument(action:ActionType.APPEND, nargs:'*',
            defaultValue:['hello'], choices: ['hello', 'world'], help:'helpme!', metavar:'S', 'arg3')

        assert posArg3.name == 'arg3'
        assert posArg3.action == ActionType.APPEND
        assert posArg3.nargs == '*'

        assert posArg3.defaultValue == ['hello']
        assert posArg3.choices == ['hello', 'world']
        assert posArg3.help == 'helpme!'
        assert posArg3.metavar == 'S'

    }

    @Test public void addOptionArgumentTest() {
        def optArg1 = testParser.addOptionArgument([:], 'arg1')

        assert optArg1.name == 'arg1'
        assert optArg1.flags == ['--arg1']
        assert optArg1.action == ActionType.STORE
        assert optArg1.nargs == '1'
        assert optArg1.defaultValue == null
        assert optArg1.choices == []
        assert optArg1.help == null
        assert optArg1.metavar == 'arg1'

        assert !optArg1.required

        def optArg2 = testParser.addOptionArgument(action:ActionType.APPEND, nargs:'3',
                'arg2')

        assert optArg2.name == 'arg2'
        assert optArg2.flags == ['--arg2']
        assert optArg2.action == ActionType.APPEND
        assert optArg2.nargs == '3'
        assert optArg2.defaultValue == null
        assert optArg2.choices == []
        assert optArg2.help == null
        assert optArg2.metavar == 'arg2'

        assert !optArg2.required

        def optArg3 = testParser.addOptionArgument(action:ActionType.APPEND, nargs:'*',
            defaultValue:['hello'], choices: ['hello', 'world'], help:'helpme!', metavar:'S', 'arg3')

        assert optArg3.name == 'arg3'
        assert optArg3.flags == ['--arg3']
        assert optArg3.action == ActionType.APPEND
        assert optArg3.nargs == '*'

        assert optArg3.defaultValue == ['hello']
        assert optArg3.choices == ['hello', 'world']
        assert optArg3.help == 'helpme!'
        assert optArg3.metavar == 'S'

        assert !optArg3.required

        def optArg4 = testParser.addOptionArgument(action:ActionType.APPEND, nargs:'3',
                required:true, 'arg4', '-T', '--a2')

        assert optArg4.name == 'arg4'
        assert optArg4.flags == ['-T', '--a2']
        assert optArg4.action == ActionType.APPEND
        assert optArg4.nargs == '3'
        assert optArg4.defaultValue == null
        assert optArg4.choices == []
        assert optArg4.help == null
        assert optArg4.metavar == 'arg4'

        assert optArg4.required

    }

    @Test public void addArgumentGroupTest() {
        def group1 = testParser.addArgumentGroup('Test Title', 'Test Description')
        assert group1.title == 'Test Title'
        assert group1.description == 'Test Description'
        assert group1.parent.is(testParser)

        def group2 = testParser.addArgumentGroup('Test Title')
        assert group2.title == 'Test Title'
        assert group2.description == null
        assert group2.parent.is(testParser)
    }

    @Test public void parseArgumentsTest1() {
        def parser = new ArgumentParser("Test Parser 1", "tester")

        def group1 = parser.addArgumentGroup('Group 1', 'Group 1')

        group1.addPositionalArgument(action:'store', nargs: '2', 'G1A1')
        group1.addPositionalArgument(action:'store', nargs: '3', 'G1A2')

        def group2 = parser.addArgumentGroup('Group 2', 'Group 2')

        group2.addPositionalArgument(action:'store', nargs: '2', 'G2A1')
        group2.addPositionalArgument(action:'store', nargs: '1', 'G2A2')

        String[] data = ['10', '11', '12', '13', '14', '15', '16', '17']
        def result = parser.parseArguments(data)

        assert result['G1A1'] == ['10', '11']
        assert result['G1A2'] == ['12', '13', '14']
        assert result['G2A1'] == ['15', '16']
        assert result['G2A2'] == ['17']
    }

    @Test public void parseArgumentsTest2() {
        testParser.addPositionalArgument(action: 'store', nargs: '2', 'two-ints')
        testParser.addPositionalArgument(action: 'store', nargs: '4', 'four-strings')
        testParser.addOptionArgument('opt1', '--opt1')
        testParser.addOptionArgument(defaultValue: 'hello!', 'opt2', '-opt2')
        testParser.addOptionArgument(defaultValue: 'world!', 'opt3', '-o3', '--opt3')

        String[] data = ['1', '2', '--opt1', 'DOH!', 'str1', 'str2', '-o3', 'VAH! KIN!', 'str3', 'str4']

        def result = parser.parseArguments(data)

        assert result['two-ints'] == ['1', '2']
        assert result['four-strings'] == ['str1', 'str2', 'str3', 'str4']
        assert result['opt1'] == 'DOH!'
        assert result['opt2'] == 'hello!'
        assert result['opt3'] == 'VAH! KIN!'
    }
}
