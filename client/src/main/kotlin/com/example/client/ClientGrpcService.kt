package com.example.client

import MathServiceOuterClass
import com.example.client.MathServiceRequestDto

interface ClientGrpcService {

    fun sendBlockingRequest(mathServiceRequestDto: MathServiceRequestDto): MathServiceOuterClass.MathResponse
//    fun sendAsyncRequest(mathServiceRequestDto: MathServiceRequestDto): MathServiceOuterClass.MathResponse

}