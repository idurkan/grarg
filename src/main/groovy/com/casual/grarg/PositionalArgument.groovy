package com.casual.grarg

class PositionalArgument extends Argument {
    PositionalArgument(def name, def action, def nargs, def defaultValue,
                    def choices, def help, def metavar) {
        super(name, action, nargs, defaultValue, choices, help, metavar)
    }
}
