package giphy

import kotlinx.html.*
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

        if (giphy != DummyGiphy) {
            div("giphy-detail col-md-8") {
                div("embed-responsive embed-responsive-16by9") {
                    iframe(classes="embed-responsive-item" ) {
                        attrs {
                            src = giphy.giphyUrl
                            title = giphy.fileName
                        }
                    }
                }
                div("details text-center") {
                    a(giphy.downloadUrl,  classes="btn btn-primary") {
                        attrs {
                            title = giphy.fileName
                            role = "button"
                            target = "_blank"
                        }
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


