package com.example.client

data class MathServiceRequestDto(
        val N: Double,
        val L: Double,
        val lm: Double,
        val Tl: Double,
        val ro: Double,
        val tEnd: Double,
        val c: Double,
        val t0: Double,
)
