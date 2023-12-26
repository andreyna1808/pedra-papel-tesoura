package com.example.pedrapapeltesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int userVal = 0;
    int appVal = 0;
    public void clearResult(View view) {
        userVal = 0;
        appVal = 0;
        score(appVal, userVal);

        TextView clearResult = findViewById(R.id.clearResult);
        TextView textView = findViewById(R.id.resultGame);

        textView.setText("");
        clearResult.setText("");
        clearResult.setTextColor(Color.parseColor("#003D5AFE"));
        clearResult.setBackgroundColor(Color.parseColor("#003D5AFE"));
    }

    public void selectStone(View view) {
        checkWinner("stone");
    }

    public void selectPaper(View view) {
        checkWinner("paper");
    }

    public void selectScissor(View view) {
        checkWinner("scissor");
    }

    private String generateChoice() {

        String[] options = {"stone", "paper", "scissor"};
        int generateNumber = new Random().nextInt(options.length);

        String appChoice = options[generateNumber];
        ImageView imgApp = findViewById(R.id.img_app);

        switch (appChoice) {
            case "stone":
                imgApp.setImageResource(R.drawable.pedra);
                break;
            case "paper":
                imgApp.setImageResource(R.drawable.papel);
                break;
            case "scissor":
                imgApp.setImageResource(R.drawable.tesoura);
                break;
        }

        return appChoice;
    }

    private String validationWinner(String userChoice, String appChoice) {
        if (userChoice == "stone" && appChoice == "scissor" || userChoice == "scissor" && appChoice == "paper" || userChoice == "paper" && appChoice == "stone") {
            return "user";
        } else if (userChoice == appChoice) {
            return "tied";
        }
        return "app";
    }

    private void checkWinner(String userChoice) {
        String appChoice = generateChoice();
        String validationWinner = validationWinner(userChoice, appChoice);

        TextView textView = findViewById(R.id.resultGame);
        TextView clearResult = findViewById(R.id.clearResult);

        switch (validationWinner) {
            case "user":
                userVal += 1;
                textView.setText("Parabéns você venceu!");
                textView.setTextColor(Color.parseColor("#008000"));
                break;
            case "tied":
                textView.setText("Empatou!");
                textView.setTextColor(Color.parseColor("#0000FF"));
                break;
            case "app":
                appVal += 1;
                textView.setText("Ihhh perdeu para o app!");
                textView.setTextColor(Color.parseColor("#FF0000"));
                break;
        }

        score(appVal, userVal);
        clearResult.setText("Zerar Score!");
        clearResult.setTextColor(Color.parseColor("#FFFFFF"));
        clearResult.setBackgroundColor(Color.parseColor("#0000FF"));
    }

    private void score(int appVal, int userVal) {
        TextView userValText = findViewById(R.id.userVal);
        TextView appValText = findViewById(R.id.appVal);

        userValText.setText("Você: " + userVal);
        appValText.setText("App: " + appVal);
    }

}