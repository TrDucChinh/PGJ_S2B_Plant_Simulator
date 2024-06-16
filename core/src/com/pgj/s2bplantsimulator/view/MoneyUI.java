package com.pgj.s2bplantsimulator.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.pgj.s2bplantsimulator.screens.MainGame;

public class MoneyUI {
    private Skin skin = new Skin(Gdx.files.internal("Skin/ui_skin.json"));
    private MainGame mainGame;
    Table table;
    Stage stage;
    private Label moneyLabel;
    private int POS_X = 10;
    private int POS_Y = Gdx.graphics.getHeight() - 30;
    public MoneyUI(MainGame mainGame){
        table = new Table();
        stage = mainGame.getHud().getStage();
        stage.addActor(table);
        this.mainGame = mainGame;
    }
    public void show(){
        table.setPosition(POS_X, POS_Y);
        table.align(Align.left);
        Image image = new Image(skin.getDrawable("money"));
        image.setScaling(Scaling.fit);
        table.add(image).size(50, 50);
        String money = mainGame.getPlayer().getMoney() + "";
        moneyLabel = new Label(money, skin.get("moneyLabel", Label.LabelStyle.class));

        table.add(moneyLabel).padLeft(10);
    }
}
