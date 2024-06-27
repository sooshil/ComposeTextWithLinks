package com.sukajee.composetextwithlinks

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sukajee.composetextwithlinks.ui.theme.ComposeTextWithLinksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTextWithLinksTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val context = LocalContext.current
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(Modifier.height(32.dp))
                        TextWithLinks(
                            textStyle = TextStyle(
                                fontSize = 24.sp,
                                textAlign = TextAlign.Justify
                            ),
                            linkData = LinkData(
                                fullText = "Welcome to our app! For more information about our Privacy Policy, " +
                                    "please review it carefully. If you're having trouble, check out " +
                                    "our Help Center for FAQs and troubleshooting guides. Ready to get started? " +
                                    "Head over to the Account Settings page to personalize your experience.",
                                linksList = listOf(
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
                            )
                        )
                    }
                }
            }
        }
    }
}