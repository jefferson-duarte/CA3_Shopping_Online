package com.stu71205.testefakeapii.services

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun ProductListScreen() {
    var products by remember { mutableStateOf(emptyList<Products>()) }

    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val productService = retrofit.create(ProductService::class.java)
            products = productService.getProducts()
        }
    }

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { product ->
            ProductItem(product)
        }
    }

//    Column {
//        products.forEach { product ->
//            ProductItem(product)
//            Spacer(modifier = Modifier.height(8.dp))
//        }
//    }
}

@Composable
fun ProductItem(product: Products) {
    val image = rememberImagePainter(data = product.image)

    Spacer(modifier = Modifier.width(8.dp))
    Text(text = product.id)
    Text(text = product.title)
    Text(text = product.price)
    Text(text = product.category)
    Text(text = product.description)
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