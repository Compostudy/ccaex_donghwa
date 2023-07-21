package com.compostudy.android.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.compostudy.android.common.model.response.DiscoverMovieResult


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    Column(
        Modifier.fillMaxSize()
    ) {

        viewModel.getMovieResults()
        val movieResultState by viewModel.movieResults.collectAsState()

        var clickedMovie by remember {
            mutableStateOf(
                DiscoverMovieResult(
                    adult = false,
                    backdrop_path = null,
                    genre_ids = emptyList(),
                    id = 0,
                    original_language = "",
                    original_title = "",
                    overview = "",
                    popularity = 0.0,
                    poster_path = null,
                    release_date = "",
                    title = "",
                    video = false,
                    vote_average = 0.0,
                    vote_count = 0
                )
            )
        }

        DetailMovieInfo(clickedMovie)

        Spacer(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
                .background(color = Color.Black)
        )

        MovieList(movieResultState) { movie ->
            clickedMovie = movie
        }
    }
}

@Composable
fun DetailMovieInfo(clickedMovie: DiscoverMovieResult) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth()
            .background(Color.Black)
            .padding(16.dp)
            .wrapContentHeight()
    ) {
        Row {
            Card(
                modifier = Modifier
                    .height(75.dp)
                    .aspectRatio(2 / 3f)
                    .align(Alignment.CenterVertically),
                shape = RoundedCornerShape(2.dp)
            )
            {
                GetImageFromMovieApi(path = clickedMovie.poster_path ?: "")
            }

            Column(
                Modifier.padding(start = 24.dp)
            ) {
                Text(
                    text = clickedMovie.title,
                    Modifier.padding(bottom = 4.dp),
                    fontSize = 16.sp,
                    color = Color.White,
                    fontStyle = FontStyle.Italic,
                )
                Text(
                    text = clickedMovie.overview,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun MovieList(movieList: List<DiscoverMovieResult>, onItemClick: (DiscoverMovieResult) -> Unit) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        state = rememberLazyGridState(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
    )
    {
        items(movieList.size) { index ->

            val item = movieList[index]

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onItemClick(item) }
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(2 / 3f),
                    shape = RoundedCornerShape(4.dp)
                )
                {
                    GetImageFromMovieApi(path = item.poster_path ?: "")
                }

                Spacer(modifier = Modifier.height(3.dp))

                Box(
                    modifier = Modifier
                        .background(color = Color.Transparent, shape = RoundedCornerShape(2.dp))
                        .fillMaxSize()
                        .aspectRatio(2 / 1f)
                        .padding(),
                )
                {
                    Text(text = item.title, color = Color.Black, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun GetImageFromMovieApi(path: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500$path",
        contentDescription = null,
        Modifier.fillMaxSize()
    )
}