package br.com.pontes.jogodamemria;

public class Carta {
    private int face1;
    private int face2;


    Carta(int face2){
        this.face1 = R.drawable.copa;
        this.face2 = face2;
    }

    public int getFace1() {
        return face1;
    }

    public int getFace2() {
        return face2;
    }
}
