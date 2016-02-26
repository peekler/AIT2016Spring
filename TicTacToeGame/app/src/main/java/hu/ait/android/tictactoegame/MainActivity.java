package hu.ait.android.tictactoegame;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import hu.ait.android.tictactoegame.view.TicTacToeView;

public class MainActivity extends AppCompatActivity {

    private ScrollView layoutContent;
    private TicTacToeView gameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutContent = (ScrollView) findViewById(R.id.layoutContent);

        gameField =
                (TicTacToeView) findViewById(R.id.gameField);
        Button btnRestart = (Button) findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // clear the game field
                gameField.clearGameField();
            }
        });

        showMessage(getString(R.string.text_welcome));
    }

    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void showSnackBarMessage(String msg) {
        Snackbar.make(layoutContent, msg, Snackbar.LENGTH_LONG).setAction(
                R.string.action_cancel, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // this code is called when action is pressed on Snackbar
                        gameField.clearGameField();
                    }
                }
        ).show();
    }


}
