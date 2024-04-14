package com.stu71205.testefakeapii.services

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun CategoriesListScreen() {
    var categories by remember { mutableStateOf(emptyList<Categories>()) }

    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val categoriesService = retrofit.create(CategoriesService::class.java)
            categories = categoriesService.getCategories()
        }
    }

    Column {
        categories.forEach { product ->
            Categories(product)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun Categories(categorie: Categories) {
    val image = rememberImagePainter(data = categorie.image)

    Spacer(modifier = Modifier.width(8.dp))
    Text(text = categorie.title)
    Text(text = categorie.price)
    Text(text = categorie.category)
    Text(text = categorie.description)
    Image(
        painter = image,
        contentDescription = null,
//        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .width(50.dp)
            .height(50.dp)
    )
}

