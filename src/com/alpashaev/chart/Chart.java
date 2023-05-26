package com.alpashaev.chart;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Chart {
    public static void draw(int[][] data){
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Creature Population");

        XYChart.Series<Number, Number> lynxSeries = new XYChart.Series<>();

        XYChart.Series<Number, Number> ratSeries = new XYChart.Series<>();

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

        Rectangle lynxSymbol = new Rectangle(10, 10, Color.ORANGE);
        Rectangle ratSymbol = new Rectangle(10, 10, Color.GRAY);

        GridPane legend = new GridPane();
        legend.setHgap(10);
        legend.setPadding(new Insets(5));

        legend.add(lynxSymbol, 0, 0);
        legend.add(ratSymbol, 0, 1);
        legend.add(new javafx.scene.control.Label("Lynx"), 1, 0);
        legend.add(new javafx.scene.control.Label("Rat"), 1, 1);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(lineChart);
        borderPane.setBottom(legend);


        Scene scene = new Scene(borderPane, 1400, 600);
        Stage stage = new Stage();

        stage.setTitle("Creature Population Chart");
        stage.setScene(scene);
        stage.show();
    }

}
