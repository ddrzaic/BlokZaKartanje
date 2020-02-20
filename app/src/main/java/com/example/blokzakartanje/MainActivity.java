package com.example.blokzakartanje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar sbBrojIgraca;
    TextView tvPrikazBrojIgraca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbBrojIgraca=findViewById(R.id.seekBarBrojIgraca);

        tvPrikazBrojIgraca=findViewById(R.id.textViewBrojIgraca);
        sbBrojIgraca.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                tvPrikazBrojIgraca.setText(String.valueOf(progress+2));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbBrojIgraca.setProgress(2);
    }


    public void buttonOK(View v){
        int brojIgraca=sbBrojIgraca.getProgress()+2;
        switch(brojIgraca){
            case 2:
                startActivity(new Intent(this,TwoPlayerActivity.class));
                break;
            case 3:
                startActivity(new Intent(this,ThreePlayerActivity.class));
                break;

            case 4:
                startActivity(new Intent(this,FourPlayerActivity.class));
                break;
            case 5:
                startActivity(new Intent(this,FivePlayerActivity.class));
                break;
            case 6:
                startActivity(new Intent(this,SixPlayerActivity.class));
                break;
        }
    }
}


