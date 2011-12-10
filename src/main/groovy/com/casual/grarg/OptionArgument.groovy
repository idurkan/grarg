package com.casual.grarg

class OptionArgument extends Argument {
    List<String> flags
    Boolean required

    OptionArgument(def name, def flags, def required, def action, def nargs,
            def defaultValue, def choices, def help, def metavar) {

        super(name, action, nargs, defaultValue, choices, help, metavar)

        this.flags = flags
        this.required = required
    }
}
