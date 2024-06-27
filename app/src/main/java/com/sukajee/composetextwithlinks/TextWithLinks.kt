package com.sukajee.composetextwithlinks

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp

/**
 * Composable function to display text with clickable links.
 *
 * @param modifier [Modifier] to apply to the composable.
 * @param linkData [LinkData] object containing the full text and list of links.
 * @param textStyle [TextStyle] to apply to the text.
 *
 * This function creates an annotated string from the full text and links,
 * and then uses a ClickableText composable to display the text and handle click events.
 * When a link is clicked, the corresponding `onClick` function is invoked.
 *
 */
@Composable
fun TextWithLinks(
    modifier: Modifier = Modifier,
    linkData: LinkData,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
) {
    val annotatedString = buildAnnotatedString {
        append(linkData.fullText)
        linkData.linksList.forEach { link ->
            var startIndex = linkData.fullText.indexOf(link.linkText)
            while (startIndex >= 0) {
                val endIndex = startIndex + link.linkText.length
                addStyle(
                    style = link.style,
                    start = startIndex,
                    end = endIndex
                )
                addStringAnnotation(
                    tag = link.linkText,
                    annotation = link.linkInfo ?: "",
                    start = startIndex,
                    end = endIndex
                )
                startIndex = linkData.fullText.indexOf(link.linkText, endIndex)
            }
        }
    }

    ClickableText(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        text = annotatedString,
        style = textStyle,
        onClick = { position ->
            linkData.linksList.forEach { link ->
                annotatedString
                    .getStringAnnotations(link.linkText, position, position)
                    .firstOrNull()?.let {
                        link.onClick.invoke(it.item)
                        return@forEach
                    }
            }
        }
    )
}

/**
 * Data class representing a link with its full text and a list of links.
 *
 * @property fullText The full text containing the link.
 * @property linksList A list of [Link] objects representing the links found in the full text.
 */
@Immutable
data class LinkData(
    val fullText: String,
    val linksList: List<Link>
)

/**
 * Represents a link within the text, with a display text, optional link information, a style, and an
 * action to perform when the link is clicked. Links can be used to navigate to other parts of the
 * application, open external URLs, or trigger other actions.
 *
 * @property linkText The text to display for the link. This is the text that users will see and interact with.
 * @property linkifyAllOccurrences Whether to linkify all occurrences of the `linkText` in the surrounding text.
 * If `false`, only the first occurrence will be linked.
 * @property linkInfo Optional additional information about the link. This can be used to provide more context
 * or details about the link destination.
 * @property style The style to apply to the link text. This can be used to customize the appearance of the link,
 * such as the font, color, or underline.
 * @property onClick The action to perform when the link is clicked. This can be used to navigate to another
 * screen, open a URL, or trigger any other desired action.
 */
@Immutable
data class Link(
    val linkText: String,
    val linkifyAllOccurrences: Boolean = false,
    val linkInfo: String? = null,
    val style: SpanStyle,
    val onClick: (String) -> Unit
)