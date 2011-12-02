package com.casual.grarg

import com.sun.javaws.exceptions.InvalidArgumentException

class ArgumentParser {
    String description
    String epilogue
    boolean addHelp
    String usage
    String programName

    // keep track of the order in which positional args must be fulfilled.
    List positionalFulfillment

    // for efficient lookup of args based on parsed names.
    Map namesToArgs

    // stores all args in order added.
    List args

    // argument groups in order added.  built-in positionals group is always first.
    // built-in optionals group is always second.
    List argGroups


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

    public def addPositionalArgument(Map optionals, String name) {
        def newArg = addPositionalArgumentInternal(optionals, name)

        // what to do with it?
    }

    private PositionalArgument addPositionalArgumentInternal(Map optionals, String name) {
        return null
    }

    public def addOptionArgument(Map optionals, String name, String keyword) {
        addOptionArgumentInternal(optionals, name, keyword)
    }

    public def addOptionArgument(Map optionals, String flag) {
        def newArg = addOptionArgumentInternal(optionals, clearLeadingDashes(flag), flag)
    }

    private OptionArgument addOptionArgumentInternal(Map map, String name, String... flag) {
        return null
    }

    public ArgumentGroup addArgumentGroup(String groupTitle, String groupDescription) {
        return null
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