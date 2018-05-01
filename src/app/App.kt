package app

import common.alert
import common.loading
import common.searchBar
import giphy.*
import kotlinx.coroutines.experimental.async
import react.*
import react.dom.div
import react.dom.h1
import lodash.*


data class AppState(var giphies: Array<Giphy> = emptyArray(),
                    var selectedGiphy: Giphy = DummyGiphy,
                    var errorMessage: String = "",
                    var loading: Boolean = false) : RState


class App : RComponent<RProps, AppState>() {

    init {
        state = AppState()
    }

    override fun RBuilder.render() {
        div("container") {
            h1("text-center") {
                +"Giphy Search"
            }
            searchBar(onSearchTermChange = lodash.debounce(::searchGiphiesCoroutines, 300))
            alert(state.errorMessage)
            loading(state.loading)
            giphyDetails(state.selectedGiphy)
            giphyList(state.giphies, ::selectGiphy)
        }
    }

    fun selectGiphy(giphy: Giphy) {
        setState {
            selectedGiphy = giphy
        }
    }

    fun searchGiphies(term: String) {
        setState {
            loading = true
            errorMessage = ""
        }

        giphySearch(term, { result: Array<Giphy> ->
            setState {
                selectedGiphy = result[0]
                giphies = result
                loading = false
            }
        }, { throwable: Throwable ->
            setState {
                errorMessage = throwable.toString()
                loading = false
            }
        })
    }

    fun searchGiphiesCoroutines(term: String) = async {
        setState {
            loading = true
            errorMessage = ""
        }

        try {
            val result: Array<Giphy> = giphySearchCoroutines(term)
            setState {
                selectedGiphy = result[0]
                giphies = result
                loading = false
            }
        } catch (t: Throwable) {
            setState {
                errorMessage = t.toString()
                loading = false
            }
        }
    }

}

fun RBuilder.app() = child(App::class) {}
