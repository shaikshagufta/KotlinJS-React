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

val app = fc<Props> {
    var currentVideo: Video? by useState(null)
    // typesafe HTML goes here!
    h1 {
        +"KotlinConf Explorer"
    }
    div {
        h3 {
            +"Videos to watch"
        }

        child(videoList){
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
    currentVideo?.let { curr ->
        child(videoPlayer) {
            attrs {
                video = curr
            }
        }
    }
    /*
    By using the let scope function,
    we ensure that the videoPlayer component is only added when 'state.currentVideo' is not null.
     */
}
/*
Output:
clicking an entry in the list will bring up the video player,
and populate it with the information from the clicked entry.
*/