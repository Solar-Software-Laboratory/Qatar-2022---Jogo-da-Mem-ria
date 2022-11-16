package br.com.pontes.jogodamemria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Controle controle = new Controle();
    private ImageButton[] figurinhas = new ImageButton[24];
    private Carta[] botoes = new Carta[24];
    private List<Integer> cartasMostradas = new ArrayList<Integer>();
    TextView tv_jogador1;
    TextView tv_jogador2;
    TextView tv_pts1;
    TextView tv_pts2;
    int jogador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.concatenar();
        controle.popularlista();
        this.definirCartaBotoes();
        this.clicarBotao();
    }

    public void clicarBotao() {
       virarCartas();

        //desvirarCarta();

    }
    public void desvirarCarta(){
        figurinhas[cartasMostradas.get(0)].setImageResource(botoes[cartasMostradas.get(0)].getFace1());
        figurinhas[cartasMostradas.get(1)].setImageResource(botoes[cartasMostradas.get(1)].getFace1());
        cartasMostradas.clear();
    }

    public boolean saoIguais(Carta c1, Carta c2, int j){
        if(c1.getFace2() == c2 .getFace2()){
            if(j == 1){
                tv_pts1.setText((Integer.parseInt(tv_pts1.getText().toString())+1)+"");
            }else {
                tv_pts2.setText((Integer.parseInt(tv_pts2.getText().toString())+1)+"");
            }
            cartasMostradas.clear();
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
                   if(cartasMostradas.size()==2){
                       jogador++;
                       //Toast toast = Toast.makeText(getApplicationContext(), "Jgador " + jogador%2 +"\n", Toast.LENGTH_LONG); toast.show();

                       if(!saoIguais(botoes[cartasMostradas.get(1)], botoes[cartasMostradas.get(0)], jogador%2)){
                           final Handler handler = new Handler(Looper.getMainLooper());
                           handler.postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   desvirarCarta();
                               }
                           }, 3000);
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

    }

    public void definirCartaBotoes(){
        for(int i=0; i<24; i++){
            Carta c = controle.sortear();
            figurinhas[i].setImageResource(c.getFace1());
            botoes[i] = c;
            controle.setVetorCartas(c, i);
        }
    }
}