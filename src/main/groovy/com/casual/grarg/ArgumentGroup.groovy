package com.casual.grarg

/**
 * Allows grouping arguments together logically.  In help-message output, groups' options are listed
 * together.
 */
class ArgumentGroup {
    private ArgumentParser parent

    String title
    String description

    List groupArgs

    public def addPositionalArgument(def argument) {
        parent.addPositionalArgument(argument)
    }

    public def addOptionArgument(def argument) {
        parent.addOptionArgument(argument)
    }
}
