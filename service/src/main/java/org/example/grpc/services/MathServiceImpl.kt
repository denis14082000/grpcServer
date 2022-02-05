package org.example.grpc.services

import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import kotlin.math.sqrt

@GRpcService
class MathServiceImpl: MathServiceGrpc.MathServiceImplBase() {

    override fun count(request: MathServiceOuterClass.MathRequest, responseObserver: StreamObserver<MathServiceOuterClass.MathResponse>) {
        val result = (1/(2 * request.l)) * sqrt(request.h / (request.p * request.s))

        val response = MathServiceOuterClass.MathResponse
                .newBuilder()
                .setAnswer(result)
                .build()

        responseObserver.onNext(response)

        responseObserver.onCompleted()
    }

}