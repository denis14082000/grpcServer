package com.example.client

import org.springframework.stereotype.Service

@Service
class ClientServiceImpl(
        private val clientGrpcService: ClientGrpcService
): ClientService {

    override fun count(countDto: CountDto): List<Double> {
        val request = MathServiceRequestDto(
                x = countDto.x.toDouble(),
                y = countDto.y.toLong()
        )

        val response = clientGrpcService.sendBlockingRequest(request)

        return response.answerList
    }

}