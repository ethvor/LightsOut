package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int GRID_SIZE = 3;
    private GridLayout grid;
    private boolean cellState [][];

    private TextView score;




    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Button current = (Button) v;
            for (int i = 0; i < grid.getChildCount(); i++) {
                Button gridButton = (Button) grid.getChildAt(i);
                if(gridButton == current){
                    int row = i / GRID_SIZE;
                    int col = i % GRID_SIZE;

                    if (cellState[row][col]){
                        cellState[row][col] = false;
                    }else{
                        cellState[row][col] = true;
                    }

                }
            }

        recolor();
        int lightsOn = countLights();
        String scoreString = "Score: " + lightsOn;
        score.setText(scoreString);
        }

    };

    View.OnClickListener resetListener = new View.OnClickListener(){
        public void onClick(View v) {
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    cellState[i][j] = false;
                }
            }
            recolor();

            int lightsOn = countLights();
            String scoreString = "Score: " + lightsOn;
            score.setText(scoreString);
        }

    };

    View.OnClickListener randomListener = new View.OnClickListener() {
        public void onClick(View v) {


            randomize();
            recolor();

            int lightsOn = countLights();
            String scoreString = "Score: " + lightsOn;
            score.setText(scoreString);

        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cellState = new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}};

        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.light_grid);
        score = findViewById(R.id.score_counter);


        randomize();

        recolor();

        int lightsOn = countLights();
        String scoreString = "Score: " + lightsOn;
        score.setText(scoreString);

        for (int i = 0; i < grid.getChildCount(); i++) {
            Button currButton = (Button) grid.getChildAt(i);
            currButton.setOnClickListener(buttonListener);

        }

        Button resetButton = findViewById(R.id.reset_button); //bind listeners to buttons
        resetButton.setOnClickListener(resetListener);

        Button randomizeButton = findViewById(R.id.random_button);
        randomizeButton.setOnClickListener(randomListener);

    }

    public void recolor(){
        for (int i = 0; i < grid.getChildCount(); i++) {
            Button gridButton = (Button) grid.getChildAt(i);

            // Find the button's row and col
            int row = i / GRID_SIZE;
            int col = i % GRID_SIZE;

            if (cellState[row][col] == true) {
                gridButton.setBackgroundColor(getColor(R.color.blue_500));
            } else {
                gridButton.setBackgroundColor(getColor(R.color.black));
            }
        }
    }

    public void randomize(){
        Random random = new Random();
        for(int i =0; i< GRID_SIZE; i++){
            for(int j =0; j< GRID_SIZE; j++){
                cellState[i][j] = random.nextBoolean();
            }
        }
    }

    public int countLights(){
        int count = 0;
        for (int i=0; i < GRID_SIZE; i++){
            for (int j = 0; j < GRID_SIZE; j++){
                if(cellState[i][j]) { //bool
                    count++;
                }
            }
        }
        return count;
    }


}