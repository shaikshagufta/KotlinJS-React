/*
To use the JavaScript package from inside of our React application,
we need to tell the Kotlin compiler what to expect.
We do this by providing it with external declarations.
*/

@file:JsModule("react-youtube-lite")
@file:JsNonModule

import react.*

@JsName("ReactYouTubeLite")
//external val reactPlayer: ComponentClass<dynamic>
/*
These last two lines are equivalent to a JavaScript import like require("react-youtube-lite").default;.
It tells the compiler that we're certain we'll get a component conforming to RClass<dynamic> at runtime.
 */
/*
The snippet above is cheating a little. The generic type for the props accepted by reactPlayer is set to dynamic.
That means the compiler will just accept any code, at the risk of breaking things at runtime.
 */
/*
A better alternative would be to create an external interface,
which specifies what kind of properties belong to the props for this external component
 */
external val reactPlayer: ComponentClass<ReactYouTubeProps>

external interface ReactYouTubeProps : Props {
    var url: String
}
//1 we can now use our new reactPlayer to replace the gray placeholder rectangle in our videoPlayer component.