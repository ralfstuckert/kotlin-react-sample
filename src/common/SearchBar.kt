package common

import giphy.Giphy
import giphy.fileName
import giphy.giphyUrl
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import kotlinx.html.title
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.div
import react.dom.img
import react.dom.input
import react.dom.li

interface SearchBarProps : RProps {
    var onSearchTermChange: (String) -> Any
}

data class SearchBarState(var term: String) : RState

class SearchBar(props: SearchBarProps) : RComponent<SearchBarProps, SearchBarState>(props) {

    override fun RBuilder.render() {
        div("search-bar") {
            input(InputType.search) {
                attrs {
                    value = state.term
                    onChangeFunction = ::onInputChange
                }
            }
        }
    }

    fun onInputChange(event: Event) {
        val target = event.target as HTMLInputElement
        val searchTerm = target.value
        setState {
            term = searchTerm
        }
        this.props.onSearchTermChange(searchTerm)
    }
}


fun RBuilder.searchBar(onSearchTermChange: (String) -> Any) = child(SearchBar::class) {
    attrs.onSearchTermChange = onSearchTermChange
}



