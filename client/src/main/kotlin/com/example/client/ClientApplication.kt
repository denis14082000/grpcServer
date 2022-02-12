package com.example.client

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class ClientApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(ClientApplication::class.java.getResource("client-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 1100.0, 700.0)
        stage.title = "Client!"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(ClientApplication::class.java)
}