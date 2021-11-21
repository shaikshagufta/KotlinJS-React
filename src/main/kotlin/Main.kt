import react.dom.*
import kotlinx.browser.document
import kotlinx.css.*
import kotlinx.serialization.Serializable
import styled.*

//1 to tell the serialization library about the Video class.
@Serializable
data class Video(
    val id: Int,
    val title: String,
    val speaker: String,
    val videoUrl: String
)
fun main() {
    render(document.getElementById("root")) {
        child(app)
    }
}

