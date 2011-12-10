package com.casual.grarg

class ArgumentParser {
    // string describing the program, shown before the list of arguments.
    String description

    // determines if the -h / --help option will automatically be added to the list of arguments
    boolean addHelp

    // an additional string of descriptive text that comes after the description, usage, and
    // arguments description
    String epilogue

    // custom usage string.  set this if the generated usage string is not desired.
    String usage

    // name/title of program to show in text output
    String programName

    // keep track of the order in which positional args must be fulfilled.
    List<PositionalArgument> positionalFulfillmentOrder = new ArrayList()

    // for efficient lookup of args based on parsed names.
    Map<String, Argument> namesToArgs = new HashMap()

    // stores all args in order added.
    List<Argument> args = new ArrayList()

    // argument groups in order added.  built-in positionals group is always first.
    // built-in optionals group is always second.
    List<ArgumentGroup> argGroups = new ArrayList()

    public ArgumentParser(String description, String programName, String epilogue=null,
                          String usage=null, boolean addHelp=true) {
        this.description = description
        this.programName = programName
        this.epilogue = epilogue
        this.usage = usage
        this.addHelp = addHelp
    }

    /**
     * Parses the arguments in the given string based on the previously specified options.
     * Returns a map from string option names to option values.
     * @param arguments The command-line arguments to parse.
     * @return
     * @throws com.casual.grarg.ArgumentParseException when invalid arguments are specified.
     */
    public def parseArguments(String arguments) {
        return null
    }

    public PositionalArgument addPositionalArgument(String name) {
        return addPositionalArgument([:], name)
    }

    public PositionalArgument addPositionalArgument(Map optionals, String name) {
        def newArg = addPositionalArgumentInternal(optionals, name)

        // what to do with it?
        positionalFulfillmentOrder.add(newArg);

        args.add(newArg)
        namesToArgs[newArg.name] == newArg

        return newArg
    }

    private PositionalArgument addPositionalArgumentInternal(Map optionals, String name) {
        def action = optionals.get('action', ActionType.STORE)
        def nargs = optionals.get('nargs', '1')
        def defaultValue = optionals.get('defaultValue', null)
        def choices = optionals.get('choices', [])
        def help = optionals.get('help', null)
        def metavar = optionals.get('metavar', name)

        return new PositionalArgument(name, action, nargs, defaultValue, choices, help, metavar)
    }

    public OptionArgument addOptionArgument(String name, String... flags) {
        return addOptionArgument([:], name, flags)
    }

    public OptionArgument addOptionArgument(Map optionals, String name, String... flags) {
        def newArg = addOptionArgumentInternal(optionals, name, flags)

        args.add(newArg)
        namesToArgs[newArg.name] == newArg

        return newArg
    }

    private OptionArgument addOptionArgumentInternal(Map optionals, String name, String... flags) {
        def action = optionals.get('action', ActionType.STORE)
        def nargs = optionals.get('nargs', '1')
        def defaultValue = optionals.get('defaultValue', null)
        def choices = optionals.get('choices', [])
        def help = optionals.get('help', null)
        def metavar = optionals.get('metavar', name)

        def required = optionals.get('required', false)

        if (flags.length < 1) {
            flags = ['--' + name]
        }

        return new OptionArgument(name, flags, required, action, nargs,
                defaultValue, choices, help, metavar)
    }

    public ArgumentGroup addArgumentGroup(String groupTitle, String groupDescription=null) {
        def argGroup = new ArgumentGroup(this, groupTitle, groupDescription)

        argGroups.add(argGroup)

        return argGroup;
    }

    /**
     * Prints a usage string showing the 'syntax' to use when calling the program.
     * 
     */
    public void printUsage() {
        println(makeUsageString())
    }

    /**
     * Prints a message showing the usage plus detailed help.
     */
    public void printHelp() {
        println(makeHelpString())
    }

    /**
     *
     */
    private String makeUsageString() {
        def builder = new StringBuilder()

        args.each {
            builder.append('yo! ')
        }

        return builder.toString()
    }

    /**
     *
     * @return
     */
    private String makeHelpString() {
        def builder = new StringBuilder()

        builder.append(makeUsageString())

        argGroups.each { ArgumentGroup group->
            group.groupArgs.each {
                builder.append('yo! \n')
            }
        }

        return builder.toString()
    }
}