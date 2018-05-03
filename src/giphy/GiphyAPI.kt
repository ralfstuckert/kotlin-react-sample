package giphy

import axios.axios
import kotlinx.coroutines.experimental.await

// some kotlin interfaces for the giphy API
external interface GiphyImg {
    var url: String
}

external interface GiphyImgContainer {
    var original: GiphyImg
    var fixed_height_small_still: GiphyImg
}

external interface Giphy {
    var id: String
    var embed_url: String
    var images: GiphyImgContainer
    var slug: String
}

// some extensions for our application
val Giphy.fileName: String
    get() = "${this.slug}.gif"

val Giphy.giphyUrl: String
    get() = this.embed_url

val Giphy.downloadUrl: String
    get() = "https://media.giphy.com/media/${this.id}/giphy.gif"

val Giphy.previewUrl: String
    get() = this.images.fixed_height_small_still.url;


object DummyGiphyImg : GiphyImg {
    override var url: String = ""
}

object DummyGiphyImgContainer : GiphyImgContainer {
    override var original: GiphyImg = DummyGiphyImg
    override var fixed_height_small_still: GiphyImg = DummyGiphyImg
}

object DummyGiphy : Giphy {
    override var id: String = ""
    override var embed_url: String = ""
    override var images: GiphyImgContainer = DummyGiphyImgContainer
    override var slug: String = ""
}

/**
 * Do not use for production, see https://giphy.api-docs.io/1.0/welcome/access-and-api-keys
 */
val PUBLIC_BETA_API_KEY = "dc6zaTOxFJmzC"

val GIPHY_SEARCH = "http://api.giphy.com/v1/gifs/search"


fun giphyUrl(searchTerm: String) = "${GIPHY_SEARCH}?q=${searchTerm}&limit=7&api_key=${PUBLIC_BETA_API_KEY}"

fun giphySearch(searchTerm: String, callback: (Array<Giphy>) -> Unit, error: (Throwable) -> Unit) {

    axios.get<dynamic>(giphyUrl(searchTerm)).then { result ->
        val giphies: Array<Giphy> = result.data.data
        callback(giphies)
    }.catch {
        error(it)
    }
}

suspend fun giphySearchCoroutines(searchTerm: String): Array<Giphy> {

    val result = axios.get<dynamic>(giphyUrl(searchTerm)).await()
    val giphies: Array<Giphy> = result.data.data
    return giphies;
}
