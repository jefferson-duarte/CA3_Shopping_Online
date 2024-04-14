package com.stu71205.testefakeapii.services

import retrofit2.http.GET

interface ProductService {
    @GET("/products")
    suspend fun getProducts(): List<Products>
}

interface CategoriesService {
    @GET("/products/category/jewelery")
    suspend fun getCategories(): List<Categories>
}