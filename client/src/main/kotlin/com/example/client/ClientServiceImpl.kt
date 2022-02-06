package com.example.client

import org.springframework.stereotype.Service

@Service
class ClientServiceImpl(
        private val clientGrpcService: ClientGrpcService
): ClientService {

    override fun count(countDto: CountDto): Double {
        val request = MathServiceRequestDto(
                l = countDto.l.toDouble(),
                h = countDto.h.toDouble(),
                p = countDto.p.toDouble(),
                s = countDto.s.toDouble(),
        )

        val response = clientGrpcService.sendBlockingRequest(request)

        return response.answer
    }

}