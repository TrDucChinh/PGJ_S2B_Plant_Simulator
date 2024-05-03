package com.pgj.s2bplantsimulator.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {
    Tileset tileSet;
    private TextureRegion textureRegion;
    int val;
    int tileSize = 16;
    public Tile(){
        tileSize = 16;
    }
    public void loadTile(){

        int x = val / tileSet.getColNum();
        int y = val % tileSet.getColNum();
        textureRegion = new TextureRegion(tileSet.loadImage(),x ,y ,tileSize, tileSize);

    }

    public Tileset getTileSet() {
        return tileSet;
    }

    public void setTileSet(Tileset tileSet) {
        this.tileSet = tileSet;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public int getTileSize() {
        return tileSize;
    }

}

