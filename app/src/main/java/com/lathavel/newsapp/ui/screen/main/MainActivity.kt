package com.lathavel.newsapp.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lathavel.newsapp.ui.navigation.AppNavigation
import com.lathavel.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                Surface {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun SearchScreen(onNavigateUp: () -> Unit) {
    println("Not yet implemented")
}

@Composable
fun MainScreen(
    onNavigateToTopHeadlines: () -> Unit,
    onNavigateToNewsSources: () -> Unit,
    onNavigateToCountries: () -> Unit,
    onNavigateToLanguages: () -> Unit,
    onNavigateToSearch: () -> Unit
) {


    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxWidth().wrapContentSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(100.dp))

            Greeting("User")
            ElevatedButton(onClick = {}) {
                Text(text = "Grab your coffee & catch up on news!")
            }

            val cardColor = Color(0xFF2F58B6)
            HeadingsView("Top Headlines", onNavigateToTopHeadlines, cardColor)
            Spacer(modifier = Modifier.height(10.dp))
            HeadingsView("News Sources", onNavigateToNewsSources, cardColor)
            Spacer(modifier = Modifier.height(10.dp))
            HeadingsView("Countries", onNavigateToCountries, cardColor)
            Spacer(modifier = Modifier.height(10.dp))
            HeadingsView("Languages", onNavigateToLanguages, cardColor)
            Spacer(modifier = Modifier.height(10.dp))
            HeadingsView("Search", onNavigateToSearch, cardColor)
        }
    }


}


@Composable
fun HeadingsView(name: String, onClick: () -> Unit, cardColor: Color){

     Button ( onClick = onClick,
         enabled = true,
         colors = ButtonColors(
             containerColor = cardColor, contentColor = Color.White,
             disabledContainerColor = Color.Gray, disabledContentColor = Color.Black
         ),
         modifier = Modifier.padding(top = 10.dp)//margin
             .width(250.dp).wrapContentHeight(),
         contentPadding = PaddingValues(top = 20.dp, bottom = 20.dp)// padding of the text
     ){
         Text(text = name)
     }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.padding(10.dp),
        color = Color.Magenta
    )
}
