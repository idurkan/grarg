package com.casual.grarg;

import org.junit.Before;
import org.junit.Test;

public class ArgumentGroupTest {
    ArgumentParser parser
    ArgumentGroup gp1
    ArgumentGroup gp2

    @Before
    public void setup() {
        parser = new ArgumentParser('Test Parser', 'foobar')
        gp1 = parser.addArgumentGroup('Group1', 'Group1')
        gp2 = parser.addArgumentGroup('Group2', 'Group2')
    }

    @Test
    public void addPositionalArgumentTest() {
        def posArg1 = gp1.addPositionalArgument([:], 'arg1')

        assert posArg1.name == 'arg1'
        assert posArg1.action == ActionType.STORE
        assert posArg1.nargs == '1'
        assert posArg1.defaultValue == null
        assert posArg1.choices == []
        assert posArg1.help == null
        assert posArg1.metavar == 'arg1'

        def posArg2 = gp2.addPositionalArgument(action:ActionType.APPEND, nargs:'3',
                'arg2')

        assert posArg2.name == 'arg2'
        assert posArg2.action == ActionType.APPEND
        assert posArg2.nargs == '3'
        assert posArg2.defaultValue == null
        assert posArg2.choices == []
        assert posArg2.help == null
        assert posArg2.metavar == 'arg2'

        def posArg3 = gp1.addPositionalArgument(action:ActionType.APPEND, nargs:'*',
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
        def optArg1 = gp1.addOptionArgument([:], 'arg1')

        assert optArg1.name == 'arg1'
        assert optArg1.flags == ['--arg1']
        assert optArg1.action == ActionType.STORE
        assert optArg1.nargs == '1'
        assert optArg1.defaultValue == null
        assert optArg1.choices == []
        assert optArg1.help == null
        assert optArg1.metavar == 'arg1'

        assert !optArg1.required

        def optArg2 = gp2.addOptionArgument(action:ActionType.APPEND, nargs:'3',
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

        def optArg3 = gp1.addOptionArgument(action:ActionType.APPEND, nargs:'*',
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

        def optArg4 = gp2.addOptionArgument(action:ActionType.APPEND, nargs:'3',
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
}
