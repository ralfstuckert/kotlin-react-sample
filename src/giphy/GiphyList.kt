package giphy

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.ul

interface GiphyListProps : RProps {
    var giphies: Array<Giphy>
    var onSelect: (Giphy) -> Unit
}

class GiphyList(props: GiphyListProps) : RComponent<GiphyListProps, RState>(props) {

    override fun RBuilder.render() {
        ul("col-md-4 list-group") {
            props.giphies.map { giphy ->
                giphyListItem(giphy, props.onSelect)
            }
        }
    }

}

fun RBuilder.giphyList(giphies: Array<Giphy>, onSelect: (Giphy) -> Unit) = child(GiphyList::class) {
    attrs.giphies = giphies
    attrs.onSelect = onSelect
}


