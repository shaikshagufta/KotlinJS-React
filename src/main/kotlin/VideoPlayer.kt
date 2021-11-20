/*
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
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

    var selectedVideo: Video?

    var onSelectVideo: (Video) -> Unit
}*/
