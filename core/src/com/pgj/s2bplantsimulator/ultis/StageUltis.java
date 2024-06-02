package com.pgj.s2bplantsimulator.ultis;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class StageUltis {
    private StageUltis(){};
    private static StageUltis stageUltis;
    public static StageUltis getInstance(){
        if(stageUltis == null){
            stageUltis = new StageUltis();
        }
        return stageUltis;
    }
    public static Vector2 getStagePos(Actor actor){
        float x = actor.getX(), y = actor.getY();
        while(actor.getParent() != null){
            x += actor.getParent().getX();
            y += actor.getParent().getY();
            actor = actor.getParent();
        }
        return new Vector2(x, y);
    }
}
