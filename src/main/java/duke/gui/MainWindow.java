package duke.gui;

import duke.Bot;
import duke.BotResult;
import duke.query.core.CoreModule;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * MainWindow handles the window GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private Label status;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Bot bot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user-profile.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/ipman-profile.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBot(Bot b) {
        this.bot = b;
        String greeting = bot.process(CoreModule.GREET_QUERY_TYPE).getResponse();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greeting, dukeImage)
        );
        status.setText(bot.getPersonality().getRandomFlavourText());
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isBlank()) {
            return;
        }
        BotResult botResult = bot.process(input);

        if (botResult.getStatus() == BotResult.BotStatus.Exit) {
            Platform.exit();
            return;
        }

        String response = botResult.getResponse();
        dialogContainer.getChildren().addAll(
               DialogBox.getUserDialog(input, userImage),
               DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
