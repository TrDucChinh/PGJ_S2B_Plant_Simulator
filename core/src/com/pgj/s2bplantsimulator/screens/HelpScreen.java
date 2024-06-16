package com.pgj.s2bplantsimulator.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.pgj.s2bplantsimulator.S2BPlantSimulator;

public class HelpScreen extends MenuScreenComponent{
    public HelpScreen(MenuGame menuGame) {
        super(menuGame);
    }

    @Override
    public void show() {
        super.show();
        Label label = new Label(null, getSkin(), "helpText");


        label.setText("Happy Farm is an engaging farm simulation game where players become real farmers, managing and developing their own farm. You will start with a small plot of land and over time, expand the farm, grow crops, raise livestock, and build a prosperous farm.\n");
        label.setText(label.getText() + "\nIn Happy Farm, you move your character using the W (up), A (left), S (down), and D (right) keys. Press F to open the shop when standing at a shop location. Press I to open the inventory and space to use a tool. Using these keys helps you manage and develop your farm effectively.");

        Table container = new Table();
        container.add(label).growX();
        container.setBackground(getSkin().getDrawable("round_button"));
        container.align(Align.topLeft);

//        container.setDebug(true);
        label.setSize(container.getWidth(), container.getHeight());
        label.setWrap(true);
        label.setDebug(true);
        label.setPosition(0, 0);
        label.setAlignment(Align.topLeft);

        getPanel().add(container).padBottom(20).size(500, 300).row();


    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
