package com.example.hw2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform