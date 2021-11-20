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

// splitting our application into components
// we could then use the videoList component by using the child function, again:
//We add currentVideo as state to our app component.
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
// let's make sure we instantiate the child components with proper attributes.
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
        //we can now write a Kotlin for-loop to iterate over the collection of unwatched and watched videos.
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
    styledDiv {
        css {
            position = Position.absolute
            top = 10.px
            right = 10.px
        }
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
