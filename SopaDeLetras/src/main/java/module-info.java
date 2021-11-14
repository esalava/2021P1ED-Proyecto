module espol.sopadeletras {
    requires javafx.controls;
    requires javafx.fxml;

    opens espol.sopadeletras to javafx.fxml;
    exports espol.sopadeletras;
}
