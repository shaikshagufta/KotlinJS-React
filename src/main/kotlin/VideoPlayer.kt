/*
IDEA:
A video player needs to know the talk title, the author of the talk, and the link to the video.
All this information is already contained in a Video object.
We can just pass one of those as a prop, and access its attributes.
*/
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import styled.*

/*
NOW,Let's add a button to mark a video as (un)watched,
to move it between the two lists.
*/
/*
Since this button will move videos between two different lists,
we can already guess that the logic handling the state change will have to lifted out of the videoPlayer,
and passed in from the parent as a prop.
*/
/*
Also,we want the button to look different based on whether a video has already been watched or not.
This is also information we need to pass as a prop.
*/
//1 including properties for the two cases
external interface VideoPlayerProps : Props {
    var video: Video
    var onWatchedButtonPressed: (Video) -> Unit
    var unwatchedVideo: Boolean
}

val videoPlayer = fc<VideoPlayerProps> { props ->
    styledDiv {
        css {
            position = Position.absolute
            top = 10.px
            right = 10.px
        }
        h3 {
            +"${props.video.speaker}: ${props.video.title}"
        }
        //2  let's add the button to the actual component
        styledButton {
            css {
                display = Display.block
                backgroundColor = if(props.unwatchedVideo) Color.lightGreen else Color.red
            }
            attrs {
                onClickFunction = {
                    props.onWatchedButtonPressed(props.video)
                }
            }
            if(props.unwatchedVideo) {
                +"Mark as watched"
            }
            else {
                +"Mark as unwatched"
            }
        }
        img {
            attrs {
                src = "https://via.placeholder.com/640x360.png?text=Video+Player+Placeholder"
            }
        }
    }
}