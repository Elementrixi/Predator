module com.alpashaev.predator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.alpashaev to javafx.fxml;
    exports com.alpashaev;
    exports com.alpashaev.entity.animal;
    opens com.alpashaev.entity.animal to javafx.fxml;
    exports com.alpashaev.manager;
    opens com.alpashaev.manager to javafx.fxml;
    exports com.alpashaev.map;
    opens com.alpashaev.map to javafx.fxml;
}