package br.com.pontes.jogodamemoria;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class infoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_info);
    }

    public void abrirTelaCartas(View view){
        Intent intent = new Intent(this, MainActivity.class);
        //onDestroy();
        finish();
        startActivity(intent);
    }


}