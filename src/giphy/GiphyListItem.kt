package giphy

import kotlinx.html.js.onClickFunction
import kotlinx.html.onClick
import kotlinx.html.role
import kotlinx.html.title
import react.*
import react.dom.*

interface GiphyListItemProps : RProps {
    var giphy: Giphy
    var onSelect: (Giphy) -> Unit
}

class GiphyListItem(props: GiphyListItemProps) : RComponent<GiphyListItemProps, RState>(props) {

    override fun RBuilder.render() {
        val giphy = props.giphy

        li("list-group-item") {
            attrs.onClickFunction = { props.onSelect(giphy) }

            div("giphy-item media") {
                div("media-left") {
                    img("media-object") {
                        attrs {
                            src = giphy.previewUrl
                            alt = giphy.fileName
                            title = giphy.fileName
                        }
                    }
                }
            }
        }
    }

}


fun RBuilder.giphyListItem(giphy: Giphy, onSelect: (Giphy) -> Unit) = child(GiphyListItem::class) {
    attrs.giphy = giphy
    attrs.key = giphy.id
    attrs.onSelect = onSelect
}

