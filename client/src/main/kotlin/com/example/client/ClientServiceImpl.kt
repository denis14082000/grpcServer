package com.example.client

import org.springframework.stereotype.Service

@Service
class ClientServiceImpl(
        private val clientGrpcService: ClientGrpcService
): ClientService {

    override fun count(countDto: CountDto): List<Double> {
        val request = MathServiceRequestDto(
                N = countDto.N.toDouble(),
                L = countDto.L.toDouble(),
                lm = countDto.lm.toDouble(),
                Tl = countDto.Tl.toDouble(),
                ro = countDto.ro.toDouble(),
                tEnd = countDto.tEnd.toDouble(),
                c = countDto.c.toDouble(),
                t0 = countDto.t0.toDouble()
        )

        val response = clientGrpcService.sendBlockingRequest(request)

        return response.answerList
    }

}