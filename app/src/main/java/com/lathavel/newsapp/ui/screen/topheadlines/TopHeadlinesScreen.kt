package com.lathavel.newsapp.ui.screen.topheadlines

import android.R
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.lathavel.newsapp.domain.model.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHeadlinesScreen(onNavigateUp: () -> Unit,
                       viewmodel: TopHeadlinesViewmodel = hiltViewModel()) {

    val uiState by viewmodel.uiState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Top HeadLines") },
            colors = TopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                scrolledContainerColor = MaterialTheme.colorScheme.onSecondaryContainer),
            navigationIcon = {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )
    }) { innerPadding ->

        if(uiState.isLoading){
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else{
            if(uiState.error != null){

                Card {

                    Column(verticalArrangement = Arrangement.Center) {

                        Text(
                            text = "Error",
                            modifier = Modifier.padding(10.dp),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                        )

                        Text(
                            text = uiState.error?.toString() ?: "Something went wrong",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                        )

                    }
                }

            }else{
                LazyColumn(modifier = Modifier
                    .padding(innerPadding)
                    .background(color = Color(0xFFE1D8D8))
                    .fillMaxWidth()
                    .wrapContentHeight()) {

                    items(uiState.articles,
                        key= {
                                it-> it.url ?: it.title ?: it.hashCode()
                        }){
                            article->
                        DetailsComposable(article = article)
                    }
                }
            }

        }

    }
}


@Composable
fun DetailsComposable(article: Article) {
    Column (modifier = Modifier.padding(20.dp)) {
        Card(colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Black
        )) {
            // Image loading with Coil
            if (!article.urlToImage.isNullOrBlank()) { // Check if there's an image URL
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(article.urlToImage)
                        .crossfade(true) // Enable crossfade animation
                        // Optional: Add listeners for success/error
                         .listener(
                             onStart = { Log.d("Coil", "Image loading started: ${article.urlToImage}") },
                             onSuccess = { _, _ -> Log.d("Coil", "Image loaded: ${article.urlToImage}") },
                             onError = { _, result -> Log.e("Coil", "Error loading image: ${result.throwable}") }
                        )
                        .build(),
                    contentDescription = article.title ?: "Article image", // Important for accessibility
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp), // Adjust height as needed
                    contentScale = ContentScale.Fit, // Or ContentScale.Fit, ContentScale.FillBounds etc.
                    //placeholder = painterResource(id = R.drawable.placeholder_image), // Optional: Your placeholder drawable
                    error = painterResource(id = R.drawable.stat_notify_error) // Optional: Your error drawable
                )
            } else {
                // Optional: Show a placeholder or a different UI if no image URL
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)){
                    Canvas(modifier = Modifier.fillMaxSize()){
                        drawRect(
                            color = Color.LightGray,
                            topLeft = Offset(0f,0f),
                            size = Size(size.width, size.height)
                        )
                    }
                    Text(text="No Image Available",
                        modifier = Modifier.align(Alignment.Center))
                }
            }
            Text(
                text = article.title ?: "",
                modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.titleLarge, // Larger title
                fontWeight = FontWeight.Bold,
                maxLines = 2
            )

            Text(text = article.description ?: "",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3)
            Text(text = article.sourceName ?: "",  modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f))
        }
    }
}
