package com.example.client

import MathServiceOuterClass

interface ClientGrpcService {

    fun sendBlockingRequest(mathServiceRequestDto: MathServiceRequestDto): MathServiceOuterClass.MathResponse
//    fun sendAsyncRequest(mathServiceRequestDto: MathServiceRequestDto): MathServiceOuterClass.MathResponse

}