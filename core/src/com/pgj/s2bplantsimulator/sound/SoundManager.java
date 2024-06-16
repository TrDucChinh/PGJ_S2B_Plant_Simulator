package com.pgj.s2bplantsimulator.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.pgj.s2bplantsimulator.model.Item;
import com.pgj.s2bplantsimulator.screens.MainGame;

import java.util.HashMap;
import java.util.Map;
public class SoundManager {
    public final String FOOTSTEP_SOUND = "footstep_sound.mp3";
    public final String BACKGROUND_MUSIC = "background_music.mp3";
    public final String WATERING_SOUND = "watering_sound.mp3";
    public final String DIGGING_SOUND = "digging_sound.wav";
    public final String HARVEST_SOUND = "harvest_sound.mp3";
    public final String PLANTING_SOUND = "planting_sound.mp3";
    private float musicScale = 1;
    private float soundScale = 1;
    public Long footstepSoundId;
    private final Map<String, Sound> soundCache = new HashMap<>();
    private Sound currentSound;

    private Sound getSound(String path){
        if(soundCache.containsKey(path) == false){
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("sound/" + path));
            soundCache.put(path, sound);
        }
        return soundCache.get(path);
    }
    MainGame mainGame;
    public SoundManager(MainGame mainGame) {
        this.mainGame = mainGame;
    }
    public void create(){
        getSound(BACKGROUND_MUSIC).loop(musicScale);
        getSound(FOOTSTEP_SOUND).loop(soundScale);
        getSound(FOOTSTEP_SOUND).pause();
    }
    public void update(){
        playerHandle();
    }
    public void playerHandle(){
        if(mainGame.player.isMoving()){
            getSound(FOOTSTEP_SOUND).resume();
        }else{
            getSound(FOOTSTEP_SOUND).pause();
        }
        if(mainGame.player.isAction()){
            Item currentItem = mainGame.player.getInventory().getCurrentItem();
            if (currentItem == null){
                return;
            }
            String itemName = currentItem.getName();
            if(currentItem.equals("Hoe")){
                getSound(DIGGING_SOUND).play(soundScale);
            }
            if(currentItem.equals("Watering Pot")){
                getSound(WATERING_SOUND).play(0.5f * soundScale);
            }
            if(currentItem.equals("Axe")){
                getSound(HARVEST_SOUND).play(soundScale);
            }
            if(currentItem.equals("Corn Seed") || currentItem.equals("Tomato Seed")){
                getSound(PLANTING_SOUND).play(soundScale);
            }
        }
    }
}
