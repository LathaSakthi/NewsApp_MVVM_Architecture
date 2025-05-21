package com.lathavel.newsapp.ui.theme.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lathavel.newsapp.ui.screen.main.HeadingsView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(onNavigateUp: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Countries")
            },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp){
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                })
        }
    ) {
        innerPadding ->
        LazyColumn (modifier = Modifier.padding(innerPadding).padding(20.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            items(count = 10){
                HeadingsView("Country"+ it, onClick = {},
                    cardColor = Color(0xFFCE7119))
            }
        }
    }


}
