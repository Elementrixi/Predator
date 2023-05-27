package com.alpashaev.manager;

import com.alpashaev.chart.Chart;
import com.alpashaev.entity.animal.Creature;
import com.alpashaev.entity.animal.Lynx;
import com.alpashaev.entity.animal.Rat;
import com.alpashaev.map.Forest;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class SimulationManager extends Application {
    public static int[][] data;

    public static void moveLynx(int[] coordinates) {
        int row = coordinates[0];
        int col = coordinates[1];
        Lynx lynx = (Lynx) Forest.getCreature(row, col);

        Integer[] randomDirections = Forest.randomDirections();
        boolean moveComplete = false;
        for (int i = 0; i < 4 && (!moveComplete); i++) {
            switch (randomDirections[i]) {
                case 1:
                    if (lynx.topFlag)
                        break;

                    if (Forest.getCreature(coordinates[0] - 1, coordinates[1]) == null) {
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 1);
                        moveComplete = true;
                    }
                    break;
                case 2:
                    if (lynx.rightFlag)
                        break;

                    if (Forest.getCreature(coordinates[0], coordinates[1] + 1) == null) {
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 2);
                        moveComplete = true;
                    }
                    break;
                case 3:
                    if (lynx.bottomFlag)
                        break;

                    if (Forest.getCreature(coordinates[0] + 1, coordinates[1]) == null) {
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 3);
                        moveComplete = true;
                    }
                    break;
                case 4:
                    if (lynx.leftFlag)
                        break;

                    if (Forest.getCreature(coordinates[0], coordinates[1] - 1) == null) {
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 4);
                        moveComplete = true;
                    }
                    break;
            }
        }
    }

    public static void moveRat(int[] coordinates) {
        int row = coordinates[0];
        int col = coordinates[1];

        Rat rat = (Rat) Forest.getCreature(row, col);

        Integer[] randomDirections = Forest.randomDirections();
        boolean moveComplete = false;

        for (int i = 0; i < 4 && (!moveComplete); i++) {
            switch (randomDirections[i]) {
                case 1:
                    if (rat.topFlag)
                        break;

                    if (Forest.getCreature(coordinates[0] - 1, coordinates[1]) == null) {
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 1);
                        moveComplete = true;
                    }
                    break;
                case 2:
                    if (rat.rightFlag)
                        break;

                    if (Forest.getCreature(coordinates[0], coordinates[1] + 1) == null) {
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 2);
                        moveComplete = true;
                    }
                    break;
                case 3:
                    if (rat.bottomFlag)
                        break;

                    if (Forest.getCreature(coordinates[0] + 1, coordinates[1]) == null) {
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 3);
                        moveComplete = true;
                    }
                    break;
                case 4:
                    if (rat.leftFlag)
                        break;

                    if (Forest.getCreature(coordinates[0], coordinates[1] - 1) == null) {
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 4);
                        moveComplete = true;
                    }
                    break;
            }
        }
    }

    public static void simulation(Forest forest) {
        int[][] lynxPositions = new int[Forest.numberOfLynx][2];
        lynxPositions = Forest.lynxPositions();
        int[] position = new int[2];

        //Lynx movement or eat Rat if it's possible
        for (int i = 0; i < Forest.numberOfLynx; i++) {
            position = lynxPositions[i];

            //Error catching
            if (position[0] < 0 || position[1] < 0 || position[0] > (Forest.ROWS - 1) || position[1] > (Forest.COLUMNS - 1)) {
                System.out.println("Sim error");
                System.exit(-1);
            }

            //Eating
            if (forest.preyNear((Lynx) Forest.getCreature(position))) {
                Lynx lynx = (Lynx) Forest.getCreature(position);
                lynx.timeSinceEat = 0;
                lynx.timeSinceBreed++;
                lynx.eat(position);
            } else { //Moving
                Lynx lynx = (Lynx) Forest.getCreature(position);
                lynx.timeSinceEat++;
                lynx.timeSinceBreed++;
                moveLynx(position);
            }
        }
        int[][] ratPositions = new int[Forest.numberOfRats][2];
        ratPositions = Forest.ratPositions();

        for (int i = 0; i < Forest.numberOfRats; i++) {
            position = ratPositions[i];

            Rat rat = (Rat) Forest.getCreature(position);
            rat.timeSinceBreed++;
            moveRat(position);
        }


        Forest.rowColScanClear();


//Breeding for Lynx
        lynxPositions = Forest.lynxPositions();
        int numberOfLynx = Forest.numberOfLynx;
        for (int i = 0; i < numberOfLynx; i++) {
            //Get lynx Coordinates
            position = lynxPositions[i];
            int row = position[0];
            int col = position[1];

            if (row < 0 || col < 0) {
                break;
            }
            forest.breed(Forest.getCreature(position));
        }

//Breeding for Rat
        ratPositions = Forest.ratPositions();
        int tempNumberOfRats = Forest.numberOfRats;

        for (int i = 0; i < tempNumberOfRats; i++) {
            //Get rat Coordinates
            position = ratPositions[i];
            int row = position[0];
            int col = position[1];

            if (row < 0 || col < 0) {
                break;
            }

            forest.breed(Forest.getCreature(row, col));
        }

//Hunger
        lynxPositions = Forest.lynxPositions();
        numberOfLynx = Forest.numberOfLynx;

        for (int i = 0; i < numberOfLynx; i++) {
            forest.starving((Lynx) Forest.getCreature(lynxPositions[i][0], lynxPositions[i][1]));
        }
    }

    private static void fieldStatsOut(int stepsAmount) {
        Stage stage1 = new Stage();
        stage1.setTitle("Statistics");
        VBox vbox = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vbox);
        Scene scene = new Scene(scrollPane, 300, 400);
        int tmp, minL = Integer.MAX_VALUE, maxL = -1, minR = Integer.MAX_VALUE, maxR = -1;
        for (int i = 0; i < stepsAmount; i++) {
            tmp = i;
            tmp++;
            if (minL > data[i][0]) minL = data[i][0];
            else if (maxL < data[i][0]) maxL = data[i][0];

            if (minR > data[i][1]) minR = data[i][1];
            else if (maxR < data[i][1]) maxR = data[i][1];
            String str = "Lynxes: " + data[i][0] + " Rats: " + data[i][1] + " step: " + tmp;
            Label label = new Label(str);
            vbox.getChildren().add(label);
        }
        String str = "MaxL: " + maxL + " minL: " + minL + " maxR: " + maxR + " minR: " + minR;
        Label label = new Label(str);
        vbox.getChildren().add(label);
        stage1.setScene(scene);
        stage1.show();
    }

    private static void dataCollector(int[] arr, int i) {
        data[i][0] = arr[0];
        data[i][1] = arr[1];
    }

    public void startAll(Stage stage) {
        start(stage);
    }

    private void textParser(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[\\D]", ""));
                }
            }
        });
    }
    @Override
    public void start(Stage stage) {
        int[] rats = new int[1];
        int[] lynx = new int[1];
        int[] hungerTime = new int[1];
        int[] maxStep = new int[1];

        VBox vpane = new VBox();

        ScrollPane scrollPane = new ScrollPane();
        GridPane gridPane = new GridPane();
        scrollPane.setContent(gridPane);

        Scale zoomTransform = new Scale(1.0, 1.0);
        gridPane.getTransforms().add(zoomTransform);

        Scene scene = new Scene(vpane, 300, 300);
        Scene scene1 = new Scene(scrollPane, 1500, 750);

        Stage stage1 = new Stage();
        stage1.setTitle("Simulation");

        Label label = new Label("Rats");
        TextField ratField = new TextField();
        Label label1 = new Label("Lynx");
        TextField lynxField = new TextField();
        Label label2 = new Label("Hunger time");
        TextField hungerTimeField = new TextField();
        Label label3 = new Label("Max step");
        TextField maxStepField = new TextField();

        Button buttonSet = new Button("Set");
        Button buttonDefaultData = new Button("Default data");
        Button buttonStart = new Button("Start");
        Button buttonClose = new Button("Close");
        Button buttonChart = new Button("Chart");

        vpane.getChildren().addAll(label, ratField, label1, lynxField, label2, hungerTimeField, label3, maxStepField, buttonSet, buttonDefaultData, buttonStart, buttonChart);

        textParser(ratField);
        textParser(lynxField);
        textParser(hungerTimeField);
        textParser(maxStepField);

        final int[][] arr = new int[1][1];
        final int[] i = {0};
        Forest[] forest = new Forest[1];
        buttonSet.setOnAction(e -> {
            rats[0] = Integer.parseInt(ratField.getText());
            lynx[0] = Integer.parseInt(lynxField.getText());
            hungerTime[0] = Integer.parseInt(hungerTimeField.getText());
            maxStep[0] = Integer.parseInt(maxStepField.getText());
            System.out.println("Rats: " + rats[0] + " Lynx: " + lynx[0] + " Hunger time: " + hungerTime[0] + " Max step: " + maxStep[0]);
            Lynx.hungerTimeMax = hungerTime[0];
            forest[0] = new Forest(rats[0], lynx[0]);
            data = new int[maxStep[0]][2];
        });

        buttonDefaultData.setOnAction(e -> {
            rats[0] = 100;
            lynx[0] = 50;
            hungerTime[0] = 8;
            maxStep[0] = 1500;
            Lynx.hungerTimeMax = hungerTime[0];
            forest[0] = new Forest(rats[0], lynx[0]);
            data = new int[maxStep[0]][2];
            System.out.println("Rats: " + rats[0] + " Lynx: " + lynx[0] + " Hunger time: " + hungerTime[0] + " Max step: " + maxStep[0]);
        });

        buttonStart.setOnAction(e -> {
            AnimationTimer animationTimer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (Forest.numberOfRats != 0 && Forest.numberOfLynx != 0 && i[0] != maxStep[0]) {
                        simulation(forest[0]);
                        arr[0] = Forest.fieldStat().clone();
                        updateGrid(gridPane);
                        dataCollector(arr[0], i[0]);
                        i[0]++;
                        System.out.println(i[0]);
                    } else {
                        fieldStatsOut(i[0]);
                        System.out.println("Simulation ended up at step " + i[0]);
                        stop();
                    }
                }
            };

            animationTimer.start();

            stage1.setScene(scene1);
            stage1.show();
        });

        scrollPane.addEventFilter(ScrollEvent.ANY, event -> {
            if (event.isControlDown()) {
                double delta = 1.2;
                if (event.getDeltaY() < 0) {
                    delta = 1.0 / delta;
                }
                zoomTransform.setX(zoomTransform.getX() * delta);
                zoomTransform.setY(zoomTransform.getY() * delta);
                event.consume();
            }
        });

        buttonClose.setOnAction(e -> stage1.close());

        buttonChart.setOnAction(e -> {
            if (maxStep[0] != 0) {
                Chart.draw(data);
            }
        });

        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    private static Creature[][] forestPrev = new Creature[200][200];
    private void updateGrid(GridPane gridPane) {
        gridPane.getChildren().clear();

        for (int i = 0; i < Forest.ROWS; i++) {
            for (int j = 0; j < Forest.COLUMNS; j++) {
                if (forestPrev[i][j] == Forest.forest[i][j]) continue;
                Creature creature = Forest.getCreature(i, j);
                gridPane.setPrefSize(10, 10);
                Rectangle rectangle = new Rectangle(10, 10, Color.WHITE);
                if (creature != null) {
                    if (creature instanceof Rat) {
                        rectangle.setFill(Color.GRAY);
                    } else if (creature instanceof Lynx) {
                        rectangle.setFill(Color.ORANGE);
                    }
                }

                gridPane.add(rectangle, j, i);
                forestPrev[i][j] = Forest.forest[i][j];
            }
        }
    }

}

