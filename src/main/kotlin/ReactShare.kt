//To have some "share" buttons we can use an off-the-shelf React component --- 'react-share'
/*
each share button consists of two React components.
For example, there's anEmailShareButton and an EmailIcon
*/
/*
IDEA:
 The different types of share buttons and icons all share the same kind of interface, which makes our job easier.
 Creating the external declarations for each component happens the same way as we already did for the video player.
 */
@file:JsModule("react-share")
@file:JsNonModule

import react.ComponentClass
import react.Props

@JsName("EmailIcon")
external val emailIcon: ComponentClass<IconProps>

@JsName("EmailShareButton")
external val emailShareButton: ComponentClass<ShareButtonProps>

@JsName("TelegramIcon")
external val telegramIcon: ComponentClass<IconProps>

@JsName("TelegramShareButton")
external val telegramShareButton: ComponentClass<ShareButtonProps>

@JsName("WhatsappIcon")
external val whatsappIcon: ComponentClass<IconProps>

@JsName("WhatsappShareButton")
external val whatsappShareButton: ComponentClass<ShareButtonProps>

external interface ShareButtonProps : Props {
    var url: String
}

external interface IconProps : Props {
    var size: Int
    var round: Boolean
}