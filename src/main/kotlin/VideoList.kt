import kotlinx.html.js.onClickFunction
import kotlinx.browser.window
import react.*
import react.dom.*

//IDEA:"watched" and "unwatched" video lists have the same functionality: they display a list of videos.
// Instead of duplicating their logic, we can turn them into a reusable component instead, and use that component twice.

//The videoList component follows the same pattern as the app component.
// It uses the fc builder function, and contains the code from our unwatchedVideos list.

//We now adjust the class definition of VideoList to make use of the props, which are passed into the fc block
val videoList = fc<VideoListProps> { props ->
    for (video in props.videos) {
        p {
            key = video.id.toString()
//'Key' attribute helps the React renderer figure out what it needs to do when the value of props.videos changes.
// It uses the key to determine which parts of a list need to refresh, and which ones stay the same.
            attrs {
                onClickFunction = {
                    window.alert("Clicked $video!")
                }
            }
            +"${video.speaker}: ${video.title}"
        }
    }
}
//For our videoList, we want to add a prop containing the list of videos to be shown.
// To do so, let's define an external interface that holds all the props which can be passed to a videoList component.

external interface VideoListProps : Props {
    var videos: List<Video>
}