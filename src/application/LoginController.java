package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	public LoginModel loginModel = new LoginModel();

	@FXML
	private Label isConnected;
	@FXML
	private TextField txtUserName;
	@FXML
	private TextField txtPassword;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (loginModel.isDbConnected()) {
			isConnected.setText("Connected");
		}
		else {
			isConnected.setText("Not Connected");
		}
	}

	public void Login (ActionEvent event) {
		try {
			if (loginModel.isLogin(txtUserName.getText(), txtPassword.getText())) {
				isConnected.setText("username and passowrd is correct");
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/application/User.fxml").openStream());
				UserController userController = (UserController)loader.getController();
				userController.getUser(txtUserName.getText());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			}
			else {
				isConnected.setText("usrname and passowrd is NOT correct");
			}
		} catch (SQLException e) {
			isConnected.setText("usrname and passowrd is NOT correct");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
