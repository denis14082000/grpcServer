package com.example.client

import MathServiceGrpc
import MathServiceOuterClass
import io.grpc.Channel
import io.grpc.ManagedChannelBuilder
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class ClientGrpcServiceImpl: ClientGrpcService {

    override fun sendBlockingRequest(mathServiceRequestDto: MathServiceRequestDto): MathServiceOuterClass.MathResponse {
        val blockingStub = MathServiceGrpc.newBlockingStub(getChannel())

        val request = getRequest(mathServiceRequestDto)

        return blockingStub.count(request)
    }

//    override fun sendAsyncRequest(mathServiceRequestDto: MathServiceRequestDto): MathServiceOuterClass.MathResponse {
//        //TODO(impl)
//    }

    private fun getChannel(): Channel {
        return ManagedChannelBuilder.forTarget(SERVER_ADDRESS)
                .usePlaintext()
                .build()

    }

    private fun getRequest(mathServiceRequestDto: MathServiceRequestDto): MathServiceOuterClass.MathRequest {
        return MathServiceOuterClass.MathRequest.newBuilder()
                .setN(mathServiceRequestDto.N)
                .setL(mathServiceRequestDto.L)
                .setLambda(mathServiceRequestDto.lm)
                .setTl(mathServiceRequestDto.Tl)
                .setRo(mathServiceRequestDto.ro)
                .setT(mathServiceRequestDto.tEnd)
                .setC(mathServiceRequestDto.c)
                .setT0(mathServiceRequestDto.t0)
                .build()
    }

    companion object {
        const val SERVER_ADDRESS = "localhost:6565" //TODO(Вынести в проперти)
    }

}