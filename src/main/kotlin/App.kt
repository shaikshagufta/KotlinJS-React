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

suspend fun fetchVideo(id: Int): Video {
    val response = window
        .fetch("https://my-json-server.typicode.com/shaikshagufta/kotlin-MyJSON-Server/videos/$id")
        .await()
        .text()
        .await()
    return Json.decodeFromString(response)
}

suspend fun fetchVideos(): List<Video> = coroutineScope {
    //We can then start 25 asynchronous tasks (one per request) and wait for all of them to complete
    (1..25).map { id ->
        async {
            fetchVideo(id)
        }
    }.awaitAll()
}

val mainScope = MainScope()
val app = fc<Props> {
    var currentVideo: Video? by useState(null)
    var unwatchedVideos: List<Video> by useState(emptyList())
    var watchedVideos: List<Video> by useState(emptyList())

    useEffectOnce {
        mainScope.launch {
            unwatchedVideos = fetchVideos()
        }
    }
    h1 {
        +"BTS Speeches Watchlist"
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
        child(videoList) {
            attrs {
                videos = watchedVideos
                selectedVideo = currentVideo
                onSelectVideo = { video ->
                    currentVideo = video
                }
            }
        }
    }
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
    }
}