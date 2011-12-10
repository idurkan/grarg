package com.casual.grarg

class Argument {

    Argument(def name, def action, def nargs, def defaultValue,
                    def choices, def help, def metavar) {
        this.name = name
        this.action = action
        this.nargs = nargs
        this.defaultValue = defaultValue
        this.choices = choices
        this.help = help
        this.metavar = metavar
    }

    String name
    ActionType action

    // may be '*', '+', '?', or an integer string.
    // '*' - 0 to infinity arguments
    // '+' - 1 to infinity arguments
    // '?' - 0 or 1 arguments
    String nargs

    // defaultValue: must agree in number with numerical nargs values.
    // for example, if nargs == '2', defaultValue must be a list with two elements
    List defaultValue

    // choices: allowable values of the argument.   
    List choices

    // describes the argument in generated help string
    String help

    // a symbol to use for the argument in a generated usage string.  defaults to match the
    // name.
    String metavar
}
