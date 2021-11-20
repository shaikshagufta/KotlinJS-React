import kotlinx.html.js.onClickFunction
import kotlinx.browser.window
import react.*
import react.dom.*

val videoList = fc<VideoListProps> { props ->


    for (video in props.videos) {
        p {
            key = video.id.toString()
            attrs {
                onClickFunction = {
                    props.onSelectVideo(video)
                }
            }
            if (video == props.selectedVideo) {
                +"▶ "
            }
            +"${video.speaker}: ${video.title}"
        }
    }
}

external interface VideoListProps : Props {
    var videos: List<Video>
    // to receive the selected video as a prop.
    // We expand the VideoListProps interface to contain the selectedVideo
    var selectedVideo: Video?
    /*
    We need to change the state of a parent component again as,
    We can't assign a value to a prop
    */
    var onSelectVideo: (Video) -> Unit
}
