package com.lathavel.newsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lathavel.newsapp.ui.screen.main.MainScreen
import com.lathavel.newsapp.ui.screen.main.SearchScreen
import com.lathavel.newsapp.ui.screen.topheadlines.TopHeadlinesScreen
import com.lathavel.newsapp.ui.theme.screen.CountriesScreen
import com.lathavel.newsapp.ui.theme.screen.LanguagesScreen
import com.lathavel.newsapp.ui.theme.screen.NewsSourcesScreen

/**
 * The NavHost is a Composable that displays different destinations (your screens) based on the
 * current route. You'll typically set this up in your main
 * activity or a central Composable that manages your app's UI.
 */
@Composable
fun AppNavigation(modifier: Modifier = Modifier,
                  navController: NavHostController = rememberNavController()){

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppDestinations.MAIN_SCREEN // Initial screen
    ) {
        //Define each screen destination
        composable(route = AppDestinations.MAIN_SCREEN) {
            MainScreen(onNavigateToTopHeadlines = {navController.navigate(AppDestinations.TOP_HEADLINES_SCREEN)},
                onNavigateToNewsSources ={navController.navigate(AppDestinations.NEWS_SOURCES_SCREEN)},
                onNavigateToCountries ={navController.navigate(AppDestinations.COUNTRIES_SCREEN)},
                onNavigateToLanguages={navController.navigate(AppDestinations.LANGUAGES_SCREEN)},
                onNavigateToSearch = {navController.navigate(AppDestinations.SEARCH_SCREEN)})
        }

        composable(route = AppDestinations.TOP_HEADLINES_SCREEN){
            TopHeadlinesScreen(
                onNavigateUp = {navController.navigateUp()}
            )
        }

        composable(route = AppDestinations.NEWS_SOURCES_SCREEN){
            NewsSourcesScreen(
                onNavigateUp = {navController.navigateUp()}
            )
        }

        composable(route = AppDestinations.COUNTRIES_SCREEN){
            CountriesScreen(
                onNavigateUp = {navController.navigateUp()}
            )
        }

        composable(route = AppDestinations.LANGUAGES_SCREEN){
            LanguagesScreen(
                onNavigateUp = {navController.navigateUp()}
            )
        }

        composable(route = AppDestinations.SEARCH_SCREEN){
            SearchScreen(
                onNavigateUp = { navController.navigateUp()}
            )
        }


    }
}
