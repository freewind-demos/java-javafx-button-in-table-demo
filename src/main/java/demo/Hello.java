package demo;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.collections.FXCollections.observableArrayList;

public class Hello extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello");
        VBox root = new VBox() {{
            getChildren().add(createTable());
        }};
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    private final ObservableList<String> data = observableArrayList("AAA", "BBB", "CCC");

    private TableView<String> createTable() {
        return new TableView<String>() {{
            getColumns().add(new TableColumn<String, String>("Name") {{
                setCellValueFactory(value -> new SimpleStringProperty(value.getValue()));
            }});
            getColumns().add(new TableColumn<String, String>("Ops") {{
                setCellFactory(param -> new TableCell<String, String>() {
                    final Button btn = new Button("-") {{
                        setOnAction(event -> data.remove(getIndex()));
                    }};

                    @Override
                    public void updateItem(String item, boolean empty) {
                        setGraphic(empty ? null : btn);
                    }
                });
            }});
            setItems(data);
            setEditable(true);
        }};
    }
}
