import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import styled.*

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
        //plugging in our new components (share icons) into the user interface of our application.
        styledDiv {
            css {
                display = Display.flex
                marginBottom = 10.px
            }
            emailShareButton {
                attrs.url = props.video.videoUrl
                emailIcon {
                    attrs.size = 32
                    attrs.round = true
                }
            }
            telegramShareButton {
                attrs.url = props.video.videoUrl
                telegramIcon {
                    attrs.size = 32
                    attrs.round = true
                }
            }
        }
        //to replace the gray placeholder rectangle in our videoPlayer component.
            reactPlayer {
                attrs.url = props.video.videoUrl
            }
    }
}