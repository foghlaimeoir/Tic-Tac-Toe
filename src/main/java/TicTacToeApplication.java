
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.text.Font;


public class TicTacToeApplication extends Application {
private boolean xTurn = true;
private boolean winner = false;
private Label turn = new Label("Turn: X");
private Button[] buttons = new Button[9];


    public void start(Stage window) {
        BorderPane layout = new BorderPane();

        GridPane grid = new GridPane();

        for (int i = 0; i < 9; i++) {
            buttons[i] = createButton();
        }

        grid.add(buttons[0], 1, 0);
        grid.add(buttons[1], 2, 0);
        grid.add(buttons[2], 3, 0);
        grid.add(buttons[3], 1, 1);
        grid.add(buttons[4], 2, 1);
        grid.add(buttons[5], 3, 1);
        grid.add(buttons[6], 1, 2);
        grid.add(buttons[7], 2, 2);
        grid.add(buttons[8], 3, 2);


        layout.setTop(turn);
        layout.setCenter(grid);

        Button resetButton = new Button("Play again.");
        resetButton.setFont(Font.font("Monospace", 30));

        resetButton.setOnAction((event) -> {
            for (Button button: buttons) {
                button.setText(" ");
            }
            winner = false;
            xTurn = true;
            turn.setText("Turn: X");
        });

        layout.setBottom(resetButton);
        Scene view= new Scene(layout);

        window.setScene(view);
        window.show();

    }
    public static void main(String[] args) {
        launch(TicTacToeApplication.class);
    }

    private Button createButton() {
        Button button = new Button(" ");
        button.setFont(Font.font("Monospace", 40));

        button.setOnAction((event) -> {

            if (!winner) {
                if (button.getText().equals(" ")){
                    if (xTurn) {
                        xTurn  = false;
                        button.setText("X");
                        turn.setText("Turn: O");
                    } else {
                        xTurn  = true;
                        button.setText("O");
                        turn.setText("Turn: X");
                    }
                    checkWinner();
                }
            }
        });
        return button;
    }

    private void checkWinner() {
        for (int i = 0; i < 2; i++) {
            String[] letters = {"X", "O"};

            for (int j = 0; j < 3; j++) {
                // check for winner in horizontals
                if (buttons[j].getText().equals(letters[i]) && buttons[j+1].getText().equals(letters[i]) && buttons[j+2].getText().equals(letters[i])) {
                    winner = true;
                    turn.setText(letters[i] + " wins!");
                }
                // check for winner in verticals

                if (buttons[j].getText().equals(letters[i]) && buttons[j+3].getText().equals(letters[i]) && buttons[j+6].getText().equals(letters[i])) {
                    winner = true;
                    turn.setText(letters[i] + " wins!");
                }
                // check for winner in left diagonal
                if (j == 0) {
                    if (buttons[j].getText().equals(letters[i]) && buttons[j+4].getText().equals(letters[i]) && buttons[j+8].getText().equals(letters[i])) {
                        winner = true;
                        turn.setText(letters[i] + " wins!");
                    }
                }

                // check for winner in right diagonal
                if (j == 2) {
                    if (buttons[j].getText().equals(letters[i]) && buttons[j+2].getText().equals(letters[i]) && buttons[j+4].getText().equals(letters[i])) {
                        winner = true;
                        turn.setText("The end!");
                    }
                }
            }
        }

    }

}
