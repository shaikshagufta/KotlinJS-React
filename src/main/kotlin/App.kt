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
/*
IDEA:
To pull some real data from a REST API into the app
instead of using hard-coded demo data for the content of our app (which we have done till now)
 */
/*
For this tutorial, we've created a small API available at
https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/1.
It only offers one endpoint, videos, which takes a number to access an element from the list.
If you visit the API with your browser,
you will see that the objects returned from the API follow the same structure as our Video objects.
ie
{
  "id": 1,
  "videoUrl": "https://www.youtube.com/watch?v=PsaFVLr8t4E",
  "title": "Conference Opening Keynote",
  "speaker": "Andrey Breslav"
}
*/
/*
1 As preparation for fetching our first video wee need to
tell the serialization library about the Video class
*/
//2 Fetching our first video
suspend fun fetchVideo(id: Int): Video {
    val response = window
        .fetch("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")
        .await()
        .text()
        .await()
    return Json.decodeFromString(response)
}
/*
3 define a function called fetchVideos that requests 25 videos from the API.
We do this by repeatedly calling the async function from Kotlin's coroutines.
*/
//For reasons of structured concurrency, we have to wrap our implementation in a coroutineScope.
suspend fun fetchVideos(): List<Video> = coroutineScope {
    //We can then start 25 asynchronous tasks (one per request) and wait for all of them to complete
    (1..25).map { id ->
        async {
            fetchVideo(id)
        }
    }.awaitAll()
}
/*
4 We now have a way to obtain real data in our app. Plugging this in our app as follows
 */
val mainScope = MainScope()
/*
The MainScope is a part of Kotlin's structured concurrency model,
and creates the scope for our asynchronous tasks to run in.
*/
val app = fc<Props> {
    var currentVideo: Video? by useState(null)
    var unwatchedVideos: List<Video> by useState(emptyList())
    var watchedVideos: List<Video> by useState(emptyList())
/*
useEffectOnce is another one of React's magic hooks
(specifically, a simplified version of the useEffect hook).
It indicates that our component performs a side effect.
It doesn't just render itself, but also communicates over the network.
 */
    useEffectOnce {
        mainScope.launch {
            unwatchedVideos = fetchVideos()
        }
    }
    h1 {
        +"KotlinConf Explorer"
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
/*
This means when we load the page:
1 The code of our app component will be invoked. This kicks off the code in the useEffectOnce block.
2 The app component is rendered with empty lists for the watched and unwatched videos
3 When our API requests finish, the useEffectOnce block assigns it to the state of the app component. This triggers a re-render.
4 The code of the app component will be invoked again, but the useEffectOnce block will not run for a second time.
OUTPUT:
We'll see real data in the app!
*/