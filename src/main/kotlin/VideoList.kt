import kotlinx.html.js.onClickFunction
import kotlinx.browser.window
import react.*
import react.dom.*

//IDEA:"watched" and "unwatched" video lists have the same functionality: they display a list of videos.
// Instead of duplicating their logic, we can turn them into a reusable component instead, and use that component twice.

//The videoList component follows the same pattern as the app component.
// It uses the fc builder function, and contains the code from our unwatchedVideos list.

val videoList = fc<Props> {
    for (video in unwatchedVideos) {
        p {
            +"${video.speaker}: ${video.title}"
        }
    }
}