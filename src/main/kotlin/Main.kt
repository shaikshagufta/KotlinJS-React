import react.dom.*
import kotlinx.browser.document
import kotlinx.css.*
import kotlinx.serialization.Serializable
import styled.*

fun main() {
    render(document.getElementById("root")) {
        h1 {
            +"KotlinConf Explorer"
        }
        div {
            h3 {
                +"Videos to watch"
            }
            //we can now write a Kotlin for-loop to iterate over the collection of unwatched and watched videos.
            for(video in unwatchedVideos) {
                p {
                    +"${video.speaker}: ${video.title}"
                }
            }

            h3 {
                +"Videos watched"
            }
            for(video in watchedVideos) {
                p {
                    +"${video.speaker}: ${video.title}"
                }
            }
        }
        div {
            h3 {
                +"John Doe: Building and breaking things"
            }
            img {
                attrs {
                    src = "https://via.placeholder.com/640x360.png?text=Video+Player+Placeholder"
                }
            }
        }
    }
}
data class Video(
    val id: Int,
    val title: String,
    val speaker: String,
    val videoUrl: String
)
//let's fill up the two lists for unwatched videos and watched videos respectively.
val unwatchedVideos = listOf(
    Video(1, "Building and breaking things", "John Doe", "https://youtu.be/PsaFVLr8t4E"),
    Video(2, "The development process", "Jane Smith", "https://youtu.be/PsaFVLr8t4E"),
    Video(3, "The Web 7.0", "Matt Miller", "https://youtu.be/PsaFVLr8t4E")
)

val watchedVideos = listOf(
    Video(4, "Mouseless development", "Tom Jerry", "https://youtu.be/PsaFVLr8t4E")
)