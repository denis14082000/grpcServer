package org.example.grpc.services

import MathServiceGrpc
import MathServiceOuterClass
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class MathServiceImpl : MathServiceGrpc.MathServiceImplBase() {

    override fun count(request: MathServiceOuterClass.MathRequest, responseObserver: StreamObserver<MathServiceOuterClass.MathResponse>) {
        val h = request.l / (request.n - 1) //шаг сетки по пространственной координате
        val tau: Double = request.t / 100 //шаг сетки по времени

        val T = mutableListOf<Double>()

        //поле температуры в начальный момент времени
        for (i in 1..request.n.toLong()) {
            T.add(request.t0)
        }
        //интегрирование нестационарного уравнения теплопроводности
        var time = 0.0

        while (time < request.t) {
            time += tau
            //определение начальных прогоночных коэффициентов на основе левого
            //ограничного условия

            val alpha = mutableListOf<Double>()
            val beta = mutableListOf<Double>()

            alpha.add(0,0.0)
            beta.add(0, request.tl)

            //определение остальных прогоночных коэффициентов
            for (i in 1 until request.n.toInt()) {
                //ai,bi,ci,fi - коэффициенти канонического представления СЛАУ с
                //трехдиагональной матрицей

                val ai = request.lambda / h * h
                val bi = (2 * request.lambda) / (h * h) + request.ro * (request.c / tau)
                val ci = request.lambda / (h * h)
                val fi = (-request.ro * request.c * T[i]) / tau

                //alfa(i), beta(i) - прогоночные коэффициенты
                val temp = ai / (bi - ci * alpha[i - 1])
                val temp2 = (ci * beta[i - 1] - fi) / (bi - ci * alpha[i - 1])

                alpha.add(i, temp)
                beta.add(i, temp2)
            }
                //определение значения температуры на правой границе

            T.set(request.n.toInt() - 1, request.tr)

            //пределение неизвестного поля температуры
            for (i in request.n.toInt() - 2 downTo 1) {
                val temp = (alpha[i] * T[i+1]) + beta[i]
                T.set(i, temp)
            }
        }

        val response = MathServiceOuterClass.MathResponse
                .newBuilder()
                .addAllAnswer(T)
                .build()

        responseObserver.onNext(response)

        responseObserver.onCompleted()
    }

}