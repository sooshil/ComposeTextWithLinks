# Compose Text with Links
This Android project demonstrates how to create clickable links within a paragraph of text using Jetpack Compose. It handles both internal links (for navigation within the app) and external links (to open web pages in a browser).
### Features
- Clickable Links: Easily embed clickable links within a text block.
- Customizable Styles: Apply different styles (color, underline, etc.) to each link.
- Internal and External Link Handling: Differentiate between links that navigate within the app and those that open in an external browser.
- Link Info: Associate additional data with each link to handle actions when clicked.
### Usage
1. Define Links: Create a list of Link objects, each representing a clickable link. Provide the link text, optional linkInfo, style, and an onClick handler.
``` kotlin
val linksList = listOf(
        Link(
            linkText = "Account Settings",
            linkInfo = "https://www.example.com/account-settings",
            style = SpanStyle(color = Color.Blue)
        ) { str -> // This str will return the linkInfo of the link.
            Toast.makeText(
                context,
                "Clicked on $str", //Toast will show -> Clicked on https://www.example.com/account-settings
                Toast.LENGTH_LONG
            ).show()
        },
        Link(
            linkText = "Help Center",
            style = SpanStyle(color = Color.Red),
            linkInfo = "refund"
        ) { str -> // This str will return the linkInfo of the link.
            // navigateToHelpCenterScreen(helpTopic = str) will pass helpTopic = "refund"
        },
        Link(
            linkText = "Privacy Policy",
            style = SpanStyle(color = Color.Magenta),
        ) {
            Toast.makeText(
                context,
                "Clicked on Privacy Policy",
                Toast.LENGTH_LONG
            ).show()
        }
    )
```
2. Use TextWithLinks Composable: Pass the linkData to the TextWithLinks composable.

```kotlin
TextWithLinks(
    linkData: LinkData,
)
```
#### where
```kotlin
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
```

### Example
The MainActivity class provides a simple example of how to use the TextWithLinks composable. It displays a paragraph with links to "Account Settings", "Help Center", and "Privacy Policy".
### Customization
- Styling: Customize the appearance of links by modifying the SpanStyle in each Link object.
- Link Handling: Implement the onClick lambda in each Link object to define the behavior when a link is clicked.
- LinkInfo: Use the linkData field to store additional information associated with each link, such as URLs, navigation targets, or action identifiers.
### Contributing
Contributions are welcome! Feel free to open issues or pull requests to suggest improvements or report bugs.
