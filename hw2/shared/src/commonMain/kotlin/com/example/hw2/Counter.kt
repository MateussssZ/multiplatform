package com.example.hw2

import kotlin.random.Random

class Counter {
    private var cnt = 0

    fun Increment(): Int {
        cnt++
        return cnt
    }

    fun Decrement(): Int {
        cnt--
        return cnt
    }

    fun Random(): Int {
        cnt = Random.nextInt(0, 100)
        return cnt
    }

    fun GetCnt(): Int{
        return cnt
    }
}