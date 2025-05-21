package com.lathavel.newsapp.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lathavel.newsapp.ui.screen.newssources.NewsSourceViewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import com.lathavel.newsapp.domain.model.NewsSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsSourcesScreen(onNavigateUp: () -> Unit,
                      viewmodel : NewsSourceViewmodel = hiltViewModel()) {

    val uiState by viewmodel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "News Sources")
                },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    scrolledContainerColor = MaterialTheme.colorScheme.onSecondaryContainer),
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateUp
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ){ innerPadding->

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

                LazyVerticalGrid(
                    modifier = Modifier.padding(innerPadding).padding(20.dp),
                    columns = GridCells.Fixed(1)) {


                    items(uiState.newsSourceList,
                        key ={
                            it-> it.sourceId ?: it.hashCode()
                        }){ newsSource->
                        DetailsCard(newsSource)
                    }

                }
            }
        }
    }


}

@Composable
fun DetailsCard(newsSource: NewsSource){

    val oddLineColor = Color.White
    val evenLineColor = Color(0xFF72F1EB)
    var buttonColor = oddLineColor/*
    if(count/2 %2==0){
        buttonColor = evenLineColor
    }*/
    OutlinedButton(onClick = {},
        colors = ButtonColors(
            containerColor = buttonColor,
            contentColor = ButtonDefaults.outlinedButtonColors().contentColor,
            disabledContainerColor = ButtonDefaults.outlinedButtonColors().disabledContainerColor,
            disabledContentColor = ButtonDefaults.outlinedButtonColors().disabledContentColor
        ),
        modifier = Modifier.padding(5.dp)) {
        Text(text =  newsSource.sourceName ?: "")
    }
}