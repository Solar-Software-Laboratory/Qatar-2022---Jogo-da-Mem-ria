package br.com.pontes.jogodamemria;

import java.util.ArrayList;
import java.util.List;

public class Controle {

    List<Carta> listaCartasTemporarias = new ArrayList<Carta>();
    private Carta[] vetorCartas = new Carta[24];

    public void popularlista(){
        this.listaCartasTemporarias.add(new Carta(R.drawable.alisson));
        this.listaCartasTemporarias.add(new Carta(R.drawable.arrascaeta));
        this.listaCartasTemporarias.add(new Carta(R.drawable.cristiano_ronaldo));
        this.listaCartasTemporarias.add(new Carta(R.drawable.de_bruyne));
        this.listaCartasTemporarias.add(new Carta(R.drawable.lewandowski));
        this.listaCartasTemporarias.add(new Carta(R.drawable.mbappe));
        this.listaCartasTemporarias.add(new Carta(R.drawable.messi));
        this.listaCartasTemporarias.add(new Carta(R.drawable.modric));
        this.listaCartasTemporarias.add(new Carta(R.drawable.neymar));
        this.listaCartasTemporarias.add(new Carta(R.drawable.sadio_mane));
        this.listaCartasTemporarias.add(new Carta(R.drawable.son));
        this.listaCartasTemporarias.add(new Carta(R.drawable.vinicius_jr));
        this.listaCartasTemporarias.add(new Carta(R.drawable.alisson));
        this.listaCartasTemporarias.add(new Carta(R.drawable.arrascaeta));
        this.listaCartasTemporarias.add(new Carta(R.drawable.cristiano_ronaldo));
        this.listaCartasTemporarias.add(new Carta(R.drawable.de_bruyne));
        this.listaCartasTemporarias.add(new Carta(R.drawable.lewandowski));
        this.listaCartasTemporarias.add(new Carta(R.drawable.mbappe));
        this.listaCartasTemporarias.add(new Carta(R.drawable.messi));
        this.listaCartasTemporarias.add(new Carta(R.drawable.modric));
        this.listaCartasTemporarias.add(new Carta(R.drawable.neymar));
        this.listaCartasTemporarias.add(new Carta(R.drawable.sadio_mane));
        this.listaCartasTemporarias.add(new Carta(R.drawable.son));
        this.listaCartasTemporarias.add(new Carta(R.drawable.vinicius_jr));
    }

    public void setVetorCartas(Carta carta, int i) {
        this.vetorCartas[i] = carta;
    }

    public Carta getVetorCartas(int i) {
        return vetorCartas[i];
    }

    public Carta sortear(){
        int indice = (int) (Math.random()*this.listaCartasTemporarias.size());
        Carta c = listaCartasTemporarias.get(indice);
        listaCartasTemporarias.remove(indice);
        return c;
    }
}
