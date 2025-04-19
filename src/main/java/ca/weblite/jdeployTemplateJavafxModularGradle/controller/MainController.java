package ca.weblite.jdeployTemplateJavafxModularGradle.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ca.weblite.jdeployTemplateJavafxModularGradle.Main;
import ca.weblite.jdeployTemplateJavafxModularGradle.services.LocaleService;
import ca.weblite.jdeployTemplateJavafxModularGradle.services.UserSettingsService;
import org.tinylog.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public BorderPane mainPane;
    public TextArea textArea;
    public ChoiceBox<Locale> localChoiceBox;
    public Stage stage;



    public static void load(Stage stage) {
        try {
            ResourceBundle resourceBundle =
                    ResourceBundle.getBundle("Main", LocaleService.getLocale());
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainController.class.getResource("main.fxml"),
                    resourceBundle);
            Parent node = fxmlLoader.load();
            MainController controller = fxmlLoader.getController();
            controller.stage = stage;
            controller.stage.setScene(new Scene(node));

        } catch (IOException e) {
            Logger.error(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.setText(UserSettingsService.getSavedText());
        textArea.textProperty().addListener((e,o,n)-> UserSettingsService.setSavedText(n));

        localChoiceBox.getItems().add(Locale.ENGLISH);
        localChoiceBox.getItems().add(Locale.FRENCH);
        localChoiceBox.setValue(LocaleService.getLocale());
        localChoiceBox.valueProperty().addListener((e,o,n)-> {
            LocaleService.setLocale(n);
            load(stage);
            Main.updateAppWindowTitle(stage);
        });
    }

    public void toDark() {
        UserSettingsService.setTheme(UserSettingsService.DARK);
    }

    public void toLight() {
        UserSettingsService.setTheme(UserSettingsService.LIGHT);
    }


}
