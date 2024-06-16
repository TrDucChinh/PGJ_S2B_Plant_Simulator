package com.pgj.s2bplantsimulator.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.pgj.s2bplantsimulator.S2BPlantSimulator;

public class CreditScreen extends MenuScreenComponent{
    public CreditScreen(MenuGame menuGame) {
        super(menuGame);
    }

    @Override
    public void show() {
        super.show();
        Label label = new Label("This game is developed by Team 1 - PROPTIT\n\n" +
                "Tran Duc Chinh\n\n" +
                "Le Dinh Hiep\n\n" , getSkin(), "helpText");
        label.setWrap(true);
//        label.setDebug(true);
        label.setPosition(0, 0);
        label.setAlignment(Align.top);


        Image logo = new Image(getSkin(), "logo");
        logo.setScaling(Scaling.fit);

        Table table = new Table();

//        table.setDebug(true);
        table.add(logo).growX().padTop(20);
        table.row();
        table.add(label).growX().padTop(20);



        table.setBackground(getSkin().getDrawable("round_button"));
        table.align(Align.topLeft);


//        Image logo = new Image(getSkin(), "logo");
//        table.add(logo).size(150, 150).padBottom(20).row();

        getPanel().add(table).size(500, 300);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void setPanel(Table panel) {
        super.setPanel(panel);
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
