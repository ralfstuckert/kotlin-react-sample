package common

import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.div
import react.dom.input

interface SearchBarProps : RProps {
    var onSearchTermChange: (String) -> Any
}

interface SearchBarState : RState {
    var term: String
}

class SearchBar(props: SearchBarProps) : RComponent<SearchBarProps, SearchBarState>(props) {

    override fun SearchBarState.init(props: SearchBarProps) {
        term = ""
    }

    override fun RBuilder.render() {
        div("search-bar") {
            input(InputType.search) {
                attrs {
                    value = state.term
                    onChangeFunction = ::onInputChange
                    placeholder = "enter search term"
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



