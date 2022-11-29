package br.unitins.cg;

import android.util.Log;

import br.unitins.cg.AndGraphics.AGScene;
import br.unitins.cg.AndGraphics.AGSprite;

public class Scene extends AGScene {
    AGSprite[] reiniciar = new AGSprite[1];
    AGSprite[] pecas = new AGSprite[7];
    AGSprite[] menu = new AGSprite[4];
    int posicao = 0;
    float x = (float) 0.18, y = (float) 0.18;

    @Override
    public void init() {
        setSceneBackgroundColor(0,1,1);

        criarPecas();
        criarInterface();

    }

    @Override
    public void stop() {

    }

    @Override
    public void restart() {

    }

    @Override
    public void loop() {
        for (int pos = 0 ; pos < pecas.length; pos++) {

            if (screenDown() && pecas[pos].contains(getLastTouchPosition())){
                Log.d("AppDemo", "ultima posicao X: " + getLastTouchPosition().getX() + " , Y: " + getLastTouchPosition().getY());
                posicao = mover(pos);
                break;
            }
        }
        if (screenClicked()) {
            Log.d("AppDemo", "ultima posicao X: " + getLastTouchPosition().getX() +
                    " , Y: " + getLastTouchPosition().getY());
            if (menu[0].contains(getLastTouchPosition())) {
                girarEsquerda(posicao);
            }
            if (menu[1].contains(getLastTouchPosition())) {
                girarDireita(posicao);
            }
            if (menu[2].contains(getLastTouchPosition())) {
                aumentar(posicao);

            }
            if (menu[3].contains(getLastTouchPosition())) {
                diminuir(posicao);
            }
            if (reiniciar[0].contains(getLastTouchPosition())) {
                Log.d("AppDemo", "entrou no resetar ");
                limparVetor(pecas);
                criarPecas();
            }
        }

        if(backButtonClicked()){
            exitGame();
        }
    }

    public void criarInterface(){
        int[] menuIcons = {R.mipmap.giraresquerda, R.mipmap.girardireita, R.mipmap.aumentar,
                           R.mipmap.diminuir};
        reiniciar[0] = createSprite(R.mipmap.reiniciar,1,1);
        reiniciar[0].vrScale.setXY((float) 0.15, (float) 0.15);
        reiniciar[0].vrPosition.setXY(  940,
                reiniciar[0].getHeight() - 20);
        for (int pos = 0 ; pos < menu.length; pos++){
            menu[pos] = createSprite(menuIcons[pos],1,1);
            menu[pos].vrScale.setXY((float) 0.15, (float) 0.15);
            menu[pos].vrPosition.setXY(  130 + pos * (menu[pos].getWidth() + 50),//(getScreenWidth()/5),
                    menu[pos].getHeight() - 20);
            Log.d("AppDemo", "loop() returned: " + ((menu[pos].getHeight()/2)+30));

        }
    }
    public void criarPecas(){
        int[] referencias = {R.mipmap.triangulorosa, R.mipmap.trianguloamarelo, R.mipmap.trianguloroxo,
                R.mipmap.trianguloverde, R.mipmap.triangulovermelho,R.mipmap.quadrado,  R.mipmap.paralelograma};
        for (int pos = 0 ; pos < pecas.length; pos++){
            pecas[pos] = createSprite(referencias[pos],1,1);
            pecas[pos].vrScale.setXY((float) x, (float) y);
            pecas[pos].vrPosition.setXY(190,
                    400 + pos * (pecas[pos].getHeight() + 50));

        }
    }
    public int mover(int pos){

        pecas[pos].vrPosition.setXY(getLastTouchPosition().fX, getLastTouchPosition().fY);
        Log.d("AppDemo", "Movendo peça: " + posicao);
        return posicao = pos;


    }

    public void girarEsquerda(int posicao){

        Log.d("AppDemo", "Peça que tá girando esquerda:  " + posicao);
        pecas[posicao].fAngle += 45;
    }
    public void girarDireita(int posicao){
        Log.d("AppDemo", "Peça que tá girando direita:  " + posicao);
        pecas[posicao].fAngle -= 45;

    }
    public void aumentar(int posicao){
        float vx = pecas[posicao].vrScale.getX();
        float vy = pecas[posicao].vrScale.getY();

        pecas[posicao].vrScale.setXY(vx += 0.05, vy += 0.05);

    }
    public void diminuir(int posicao){
        float vx = pecas[posicao].vrScale.getX();
        float vy = pecas[posicao].vrScale.getY();
        if (vx > 0.15 && vy > 0.15) {
            pecas[posicao].vrScale.setXY(vx -= 0.05, vy -= 0.05);
        }

    }
    public void limparVetor(AGSprite[] sprite){
        for (int pos =0; pos < sprite.length; pos++){
            removeSprite(sprite[pos]);
        }
    }
}