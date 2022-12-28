package br.com.pontes.jogodamemoria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputFilter;
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
    private Carta[] botoes = new Carta[24];
    private ImageButton[] figurinhas = new ImageButton[24];
    private List<ImageButton> botoesNaoAchados = new ArrayList<ImageButton>();
    private List<Integer> indiceTempCartasMostradas = new ArrayList<Integer>();
    private TextView tv_jogador1;
    private TextView tv_jogador2;
    private TextView tv_pts1;
    private TextView tv_pts2;
    private TextView tv_flJ1;
    private TextView tv_flJ2;
    private byte jogador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        setTitle("Jogo da Memória");
        this.concatenar();
        //mostrarOrientacoes();
        this.virarCartas();
        this.desativarBotoes();
        jogarNovosJogadores();
    }

    // faz com que as cartas nos índices i1 e i2 voltem 
    // com o drawable padrão das "costas" da carta
    public void desvirarCarta(int i1, int i2){
        figurinhas[i1].setImageResource(botoes[i1].getFace1());
        figurinhas[i2].setImageResource(botoes[i2].getFace1());
        indiceTempCartasMostradas.clear();
    }

    public void verificarIgualdade(int i1, int i2, int j){
        //compara se são iguais
        if((botoes[i1].getFace2() == botoes[i2] .getFace2()) ){
            botoesNaoAchados.remove(figurinhas[i1]);
            botoesNaoAchados.remove(figurinhas[i2]);
            indiceTempCartasMostradas.clear();
            ativaBotoesNaoAchados();
            if(j == 1){
                figurinhas[i1].setColorFilter(getResources().getColor(R.color.green));
                figurinhas[i2].setColorFilter(getResources().getColor(R.color.green));
                tv_pts1.setText((Integer.parseInt(tv_pts1.getText().toString())+1)+"");
            }else {
                figurinhas[i2].setColorFilter(getResources().getColor(R.color.yellow));
                figurinhas[i1].setColorFilter(getResources().getColor(R.color.yellow));
                tv_pts2.setText((Integer.parseInt(tv_pts2.getText().toString())+1)+"");
            }
            //teste se já acharam todas as cartas
            this.verificarTermino();
        }else {
            jogador++;
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    desvirarCarta(i1, i2);
                    ativaBotoesNaoAchados();
                }
            }, 1500);
        }
        Toast t = Toast.makeText(getApplicationContext(), "VEZ DE " + ((jogador%2==1)?tv_jogador1.getText():tv_jogador2.getText()), Toast.LENGTH_SHORT); t.show();
    }
    public void verificarTermino(){
        if(botoesNaoAchados.size()==0){
            //empate
            if ((Integer.parseInt(tv_pts1.getText().toString())) == (Integer.parseInt(tv_pts2.getText().toString()))){
                gerarAlertDialogResultado("Empate", "Ambos acertaram a mesma quantidade de cartas");
            }else if((Integer.parseInt(tv_pts1.getText().toString())) > (Integer.parseInt(tv_pts2.getText().toString()))){
                this.gerarAlertDialogResultado("Vitória", "O jogador " + tv_jogador1.getText() + " venceu!");

            }else{
                this.gerarAlertDialogResultado("Vitória", "O jogador " + tv_jogador2.getText() + " venceu!");
            }
        }
    }


    public void gerarAlertDialogResultado(String titulo, String mensagem){
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle(titulo + "!");
        adb.setMessage(mensagem);
        adb.setIcon(R.drawable.copa);
        adb.setPositiveButton("Jogar Novamente", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                jogarNovamente();
            }
        });
        adb.setNegativeButton("Novos Jogadores", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                jogarNovosJogadores();
            }
        });
        adb.setNeutralButton("Fechar", null);
       /* adb.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sairJogo();
            }
        });*/
        adb.create();
        adb.show();
    }

    // TODO: sugestão de mudar o nome do método
    // para "mostrarFaceCartas"
    public void virarCartas() {
        for (int i = 0; i < 24; i ++) {
            int finalI = i;
            figurinhas[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    figurinhas[finalI].setImageResource(botoes[finalI].getFace2());
                    figurinhas[finalI].setClickable(false);
                    figurinhas[finalI].setEnabled(false);
                    indiceTempCartasMostradas.add(finalI);

                   if(indiceTempCartasMostradas.size()==2){
                       desativarBotoes();
                       verificarIgualdade(indiceTempCartasMostradas.get(0), indiceTempCartasMostradas.get(1), jogador%2);
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
        tv_flJ1 = findViewById(R.id.tv_flJ1);
        tv_flJ2 = findViewById(R.id.tv_flJ2);

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

    public void desativarBotoes(){
        for (int i = 0; i < 24; i++) {
            figurinhas[i].setClickable(false);
            figurinhas[i].setEnabled(false);
        }
    }

    public void inicializaListaBotoesNaoAchados(){
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

   public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void jogarNovamente(){
        this.reinicializar();
    }
    public void jogarNovosJogadores(){
        this.reinicializar();
        final EditText editText2 = new EditText(this);
        editText2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        editText2.setFilters(new InputFilter[] {new InputFilter.LengthFilter(9)});
        AlertDialog.Builder secondPlayer = new AlertDialog.Builder(this);
        secondPlayer.setCancelable(false);
        secondPlayer.setMessage("Nome do jogador 2 (amarelo):");
        secondPlayer.setView(editText2);
        secondPlayer.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String jogador2 = editText2.getText().toString();
                if(!jogador2.isEmpty()){
                    tv_jogador2.setText(jogador2 + "");
                    tv_flJ2.setText(String.valueOf(jogador2.charAt(0)));
                }
            }
        });
        secondPlayer.create();
        secondPlayer.show();

        final EditText editText1 = new EditText(this);
        editText1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        editText1.setFilters(new InputFilter[] {new InputFilter.LengthFilter(8)});
        AlertDialog.Builder firstPlayer = new AlertDialog.Builder(this);
        firstPlayer.setMessage("Nome do jogador 1 (verde):");
        firstPlayer.setCancelable(false);
        // firstPlayer.setTitle("J1");
        firstPlayer.setView(editText1);
        firstPlayer.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String jogador1 = editText1.getText().toString();
                if(!jogador1.isEmpty()){
                    tv_jogador1.setText(jogador1 + "");
                    tv_flJ1.setText(String.valueOf(jogador1.charAt(0)));
                }
            }
        });
        firstPlayer.create();
        firstPlayer.show();
    }

    public void sairJogo(){
        System.exit(0);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.item_sobre:
                Intent intent = new Intent(this, SobreActivity.class);
                startActivity(intent);
                break;
            case R.id.item_novo_jogo:
                gerarAlertDialogResultado("Novo jogo", null);
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
        botoesNaoAchados.clear();
        indiceTempCartasMostradas.clear();
        inicializaListaBotoesNaoAchados();
        ativaBotoesNaoAchados();
        controle.popularlista();
        definirCartaBotoes();
        descolorir();
        tv_pts1.setText("0");
        tv_pts2.setText("0");
        jogador =1;
    }

    private void descolorir() {
        for (int i = 0; i < 24; i++) {
            figurinhas[i].setColorFilter(null);
        }
    }
}
