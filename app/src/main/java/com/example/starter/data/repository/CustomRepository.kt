package com.example.starter.data.repository

import com.example.starter.data.serivce.CustomService

class CustomRepository(customService: CustomService) {
    suspend fun getData(): List<String> = listOf("A", "B", "C")
}
