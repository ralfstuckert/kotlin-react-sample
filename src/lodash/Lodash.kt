package lodash

@JsModule("lodash")
external val lodash: Lodash

external interface Lodash {
    fun <K,V> debounce(functionToDebounce: (K) -> V, debounceMillis: Int): (K) -> V
}


