package com.alpashaev.chart;


import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Chart {
    public static void draw(int[][] data){
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Creature Population");
        lineChart.setLegendVisible(true);

        XYChart.Series<Number, Number> lynxSeries = new XYChart.Series<>();
        lynxSeries.setName("Lynx");

        XYChart.Series<Number, Number> ratSeries = new XYChart.Series<>();
        ratSeries.setName("Rat");

        for (int k = 0; k < data.length; k++) {
            int[] stepData = data[k];
            int lynxCount = stepData[0];
            int ratCount = stepData[1];

            lynxSeries.getData().add(new XYChart.Data<>(k, lynxCount));
            ratSeries.getData().add(new XYChart.Data<>(k, ratCount));
        }

        String hideSymbolsCss = ".chart-line-symbol {-fx-background-color: transparent, transparent;}";
        lineChart.applyCss();
        lineChart.getStylesheets().add(
                "data:text/css," + hideSymbolsCss
        );

        lineChart.getData().add(lynxSeries);
        lineChart.getData().add(ratSeries);

        Scene scene = new Scene(lineChart, 1400, 600);
        Stage stage = new Stage();

        stage.setTitle("Creature Population Chart");
        stage.setScene(scene);
        stage.show();
    }

}
