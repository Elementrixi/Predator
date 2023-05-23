package com.alpashaev;

import com.alpashaev.manager.SimulationManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        SimulationManager simulationManager = new SimulationManager();
        simulationManager.startAll(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
