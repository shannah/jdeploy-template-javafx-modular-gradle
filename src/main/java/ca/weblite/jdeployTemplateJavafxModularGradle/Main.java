package ca.weblite.jdeployTemplateJavafxModularGradle;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ca.weblite.jdeployTemplateJavafxModularGradle.controller.MainController;
import ca.weblite.jdeployTemplateJavafxModularGradle.services.LocaleService;
import ca.weblite.jdeployTemplateJavafxModularGradle.services.UserSettingsService;
import ca.weblite.jdeployTemplateJavafxModularGradle.services.WindowService;

import java.util.Objects;
import java.util.ResourceBundle;


public class Main extends Application {


    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) {
        try {
            UserSettingsService.initTheme();

            updateAppWindowTitle(stage);
            stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("icon-64.png"))));
            stage.setMinHeight(300);
            stage.setMinWidth(400);
            stage.initStyle(StageStyle.DECORATED);

            MainController.load(stage);

            WindowService.setup(stage);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateAppWindowTitle(Stage stage) {
        ResourceBundle resourceBundle =
                ResourceBundle.getBundle("Main", LocaleService.getLocale());
        stage.setTitle(resourceBundle.getString("name"));
    }

}
