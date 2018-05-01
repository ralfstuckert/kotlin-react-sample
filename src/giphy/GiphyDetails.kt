package giphy

import kotlinx.html.IframeSandbox
import kotlinx.html.role
import kotlinx.html.title
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*


interface GiphyProps : RProps {
    var giphy: Giphy
}

class GiphyDetails(props: GiphyProps) : RComponent<GiphyProps, RState>(props) {

    override fun RBuilder.render() {
        val giphy = props.giphy
        if (giphy == DummyGiphy) {
            span {  }
        } else {
            div("giphy-detail col-md-8") {
                div("embed-responsive embed-responsive-16by9") {
                    iframe(classes="embed-responsive-item" ) {
                        attrs.src = giphy.giphyUrl
                        attrs.title = giphy.fileName
                    }
                }
                div("details text-center") {
                    a("btn btn-primary") {
                        attrs.href=giphy.downloadUrl
                        attrs.title = giphy.fileName
                        attrs.role = "button"
                        attrs["download"]= giphy.fileName
                        attrs["alt"]= giphy.fileName

                        +"Download from Giphy"
                    }
                }
            }
        }
    }
}

fun RBuilder.giphyDetails(giphy: Giphy = DummyGiphy) = child(GiphyDetails::class) {
    attrs.giphy = giphy
}


