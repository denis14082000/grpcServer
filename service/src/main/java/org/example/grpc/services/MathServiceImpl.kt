package org.example.grpc.services

import MathServiceGrpc
import MathServiceOuterClass
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import kotlin.math.cos

@GRpcService
class MathServiceImpl : MathServiceGrpc.MathServiceImplBase() {

    override fun count(request: MathServiceOuterClass.MathRequest, responseObserver: StreamObserver<MathServiceOuterClass.MathResponse>) {
        val answer = mutableListOf<Double>()

        val x = request.x

        for (i in 0 .. x.toInt()) {
            val temp = cos((i * i ) / 20.0) / ( 1 + (i * i ) / 100.0)

            answer.add(temp)
        }

        val response = MathServiceOuterClass.MathResponse
                .newBuilder()
                .addAllAnswer(answer)
                .build()

        responseObserver.onNext(response)

        responseObserver.onCompleted()
    }

}