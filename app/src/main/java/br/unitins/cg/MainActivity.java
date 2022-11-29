package br.unitins.cg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.unitins.cg.AndGraphics.AGActivityGame;

public class MainActivity extends AGActivityGame {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.init(this, false);
        addScene(new Scene());

    }
}