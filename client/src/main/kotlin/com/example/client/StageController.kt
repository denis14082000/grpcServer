package com.example.client

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.chart.LineChart
import javafx.scene.chart.XYChart
import javafx.scene.control.TextField

class StageController {

    @FXML
    private lateinit var x: TextField

    @FXML
    private lateinit var y: TextField

    @FXML
    private lateinit var chart: LineChart<Any, Any>

    @FXML
    private fun onSendButtonClick() {
        val clientService = ClientServiceImpl(ClientGrpcServiceImpl())

        val countDto = CountDto(
                x = x.text,
                y = y.text,
        )

        val data = clientService.count(countDto)

        val series = XYChart.Series<Any, Any>()
        series.data.addAll(getArrayList(data))

        chart.data.clear()

        chart.data.add(series)
    }

    @FXML
    private fun onClearFieldsButtonClick() {
        x.text = ""
        y.text = ""
    }

    private fun getArrayList(data: List<Double>): List<XYChart.Data<Any, Any>> {
        val observableList = FXCollections.observableArrayList<XYChart.Data<Any, Any>>()

        for (i in data.indices) {
            observableList.add(XYChart.Data(i, data[i]))
        }

        return observableList
    }
}