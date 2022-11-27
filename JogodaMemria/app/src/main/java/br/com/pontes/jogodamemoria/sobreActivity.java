package br.com.pontes.jogodamemoria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class sobreActivity extends AppCompatActivity {

    TextView tv_m_vinicius;
    TextView tv_m_vitor;
    TextView tv_alonso;
    TextView tv_jonas;
    TextView tv_projeto;
    TextView tv_linguagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        tv_m_vinicius = findViewById(R.id.tv_m_vinicius);
        tv_m_vitor = findViewById(R.id.tv_m_vitor);
        tv_alonso = findViewById(R.id.tv_alonso);
        tv_jonas = findViewById(R.id.tv_jonas);
        tv_projeto = findViewById(R.id.tv_projeto);
        tv_linguagem = findViewById(R.id.tv_linguagem);
        ativarLinks();
    }

    private void ativarLinks() {
        tv_m_vinicius.setMovementMethod(LinkMovementMethod.getInstance());
        tv_m_vitor.setMovementMethod(LinkMovementMethod.getInstance());
        tv_alonso.setMovementMethod(LinkMovementMethod.getInstance());
        tv_jonas.setMovementMethod(LinkMovementMethod.getInstance());
        tv_projeto.setMovementMethod(LinkMovementMethod.getInstance());
        tv_linguagem.setMovementMethod(LinkMovementMethod.getInstance());
    }
}