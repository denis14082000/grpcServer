package com.example.client

import com.example.client.CountDto

interface ClientService {

    fun count(countDto: CountDto): Double

}