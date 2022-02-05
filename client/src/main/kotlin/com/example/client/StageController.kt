package com.example.client

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TextField

class StageController {
    @FXML
    private lateinit var welcomeText: Label

    @FXML
    private lateinit var test: TextField

    @FXML
    private fun onHelloButtonClick() {
        test.text = "test"
    }
}