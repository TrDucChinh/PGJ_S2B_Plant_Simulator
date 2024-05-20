package com.pgj.s2bplantsimulator.inventory;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.pgj.s2bplantsimulator.screens.MainGame;

public class Tool extends Item {
    public Tool(String name, Image image, MainGame mainGame){
        super.setName(name);
        super.setImage(image);
        super.setMainGame(mainGame);
        image.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainGame.player.equipment.setCurrentItem(Tool.this);
                System.out.println("Current Item: " + mainGame.player.equipment.getCurrentItem().getName());
            }
        });
    }

}
