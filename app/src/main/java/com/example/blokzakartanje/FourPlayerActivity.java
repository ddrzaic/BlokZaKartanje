package com.example.blokzakartanje;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class FourPlayerActivity extends AppCompatActivity {
    int[] bod = new int[]{0,0,0,0};
    int[] bod_undo = new int[]{0,0,0,0};
    TextView[] tvBod=new TextView[4];
    TextView[] tvIme=new TextView[4];
    String[] ime=new String[4];
    int mijesa=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_player);
        tvBod[0]=findViewById(R.id.cetiritvbodoviprvog);
        tvBod[1]=findViewById(R.id.cetiritvbodovidrugog);
        tvBod[2]=findViewById(R.id.cetiritvbodovitreceg);
        tvBod[3]=findViewById(R.id.cetiritvbodovicetvrtog);


        tvIme[0]=findViewById(R.id.cetiritvimeprvog);
        tvIme[1]=findViewById(R.id.cetiritvimedrugog);
        tvIme[2]=findViewById(R.id.cetiritvimetreceg);
        tvIme[3]=findViewById(R.id.cetiritvimecetvrtog);



        unosImena();
        updateMjesa();

    }


    public void undoBodovi(View v){
        for(int i=0;i<4;i++){
            bod[i]=bod_undo[i];
        }
        mijesa--;
        if(mijesa==-1)mijesa=3;
        updateMjesa();
        updateBodovi();
    }

    public void unosBodova(View v){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,android.R.style.Theme_Black_NoTitleBar);
        LayoutInflater inflater = FourPlayerActivity.this.getLayoutInflater();
        builder.setView(R.layout.dialog4bodovi);
        //In case it gives you an error for setView(View) try
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog4bodovi, null);
        builder.setView(customLayout);
        final AlertDialog show=builder.show();
        EditText[] etBod=new EditText[4];
        etBod[0]=customLayout.findViewById(R.id.editText);
        etBod[1]=customLayout.findViewById(R.id.editText2);
        etBod[2]=customLayout.findViewById(R.id.editText3);
        etBod[3]=customLayout.findViewById(R.id.editText4);
        for(int i=0;i<4;i++){
            etBod[i].setHint(ime[i]);
        }
        final EditText focus=customLayout.findViewById(R.id.editText);
        focus.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);


        final EditText FinalFocus=customLayout.findViewById(R.id.editText4);
        FinalFocus.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    InputMethodManager imm = (InputMethodManager) FourPlayerActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(FinalFocus.getWindowToken(), 0);
                    customLayout.findViewById(R.id.btn).callOnClick();
                }
                return false;
            }
        });

        customLayout.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mijesa++;
                if(mijesa==4)mijesa=0;
                updateMjesa();
                EditText[] etBod=new EditText[4];
                etBod[0]=customLayout.findViewById(R.id.editText);
                etBod[1]=customLayout.findViewById(R.id.editText2);
                etBod[2]=customLayout.findViewById(R.id.editText3);
                etBod[3]=customLayout.findViewById(R.id.editText4);
                String[] ime=new String[4];
                for(int i=0;i<4;i++){
                    bod_undo[i]=bod[i];
                    if(!etBod[i].getText().toString().equals("")){
                        bod[i]+=Integer.valueOf(etBod[i].getText().toString());
                    }
                }
                updateBodovi();
                show.dismiss();
            }
        });

    }


    void updateMjesa(){
        for(int i=0;i<4;i++){
            tvIme[i].setTextColor(Color.WHITE);
        }
        tvIme[mijesa].setTextColor(Color.MAGENTA);
    }



    void unosImena(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,android.R.style.Theme_Black_NoTitleBar);
        LayoutInflater inflater = FourPlayerActivity.this.getLayoutInflater();
        builder.setCancelable(false);
        builder.setView(R.layout.dialogcetiriimena);
        //In case it gives you an error for setView(View) try
        final View customLayout = getLayoutInflater().inflate(R.layout.dialogcetiriimena, null);
        builder.setView(customLayout);
        final AlertDialog show=builder.show();
        final EditText focus=customLayout.findViewById(R.id.editText);


        focus.requestFocus();

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        final EditText FinalFocus=customLayout.findViewById(R.id.editText4);
        FinalFocus.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    InputMethodManager imm = (InputMethodManager) FourPlayerActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(FinalFocus.getWindowToken(), 0);
                    customLayout.findViewById(R.id.btn).callOnClick();
                }
                return false;
            }
        });
        customLayout.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText[] etIme=new EditText[4];
                etIme[0]=customLayout.findViewById(R.id.editText);
                etIme[1]=customLayout.findViewById(R.id.editText2);
                etIme[2]=customLayout.findViewById(R.id.editText3);
                etIme[3]=customLayout.findViewById(R.id.editText4);


                for(int i=0;i<4;i++){
                    if(!etIme[i].getText().toString().equals("")){
                        ime[i]=etIme[i].getText().toString();
                    }
                    else ime[i]="Nema imena";
                }
                for(int i=0;i<4;i++){
                    tvIme[i].setText(ime[i]);
                }
                show.dismiss();
            }
        });
    }

    void updateBodovi(){
        for(int i=0;i<4;i++){
            tvBod[i].setText(String.valueOf(bod[i]));
        }
    }
}
