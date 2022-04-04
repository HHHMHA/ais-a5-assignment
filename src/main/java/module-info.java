module ais.a5.assignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens org.thekiddos.a5.gui to javafx.graphics, javafx.fxml;
    exports org.thekiddos.a5;
}
