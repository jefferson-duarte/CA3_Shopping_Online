package com.stu71205.testefakeapii.services

data class Products(
    val id: String,
    val title: String,
    val price: String,
    val category: String,
    val description: String,
    val image: String,
)

data class Categories(
    val title: String,
    val price: String,
    val category: String,
    val description: String,
    val image: String
)