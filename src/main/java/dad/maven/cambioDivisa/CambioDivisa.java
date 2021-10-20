package dad.maven.cambioDivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {

	private TextField valor1Text;
	private TextField resulText;

	private Button cambiarButton;

	private ComboBox<Divisa> divisa1Combo;
	private ComboBox<Divisa> divisa2Combo;

	Alert error = new Alert(AlertType.ERROR);

	@Override
	public void start(Stage primaryStage) throws Exception {

		Divisa euro = new Divisa("Euro", 1.0);
		Divisa libra = new Divisa("Libra", 0.8873);
		Divisa dolar = new Divisa("Dolar", 1.2007);
		Divisa yen = new Divisa("Yen", 133.59);

		valor1Text = new TextField();
		valor1Text.setMaxWidth(75);
		valor1Text.setPromptText("0");

		divisa1Combo = new ComboBox<Divisa>();
		divisa1Combo.getItems().addAll(euro, libra, dolar, yen);
		divisa1Combo.getSelectionModel().select(0);

		resulText = new TextField();
		resulText.setMaxWidth(75);
		resulText.setPromptText("0");
		resulText.disabledProperty();

		divisa2Combo = new ComboBox<Divisa>();
		divisa2Combo.getItems().addAll(euro, libra, dolar, yen);
		divisa2Combo.getSelectionModel().select(0);

		cambiarButton = new Button("Cambiar");
		cambiarButton.setOnAction(e -> onCambiarButtonAction(e));

		HBox valor1Box = new HBox();
		valor1Box.setSpacing(5);
		valor1Box.setAlignment(Pos.CENTER);
		valor1Box.getChildren().addAll(valor1Text, divisa1Combo);

		HBox valor2Box = new HBox();
		valor2Box.setSpacing(5);
		valor2Box.setAlignment(Pos.CENTER);
		valor2Box.getChildren().addAll(resulText, divisa2Combo);

		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.setFillWidth(false);
		root.getChildren().addAll(valor1Box, valor2Box, cambiarButton);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("CambioDivisa");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void onCambiarButtonAction(ActionEvent e) {

		double cant;
 
		try {
			Divisa divisaOriginal = divisa1Combo.getSelectionModel().getSelectedItem();
			Divisa divisACambiar = divisa2Combo.getSelectionModel().getSelectedItem();

			cant = Double.parseDouble(valor1Text.getText());
			Divisa.fromTo(divisaOriginal, divisACambiar, cant);
			resulText.setText(Divisa.fromTo(divisaOriginal, divisACambiar, cant).toString());

		} catch (NumberFormatException e1) {
			error.setTitle("CambioDivisa");
			error.setHeaderText("Error");
			error.setContentText("El numero introducido no es valido.");
			error.showAndWait();
		}

	}

	public static void main(String[] args) {
		launch(args);

	}

}
