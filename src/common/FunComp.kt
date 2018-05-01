package common

import react.*
import react.dom.div
import react.dom.h3
import react.dom.span

fun RBuilder.alert(message: String = "") = if (message.isNotEmpty()) {
    div("alert alert-danger") {
        +message
    }
} else {
    empty
}


fun RBuilder.loading(isLoading: Boolean) = if (isLoading) {
    h3 {
        +"Loading... "
        spinner()
    }
} else {
    empty
}


fun RBuilder.spinner() = div("spinner") {}


object empty:ReactElement {
    override val props = object:RProps {}
}


