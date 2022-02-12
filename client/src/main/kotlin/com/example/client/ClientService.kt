package com.example.client

interface ClientService {

    fun count(countDto: CountDto): List<Double>

}