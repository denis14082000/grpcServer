package com.example.client

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class ClientApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(ClientApplication::class.java.getResource("hello-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
        stage.title = "Hello!"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(ClientApplication::class.java)
}