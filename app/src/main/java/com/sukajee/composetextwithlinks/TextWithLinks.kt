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

@Composable
fun TextWithLinks(
    modifier: Modifier = Modifier,
    fullText: String,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
    linksList: List<Link>,
) {
    val annotatedString =
        buildAnnotatedString {
            append(fullText)
            linksList.forEach { link ->
                val startIndex = fullText.indexOf(link.linkText)
                val endIndex = startIndex + link.linkText.length
                addStyle(
                    style = link.style,
                    start = startIndex,
                    end = endIndex
                )
                addStringAnnotation(
                    tag = link.linkText,
                    annotation = link.linkData ?: "",
                    start = startIndex,
                    end = endIndex
                )
            }
        }

    ClickableText(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        text = annotatedString,
        style = textStyle,
        onClick = { position ->
            linksList.forEach { link ->
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

@Immutable
data class Link(
    val linkText: String,
    val linkData: String? = null,
    val style: SpanStyle,
    val onClick: (String) -> Unit
)