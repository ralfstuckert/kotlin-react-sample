package app

import common.alert
import common.loading
import common.searchBar
import giphy.*
import kotlinx.coroutines.experimental.async
import lodash.lodash
import react.*
import react.dom.div
import react.dom.h1


interface AppState : RState {
    var giphies: Array<Giphy>
    var selectedGiphy: Giphy
    var errorMessage: String
    var loading: Boolean
}


class App : RComponent<RProps, AppState>() {

    override fun AppState.init() {
        giphies = emptyArray()
        selectedGiphy = DummyGiphy
        errorMessage = ""
        loading = false
    }

    override fun RBuilder.render() {
        div("container") {
            h1("text-center") {
                +"Giphy Search"
            }
            searchBar(onSearchTermChange = lodash.debounce(::searchGiphies, 300))
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

        giphySearch(term).then { result: Array<Giphy> ->
            setState {
                selectedGiphy = result[0]
                giphies = result
                loading = false
            }
        }.catch { throwable: Throwable ->
            setState {
                errorMessage = throwable.toString()
                loading = false
            }
        }
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
