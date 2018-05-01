package lodash

@JsModule("lodash")
external val lodash: Lodash = definedExternally

external interface Lodash {
    fun <V> debounce(function: (dynamic) -> V, debounceMillis: Int): (dynamic) -> V
}

