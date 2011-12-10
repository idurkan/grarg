package com.casual.grarg

/**
 * Allows grouping arguments together logically.  In help-message output, groups' options are listed
 * together.
 */
class ArgumentGroup {
    ArgumentParser parent

    String title
    String description

    final List<Argument> groupArgs = new ArrayList<Argument>()

    ArgumentGroup(ArgumentParser parent, String title, String description) {
        this.parent = parent
        this.title = title
        this.description = description
    }

    public def addPositionalArgument(String name) {
        return addPositionalArgument([:], name)
    }

    public def addPositionalArgument(Map optionals, String name) {
        PositionalArgument result = parent.addPositionalArgument(optionals, name)
        groupArgs.add(result)
        return result
    }

    public def addOptionArgument(String name, String... flags) {
        addOptionArgument([:], name, flags)
    }

    public def addOptionArgument(Map optionals, String name, String... flags) {
        OptionArgument result = parent.addOptionArgument(optionals, name, flags)
        groupArgs.add(result)
        return result
    }
}
