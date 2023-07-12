module edu.fct.wholesalemanagemetsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.jfoenix;
    requires java.sql;
    requires java.desktop;

    opens edu.fct.wholesalemanagemetsystem to javafx.fxml;
    opens edu.fct.wholesalemanagemetsystem.model to javafx.fxml;
    opens edu.fct.wholesalemanagemetsystem.controller to javafx.fxml;
    exports edu.fct.wholesalemanagemetsystem;
    exports edu.fct.wholesalemanagemetsystem.model;
    exports edu.fct.wholesalemanagemetsystem.controller;

}