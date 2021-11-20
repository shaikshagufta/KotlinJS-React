import kotlinx.coroutines.async
import kotlinx.css.*
import react.*
import react.dom.*
import kotlinx.browser.window
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import styled.css
import styled.styledDiv

/*
When the button is clicked, a video should either be:
_moved from the unwatched list to the watched list, or
_moved from the watched list to the unwatched list.
Lists that can change? That's a prime candidate for more application state!
 */
//1 adding the following useState calls
val app = fc<Props> {
    var currentVideo: Video? by useState(null)
    var unwatchedVideos: List<Video> by useState(
        listOf(
            Video(1, "Building and breaking things", "John Doe", "https://youtu.be/PsaFVLr8t4E"),
            Video(2, "The development process", "Jane Smith", "https://youtu.be/PsaFVLr8t4E"),
            Video(3, "The Web 7.0", "Matt Miller", "https://youtu.be/PsaFVLr8t4E")
        )
    )
    var watchedVideos: List<Video> by useState(
        listOf(
            Video(4, "Mouseless development", "Tom Jerry", "https://youtu.be/PsaFVLr8t4E")
        )
    )
/*
2 Since we include all of our demo data in the default values for watchedVideos and unwatchedVideos directly,
we no longer need the file-level declarations from before (Main.kt)
*/

    // typesafe HTML goes here!
    h1 {
        +"KotlinConf Explorer"
    }
    div {
        h3 {
            +"Videos to watch"
        }

        child(videoList) {
            attrs {
                videos = unwatchedVideos
                selectedVideo = currentVideo
                onSelectVideo = { video ->
                    currentVideo = video
                }
            }
        }
        h3 {
            +"Videos watched"
        }
        //pass the selectedVideo,
        // and a handler for onSelectVideo for each of our two video lists
        child(videoList) {
            attrs {
                videos = unwatchedVideos
                selectedVideo = currentVideo
                onSelectVideo = { video ->
                    currentVideo = video
                }
            }
        }
    }
    /*
    Because our VideoPlayerProps interface specifies that
    the videoPlayer component takes a non-null Video,
    we need to make sure handle this in our app component accordingly.
     */
//3 Finally ,changing the call-site for videoPlayer
    currentVideo?.let { curr ->
        child(videoPlayer) {
            attrs {
                video = curr
                unwatchedVideo = curr in unwatchedVideos
                onWatchedButtonPressed = {
                    if (video in unwatchedVideos) {
                        unwatchedVideos = unwatchedVideos - video
                        watchedVideos = watchedVideos + video
                    }
                    else
                    {
                        watchedVideos = watchedVideos - video
                        unwatchedVideos = unwatchedVideos + video
                    }
                }

            }
        }

        /*
    By using the let scope function,
    we ensure that the videoPlayer component is only added when 'state.currentVideo' is not null.
     */
    }
}
/*
Output:
1 clicking an entry in the list will bring up the video player,
and populate it with the information from the clicked entry.
2 Adding a button and wiring it,
we use a basic Kotlin if expression to change the color of the button dynamically.
3 when we press the button a few times.
 The video will jump between the two lists.
*/