package common

import react.*
import react.dom.div
import react.dom.h3
import react.dom.span


fun RBuilder.alert(message: String = "") = if (message.isEmpty()) {
    span {}
} else {
    div("alert alert-danger") {
        +message
    }
}

fun RBuilder.loading(isLoading: Boolean) = if (isLoading) {
    h3 {
        +"Loading... "
        spinner()
    }
} else {
    span {}
}

fun RBuilder.spinner() = div("spinner") {}
