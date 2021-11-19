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
val app = fc<Props> {
    // typesafe HTML goes here!
    h1 {
        +"KotlinConf Explorer"
    }
    div {
        h3 {
            +"Videos to watch"
        }
        child(videoList)
        //we can now write a Kotlin for-loop to iterate over the collection of unwatched and watched videos.
        for (video in unwatchedVideos) {
            p {
                +"${video.speaker}: ${video.title}"
            }
        }

        h3 {
            +"Videos watched"
        }
        child(videoList)
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
//problem: The app component has no control over the content that is shown by the videoList component.
// It is hard-coded, so we see the same list twice.