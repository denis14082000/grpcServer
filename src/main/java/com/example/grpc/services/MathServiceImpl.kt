package com.example.grpc.services

import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class MathServiceImpl: MathServiceGrpc.MathServiceImplBase() {

    override fun count(request: MathServiceOuterClass.MathRequest, responseObserver: StreamObserver<MathServiceOuterClass.MathResponse>) {
        val result = (request.a + request.b) * request.t

        val response = MathServiceOuterClass.MathResponse
                .newBuilder()
                .setAnswer(result)
                .build()

        responseObserver.onNext(response)

        responseObserver.onCompleted()
    }

}