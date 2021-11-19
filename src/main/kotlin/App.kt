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
// let's make sure we instantiate the child components with proper attributes.
        child(videoList){
            attrs {
                videos = unwatchedVideos
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
        child(videoList){
            attrs {
                videos = unwatchedVideos
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
//problem: The app component has no control over the content that is shown by the videoList component.
// It is hard-coded, so we see the same list twice.

//Solution: So Instead, we need a mechanism to pass the list into the component.

//We now have a reusable component that can render a list of videos.
// The code for our app component got smaller, and we got rid of some duplication in our code.