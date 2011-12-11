package com.casual.grarg

public class OptionArgument extends Argument {
    List<String> flags
    Boolean required

    private OptionArgument(def name, def flags, def required, def action, def nargs,
            def defaultValue, def choices, def help, def metavar) {

        super(name, action, nargs, defaultValue, choices, help, metavar)

        this.flags = flags
        this.required = required
    }

    public static createOptionArgument(def name, def flags, def required, def action, def nargs,
            def defaultValue, def choices, def help, def metavar) {

        // TODO validate the option argument parameters
        return new OptionArgument(name, flags, required, action, nargs, defaultValue,
                choices, help, metavar)
    }
}
