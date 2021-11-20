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
val unwatchedVideos = listOf(
    Video(1, "Building and breaking things", "John Doe", "https://youtu.be/PsaFVLr8t4E"),
    Video(2, "The development process", "Jane Smith", "https://youtu.be/PsaFVLr8t4E"),
    Video(3, "The Web 7.0", "Matt Miller", "https://youtu.be/PsaFVLr8t4E")
)

val watchedVideos = listOf(
    Video(4, "Mouseless development", "Tom Jerry", "https://youtu.be/PsaFVLr8t4E")
)
/*
problem: each list keeps track of the selected video on its own.
That means, when we have two lists (one for watched and one for unwatched videos), we can select two videos.
That's not really helpful, since we only have one video player

Clearly, a list can't keep track of both which video is selected inside itself, and inside a sibling list.
Really, the selected video is not part of the list state, but of the application state.
IDEA:
This means we need to lift the state out of the individual components.
The process of migrating state from components to their parents is called lifting state.
*/