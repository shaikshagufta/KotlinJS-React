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
/*
    Since we include all of our demo data in the default values
    for watchedVideos and unwatchedVideos directly,
    we no longer need the file-level declarations here.
*/

/*
val unwatchedVideos = listOf(
    Video(1, "Building and breaking things", "John Doe", "https://youtu.be/PsaFVLr8t4E"),
    Video(2, "The development process", "Jane Smith", "https://youtu.be/PsaFVLr8t4E"),
    Video(3, "The Web 7.0", "Matt Miller", "https://youtu.be/PsaFVLr8t4E")
)

val watchedVideos = listOf(
    Video(4, "Mouseless development", "Tom Jerry", "https://youtu.be/PsaFVLr8t4E")
)
*/
