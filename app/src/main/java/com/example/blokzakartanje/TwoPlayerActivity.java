package com.example.blokzakartanje;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class TwoPlayerActivity extends AppCompatActivity {
    int[] bod = new int[]{0,0};
    int[] bod_undo = new int[]{0,0};
    TextView[] tvBod=new TextView[2];
    TextView[] tvIme=new TextView[2];
    String[] ime=new String[2];
    int mijesa=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);
        tvBod[0]=findViewById(R.id.dvatvbodoviprvog);
        tvBod[1]=findViewById(R.id.dvatvbodovidrugog);



        tvIme[0]=findViewById(R.id.dvatvimeprvog);
        tvIme[1]=findViewById(R.id.dvatvimedrugog);
      




        unosImena();
        updateMjesa();

    }


    public void undoBodovi(View v){
        for(int i=0;i<2;i++){
            bod[i]=bod_undo[i];
        }
        mijesa--;
        if(mijesa==-1)mijesa=1;
        updateMjesa();
        updateBodovi();
    }

    public void unosBodova(View v){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,android.R.style.Theme_Black_NoTitleBar);
        LayoutInflater inflater = TwoPlayerActivity.this.getLayoutInflater();
        builder.setView(R.layout.dialog2bodovi);
        //In case it gives you an error for setView(View) try
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog2bodovi, null);
        builder.setView(customLayout);
        final AlertDialog show=builder.show();
        EditText[] etBod=new EditText[2];
        etBod[0]=customLayout.findViewById(R.id.editText);
        etBod[1]=customLayout.findViewById(R.id.editText2);
        

        for(int i=0;i<2;i++){
            etBod[i].setHint(ime[i]);
        }
        final EditText focus=customLayout.findViewById(R.id.editText);
        focus.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);


        final EditText FinalFocus=customLayout.findViewById(R.id.editText2);


        FinalFocus.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    InputMethodManager imm = (InputMethodManager) TwoPlayerActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
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
                if(mijesa==2)mijesa=0;
                updateMjesa();
                EditText[] etBod=new EditText[2];
                etBod[0]=customLayout.findViewById(R.id.editText);
                etBod[1]=customLayout.findViewById(R.id.editText2);
                

                String[] ime=new String[2];
                for(int i=0;i<2;i++){
                    bod_undo[i]=bod[i];
                    if(!etBod[i].getText().toString().equals("")){
                        bod[i]+=Integer.valueOf(etBod[i].getText().toString());
                    }
                }
                updateBodovi();
                InputMethodManager imm = (InputMethodManager) TwoPlayerActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(FinalFocus.getWindowToken(), 0);
                show.dismiss();
            }
        });

    }


    void updateMjesa(){
        for(int i=0;i<2;i++){
            tvIme[i].setTextColor(Color.WHITE);
        }
        tvIme[mijesa].setTextColor(Color.MAGENTA);
    }



    void unosImena(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,android.R.style.Theme_Black_NoTitleBar);
        LayoutInflater inflater = TwoPlayerActivity.this.getLayoutInflater();
        builder.setCancelable(false);
        builder.setView(R.layout.dialogdvaimena);
        //In case it gives you an error for setView(View) try
        final View customLayout = getLayoutInflater().inflate(R.layout.dialogdvaimena, null);
        builder.setView(customLayout);
        final AlertDialog show=builder.show();
        final EditText focus=customLayout.findViewById(R.id.editText);
        focus.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);


        final EditText FinalFocus=customLayout.findViewById(R.id.editText2);


        FinalFocus.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    InputMethodManager imm = (InputMethodManager) TwoPlayerActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(FinalFocus.getWindowToken(), 0);
                    customLayout.findViewById(R.id.btn).callOnClick();
                }
                return false;
            }
        });
        customLayout.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText[] etIme=new EditText[2];
                etIme[0]=customLayout.findViewById(R.id.editText);
                etIme[1]=customLayout.findViewById(R.id.editText2);



                for(int i=0;i<2;i++){
                    if(!etIme[i].getText().toString().equals("")){
                        ime[i]=etIme[i].getText().toString();
                    }
                    else ime[i]="Nema imena";
                }
                for(int i=0;i<2;i++){
                    tvIme[i].setText(ime[i]);
                }
                InputMethodManager imm = (InputMethodManager) TwoPlayerActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(FinalFocus.getWindowToken(), 0);
                show.dismiss();
            }
        });
    }

    void updateBodovi(){
        for(int i=0;i<2;i++){
            tvBod[i].setText(String.valueOf(bod[i]));
        }
    }

}


