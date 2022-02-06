package com.example.client

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.scene.chart.LineChart
import javafx.scene.chart.XYChart
import javafx.scene.control.Label
import javafx.scene.control.TextField
import org.springframework.stereotype.Component

class StageController {
    @FXML
    private lateinit var l: TextField

    @FXML
    private lateinit var f: TextField

    @FXML
    private lateinit var p: TextField

    @FXML
    private lateinit var s: TextField

    @FXML
    private lateinit var chart: LineChart<Any, Any>

    @FXML
    private fun onSendButtonClick() {
        val clientService = ClientServiceImpl(ClientGrpcServiceImpl())

        val countDto = CountDto(
                l = l.text,
                h = f.text,
                p = p.text,
                s = s.text,
        )

        val data = clientService.count(countDto)

        println(data)

        val series = XYChart.Series<Any, Any>()
        series.data.add(getArrayList(data).first())
        series.data.add(XYChart.Data(200, 500))

        chart.data.add(series)
    }

    private fun getArrayList(data: Double): ObservableList<XYChart.Data<Any, Any>> {
        return FXCollections.observableArrayList(XYChart.Data(data, data))
    }
}