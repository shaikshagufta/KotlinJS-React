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
        for (video in unwatchedVideos) {
            p {
                +"${video.speaker}: ${video.title}"
            }
        }

        h3 {
            +"Videos watched"
        }
        //pass the selectedVideo,
        // and a handler for onSelectVideo for each of our two video lists
        child(videoList){
            attrs {
                videos = unwatchedVideos
                selectedVideo = currentVideo
                onSelectVideo = { video ->
                    currentVideo = video
                }
            }
        }
        for (video in watchedVideos) {
            p {
                +"${video.speaker}: ${video.title}"
            }
        }
    }
    //let scope function-- we ensure that the videoPlayer component is only added when state.currentVideo is not null.
    currentVideo?.let { curr ->
        child(videoPlayer) {
            attrs {
                video = curr
            }
        }
    }
}
/*
clicking an entry in the list will bring up the video player,
and populate it with the information from the clicked entry.
*/