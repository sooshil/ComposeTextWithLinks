# Compose Text with Links
This Android project demonstrates how to create clickable links within a paragraph of text using Jetpack Compose. It handles both internal links (for navigation within the app) and external links (to open web pages in a browser).
### Features
- Clickable Links: Easily embed clickable links within a text block.
- Customizable Styles: Apply different styles (color, underline, etc.) to each link.
- Internal and External Link Handling: Differentiate between links that navigate within the app and those that open in an external browser.
- Link Metadata: Associate additional data with each link to handle actions when clicked.
### Usage
1. Define Links: Create a list of Link objects, each representing a clickable link. Provide the link text, optional metadata, style, and an onClick handler.
``` kotlin
val linksList = listOf(
    Link(
        linkText = "Account Settings",
        linkData = "https://www.example.com/account-settings",
        style = SpanStyle(color = Color.Blue)
    ) { str -> 
        // Handle external link click (e.g., open in browser)
        // str here returns the linkData string.
    },
    Link(
        linkText = "Help Center",
        style = SpanStyle(color = Color.Red),
    ) { str -> 
        // Handle link click
    },
    // ... more links
)
```
2. Use TextWithLinks Composable: Pass the full text and the list of links to the TextWithLinks composable.

```kotlin
TextWithLinks(
    fullText = "Paragraph with links.",
    linksList = linksList
)
```

### Example
The MainActivity class provides a simple example of how to use the TextWithLinks composable. It displays a paragraph with links to "Account Settings", "Help Center", and "Privacy Policy".
### Customization
- Styling: Customize the appearance of links by modifying the SpanStyle in each Link object.
- Link Handling: Implement the onClick lambda in each Link object to define the behavior when a link is clicked.
- Metadata: Use the linkData field to store additional information associated with each link, such as URLs, navigation targets, or action identifiers.
### Contributing
Contributions are welcome! Feel free to open issues or pull requests to suggest improvements or report bugs.
