package br.com.pontes.jogodamemoria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SobreActivity extends AppCompatActivity {

    private TextView tv_m_vinicius;
    private TextView tv_m_vitor;
    private TextView tv_alonso;
    private TextView tv_jonas;
    private TextView tv_projeto;
    private TextView tv_linguagem;
    private TextView tv_ide;
    private ImageView iv_marcosVinicius;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sobre);
        iv_marcosVinicius = findViewById(R.id.iv_m_vinicius);
        tv_m_vinicius = findViewById(R.id.tv_m_vinicius);
        tv_m_vitor = findViewById(R.id.tv_m_vitor);
        tv_alonso = findViewById(R.id.tv_alonso);
        tv_jonas = findViewById(R.id.tv_jonas);
        tv_projeto = findViewById(R.id.tv_projeto);
        tv_ide = findViewById(R.id.tv_ide);
        tv_linguagem = findViewById(R.id.tv_linguagem);
        this.ativaLinks();
        this.removerUnderline();
    }

    private void ativaLinks() {
        tv_m_vinicius.setMovementMethod(LinkMovementMethod.getInstance());
        tv_m_vitor.setMovementMethod(LinkMovementMethod.getInstance());
        tv_alonso.setMovementMethod(LinkMovementMethod.getInstance());
        tv_jonas.setMovementMethod(LinkMovementMethod.getInstance());
        tv_projeto.setMovementMethod(LinkMovementMethod.getInstance());
        tv_linguagem.setMovementMethod(LinkMovementMethod.getInstance());
        tv_ide.setMovementMethod(LinkMovementMethod.getInstance());

  }

    private void removerUnderline(){
        Spannable s = (Spannable) tv_linguagem.getText();
        s.setSpan(new NoUnderLine(),0,s.length(), Spanned.SPAN_MARK_MARK);
        s = (Spannable) tv_ide.getText();
        s.setSpan(new NoUnderLine(),0,s.length(), Spanned.SPAN_MARK_MARK);
        s = (Spannable) tv_alonso.getText();
        s.setSpan(new NoUnderLine(),0,s.length(), Spanned.SPAN_MARK_MARK);
        s = (Spannable) tv_jonas.getText();
        s.setSpan(new NoUnderLine(),0,s.length(), Spanned.SPAN_MARK_MARK);
        s = (Spannable) tv_m_vitor.getText();
        s.setSpan(new NoUnderLine(),0,s.length(), Spanned.SPAN_MARK_MARK);
        s = (Spannable) tv_projeto.getText();
        s.setSpan(new NoUnderLine(),0,s.length(), Spanned.SPAN_MARK_MARK);
        s = (Spannable) tv_m_vinicius.getText();
        s.setSpan(new NoUnderLine(),0,s.length(), Spanned.SPAN_MARK_MARK);
    }


  public void openBrowser(View v){
        String url = (String)v.getTag();
      Intent i = new Intent();
      i.setAction(Intent.ACTION_VIEW);
      i.addCategory(Intent.CATEGORY_BROWSABLE);
      i.setData(Uri.parse(url));
      startActivity(i);
  }
}