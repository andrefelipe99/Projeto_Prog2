package tela;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Tela extends Application{
	private static Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxmlLogin/TelaLogin.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Login");
		stage.setScene(scene);
		stage.show();
		setStage(stage);
	}
	
	public void start(Stage stage, String caminho, String titulo) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(caminho));
		Scene scene = new Scene(root);
		stage.setTitle(titulo);
		stage.setScene(scene);
		stage.show();
		setStage(stage);
	}
	
	public static void fechar() {
    	Tela.getStage().close();
    	Tela tela = new Tela();
    	
    	try {
			tela.start(new Stage());
		} catch (Exception e) {
		
		}
    }	

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Tela.stage = stage;
	}
}
