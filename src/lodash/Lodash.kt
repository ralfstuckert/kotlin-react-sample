package lodash

@JsModule("lodash")
external val lodash: dynamic

external interface Lodash {
    fun <K,V> debounce(functionToDebounce: (K) -> V, debounceMillis: Int): (K) -> V
}


