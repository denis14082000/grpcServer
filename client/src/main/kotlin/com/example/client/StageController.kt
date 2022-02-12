package com.example.client

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.chart.LineChart
import javafx.scene.chart.XYChart
import javafx.scene.control.TextField

class StageController {

    @FXML
    private lateinit var N: TextField

    @FXML
    private lateinit var L: TextField

    @FXML
    private lateinit var lm: TextField

    @FXML
    private lateinit var ro: TextField

    @FXML
    private lateinit var Tl: TextField

    @FXML
    private lateinit var tEnd: TextField

    @FXML
    private lateinit var c: TextField

    @FXML
    private lateinit var t0: TextField

    @FXML
    private lateinit var chart: LineChart<Any, Any>

    @FXML
    private fun onSendButtonClick() {
        val clientService = ClientServiceImpl(ClientGrpcServiceImpl())

        val countDto = CountDto(
                N = N.text,
                L = L.text,
                lm = lm.text,
                ro = ro.text,
                Tl = Tl.text,
                tEnd = tEnd.text,
                c = c.text,
                t0 = t0.text
        )

        val data = clientService.count(countDto)

        val series = XYChart.Series<Any, Any>()
        series.data.addAll(getArrayList(data))

        chart.data.clear()

        chart.data.add(series)
    }

    @FXML
    private fun onClearFieldsButtonClick() {

    }

    private fun getArrayList(data: List<Double>): List<XYChart.Data<Any, Any>> {
        val observableList = FXCollections.observableArrayList<XYChart.Data<Any, Any>>()

        val lNum = L.text.toDouble()
        val nNum = N.text.toDouble()

        val h = lNum / (nNum - 1)

        for ((index, elem) in data.withIndex()) {
            val x = h * index

            observableList.add(XYChart.Data(x, elem))
        }

        return observableList
    }
}