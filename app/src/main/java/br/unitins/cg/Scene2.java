package br.unitins.cg;

import br.unitins.cg.AndGraphics.AGScene;
import br.unitins.cg.AndGraphics.AGSprite;
import br.unitins.cg.AndGraphics.AGTimer;

public class Scene2 extends AGScene {

    AGTimer tempo = null;
    AGSprite[] botoes = new AGSprite[3];
//    int referencias = {R.mipmap.botaoverde,R.mipmap.botaoamarelo, R.mipmap.botaorosa};
    @Override
    public void init() {
        tempo = new AGTimer(4000);
        setSceneBackgroundColor(1,0,0);
    }

    @Override
    public void stop() {

    }

    @Override
    public void restart() {

    }

    @Override
    public void loop() {
        if(screenClicked()){
            setCurrentScene(0);
        }
        tempo.update();
        if (tempo.isTimeEnded()){
            setCurrentScene(0);
        }
    }
}
