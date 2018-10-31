package com.rp.firebaseauthdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Database extends AppCompatActivity {

    RadioButton coroa1, coroa2, coroa3;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        btnSubmit = findViewById(R.id.btnSubmit);
        coroa1 = findViewById(R.id.rbtnCoroa1);
        coroa2 = findViewById(R.id.rbtnCoroa2);
        coroa3 = findViewById(R.id.rbtnCoroa3);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        String str="";
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbtnCoroa1:
                if(checked)
                    str = "Coroa 1 Selected";
                break;
            case R.id.rbtnCoroa2:
                if(checked)
                    str = "Coroa 2 Selected";
                break;
            case R.id.rbtnCoroa3:
                if(checked)
                    str = "Coroa 3 Selected";
                break;
        }



    }
}
