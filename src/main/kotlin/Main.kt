import react.dom.*
import kotlinx.browser.document
import kotlinx.css.*
import kotlinx.serialization.Serializable
import styled.*

fun main() {
    render(document.getElementById("root")) {
        child(app)
    }
}
data class Video(
    val id: Int,
    val title: String,
    val speaker: String,
    val videoUrl: String
)
