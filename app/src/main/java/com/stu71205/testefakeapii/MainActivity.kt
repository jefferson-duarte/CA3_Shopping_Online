package com.stu71205.testefakeapii

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.stu71205.testefakeapii.services.CategoriesListScreen
import com.stu71205.testefakeapii.services.ProductListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductListScreen()
//            CategoriesListScreen()
        }
    }
}