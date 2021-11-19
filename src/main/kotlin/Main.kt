import react.dom.*
import kotlinx.browser.document
import kotlinx.css.*
import kotlinx.serialization.Serializable
import styled.*

fun main() {
    render(document.getElementById("root")) {
        h1{
            +"Hello, React+Kotlin/JS from T4!"
        }
    }
}