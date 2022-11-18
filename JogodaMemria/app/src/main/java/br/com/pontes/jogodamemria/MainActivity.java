package br.com.pontes.jogodamemria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Controle controle = new Controle();
    private ImageButton[] figurinhas = new ImageButton[24];
    private List<ImageButton> botoesNaoAchados = new ArrayList<ImageButton>();
    private Carta[] botoes = new Carta[24];
    private List<Integer> cartasMostradas = new ArrayList<Integer>();
    private TextView tv_jogador1;
    private TextView tv_jogador2;
    private TextView tv_pts1;
    private TextView tv_pts2;
    //private TextView tv_jogadorAtual;
    private int jogador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.concatenar();
        //controle.popularlista();
        //this.definirCartaBotoes();
        this.virarCartas();
    }

    public void desvirarCarta(){
        figurinhas[cartasMostradas.get(0)].setImageResource(botoes[cartasMostradas.get(0)].getFace1());
        figurinhas[cartasMostradas.get(1)].setImageResource(botoes[cartasMostradas.get(1)].getFace1());
        ativaBotoesNaoAchados();
        Toast toast = Toast.makeText(getApplicationContext(),"Vez de "+ (jogador%2==1 ? tv_jogador2.getText():tv_jogador1.getText()), Toast.LENGTH_LONG); toast.show();
        cartasMostradas.clear();
    }

    public boolean saoIguais(Carta c1, Carta c2, int j){
        //compara se são iguais
        if((c1.getFace2() == c2 .getFace2()) ){
            Toast toast = Toast.makeText(getApplicationContext(),"Vez de "+ (jogador%2==1 ? tv_jogador2.getText():tv_jogador1.getText()), Toast.LENGTH_LONG); toast.show();
            botoesNaoAchados.remove(figurinhas[cartasMostradas.get(0)]);
            botoesNaoAchados.remove(figurinhas[cartasMostradas.get(1)]);
            ativaBotoesNaoAchados();
            if(j == 1){
                figurinhas[cartasMostradas.get(0)].setColorFilter(getResources().getColor(R.color.green));
                figurinhas[cartasMostradas.get(1)].setColorFilter(getResources().getColor(R.color.green));
                tv_pts1.setText((Integer.parseInt(tv_pts1.getText().toString())+1)+"");
            }else {
                tv_pts2.setText((Integer.parseInt(tv_pts2.getText().toString())+1)+"");
                figurinhas[cartasMostradas.get(0)].setColorFilter(getResources().getColor(R.color.yellow));
                figurinhas[cartasMostradas.get(1)].setColorFilter(getResources().getColor(R.color.yellow));
            }
            cartasMostradas.clear();
            //teste se já acharam todas as cartas
            if(botoesNaoAchados.size()==0){
                //empate
                if ((Integer.parseInt(tv_pts1.getText().toString())) == (Integer.parseInt(tv_pts2.getText().toString()))){
                    AlertDialog.Builder empate = new AlertDialog.Builder(this);
                    empate.setTitle("Empate !");
                    empate.setMessage("ambos os jogadonhes garanham... ou perderam, depende do ponto de vista");
                    empate.setPositiveButton("OK", null);
                    empate.create();
                    empate.show();
                }else if((Integer.parseInt(tv_pts1.getText().toString())) > (Integer.parseInt(tv_pts2.getText().toString()))){
                    AlertDialog.Builder alertaVenceu = new AlertDialog.Builder(this);
                    alertaVenceu.setTitle("Vitória !!");
                    alertaVenceu.setMessage("O jogador " + tv_jogador1.getText() + " venceu!");
                    alertaVenceu.setPositiveButton("OK", null);
                    alertaVenceu.create();
                    alertaVenceu.show();
                }else{
                    AlertDialog.Builder alertaVenceu = new AlertDialog.Builder(this);
                    alertaVenceu.setTitle("Vitória !!");
                    alertaVenceu.setMessage("O jogador " + tv_jogador2.getText() + " venceu!");
                    alertaVenceu.setPositiveButton("OK", null);
                    alertaVenceu.create();
                    alertaVenceu.show();
                }
            }
            return true;
        }
        return false;
    }

    public void virarCartas() {
        for (int i = 0; i < 24; i ++) {
            int finalI = i;
            figurinhas[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    figurinhas[finalI].setImageResource(botoes[finalI].getFace2());
                    cartasMostradas.add(finalI);
                    //previne o clique na mesma carta
                    figurinhas[finalI].setClickable(false);
                    figurinhas[finalI].setEnabled(false);
                   if(cartasMostradas.size()==2){
                       jogador++;
                       desativaBotoes();
                       if(!saoIguais(botoes[cartasMostradas.get(1)], botoes[cartasMostradas.get(0)], jogador%2)){
                           final Handler handler = new Handler(Looper.getMainLooper());
                           handler.postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   desvirarCarta();
                               }
                           }, 1500);
                       }

                   }
                }
            });
        }
    }
    private void concatenar(){
        tv_jogador1 = findViewById(R.id.tv_jogador1);
        tv_pts1 = findViewById(R.id.tv_pts1);
        tv_jogador2 = findViewById(R.id.tv_jogador2);
        tv_pts2 = findViewById(R.id.tv_pts2);
        //tv_jogadorAtual = findViewById(R.id.tv_jogadorAtual);

        this.figurinhas[0] = findViewById(R.id.figurinha0);
        this.figurinhas[1] = findViewById(R.id.figurinha1);
        this.figurinhas[2] = findViewById(R.id.figurinha2);
        this.figurinhas[3] = findViewById(R.id.figurinha3);
        this.figurinhas[4] = findViewById(R.id.figurinha4);
        this.figurinhas[5] = findViewById(R.id.figurinha5);
        this.figurinhas[6] = findViewById(R.id.figurinha6);
        this.figurinhas[7] = findViewById(R.id.figurinha7);
        this.figurinhas[8] = findViewById(R.id.figurinha8);
        this.figurinhas[9] = findViewById(R.id.figurinha9);
        this.figurinhas[10] = findViewById(R.id.figurinha10);
        this.figurinhas[11] = findViewById(R.id.figurinha11);
        this.figurinhas[12] = findViewById(R.id.figurinha12);
        this.figurinhas[13] = findViewById(R.id.figurinha13);
        this.figurinhas[14] = findViewById(R.id.figurinha14);
        this.figurinhas[15] = findViewById(R.id.figurinha15);
        this.figurinhas[16] = findViewById(R.id.figurinha16);
        this.figurinhas[17] = findViewById(R.id.figurinha17);
        this.figurinhas[18] = findViewById(R.id.figurinha18);
        this.figurinhas[19] = findViewById(R.id.figurinha19);
        this.figurinhas[20] = findViewById(R.id.figurinha20);
        this.figurinhas[21] = findViewById(R.id.figurinha21);
        this.figurinhas[22] = findViewById(R.id.figurinha22);
        this.figurinhas[23] = findViewById(R.id.figurinha23);
        this.desativaBotoes();
        this.inicializaListaBotoes();
    }

    public void desativaBotoes(){
        for (int i = 0; i < 24; i++) {
            this.figurinhas[i].setClickable(false);
            this.figurinhas[i].setEnabled(false);
        }
    }

    public void inicializaListaBotoes(){
        for (int i = 0; i < 24; i++) {
            botoesNaoAchados.add(figurinhas[i]);
        }
    }

    public void ativaBotoesNaoAchados(){
        for(int i = 0; i < botoesNaoAchados.size();i++){
            botoesNaoAchados.get(i).setEnabled(true);
            botoesNaoAchados.get(i).setClickable(true);
        }
    }

    public void ativaBotoes(){
        for (int i = 0; i < 24; i++) {
            this.figurinhas[i].setEnabled(true);
            this.figurinhas[i].setClickable(true);

        }
    }

   public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.comecarJogo:
                this.reinicializar();
                final EditText editText2 = new EditText(this);
                AlertDialog.Builder secondPlayer = new AlertDialog.Builder(this);
                secondPlayer.setMessage("Nome do jogador 2 (amarelo):");
                //secondPlayer.setTitle("J2");
                secondPlayer.setView(editText2);
                secondPlayer.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String jogador2 = editText2.getText().toString();
                        tv_jogador2.setText(jogador2 + "");
                    }
                });
                secondPlayer.create();
                secondPlayer.show();

                final EditText editText1 = new EditText(this);
                AlertDialog.Builder firstPlayer = new AlertDialog.Builder(this);
                firstPlayer.setMessage("Nome do jogador 1 (Verde):");
                // firstPlayer.setTitle("J1");
                firstPlayer.setView(editText1);
                firstPlayer.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String jogador1 = editText1.getText().toString();
                        tv_jogador1.setText(jogador1 + "");
                        //tv_jogadorAtual.setText("Vez de "+ jogador1);
                        //Toast toast = Toast.makeText(getApplicationContext(), "Vez de " + jogador1 +"\n", Toast.LENGTH_LONG); toast.show();

                    }
                });
                firstPlayer.create();
                firstPlayer.show();
                //Toast.makeText(getApplicationContext(), "inicializa novo jogo", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void definirCartaBotoes(){
        for(int i=0; i<24; i++){
            Carta c = controle.sortear();
            figurinhas[i].setImageResource(c.getFace1());
            botoes[i] = c;
            controle.setVetorCartas(c, i);
        }
    }
    public void reinicializar(){
        this.ativaBotoes();
        controle.popularlista();
        definirCartaBotoes();
        descolorir();
        tv_pts1.setText("0");
        tv_pts2.setText("0");
    }

    private void descolorir() {
        for (int i = 0; i < 24; i++) {
            figurinhas[i].setColorFilter(null);
        }
    }
}