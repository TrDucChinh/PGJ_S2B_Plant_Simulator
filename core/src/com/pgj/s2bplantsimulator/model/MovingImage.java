package com.pgj.s2bplantsimulator.model;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Scaling;
import com.pgj.s2bplantsimulator.ultis.StageUltis;
import com.pgj.s2bplantsimulator.view.MovingImageContainer;

public class MovingImage extends Image {
    private Item item;
    private int quantityLabel;

    public MovingImage(Image image, Item item){
        super(image.getDrawable());
        this.item = item;
        this.setScaling(Scaling.fit);
        createAction();
    }

    public Item getItem() {
        return item;
    }

    public void createAction(){
            MovingImage image = this;
            image.addListener(new DragListener(){
                private Vector2 initialPos;
                private Stage stage;
                public void touchDragged(InputEvent event, float x, float y, int pointer) {
                    image.setPosition(event.getStageX(), event.getStageY());
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    stage = image.getStage();
                    initialPos = StageUltis.getStagePos(image);
                    Stack stack = (Stack) image.getParent();
                    stack.clearChildren();
                    stage.addActor(image);
                    image.toFront();
                    image.setPosition(event.getStageX(), event.getStageY());
                    return true;
                }


                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    if(updateOnScreenPos(true) == false){
                        image.setPosition(initialPos.x, initialPos.y);
                        updateOnScreenPos(false);
                    }
                }
            });
    }
    public boolean updateOnScreenPos(boolean isNewLocation){
        Stage stage = this.getStage();
        for(Actor actor : stage.getActors() ){
            if(actor instanceof Table){
                if(isNewLocation == false || (isNewLocation == true && actor.isVisible() == true)){
                    Table slots = (Table) actor;
                    for(Cell  cell : slots.getCells()){

                        if(cell.getActor() instanceof MovingImageContainer){
                            MovingImageContainer imageContainer = (MovingImageContainer) cell.getActor();
                            if(imageContainer.getActor() == null){
                                Vector2 pos = StageUltis.getInstance().getStagePos(imageContainer);
                                if((pos.x < this.getX())
                                        && (this.getX() <= pos.x + 50)
                                        && (pos.y <= this.getY())
                                        && (this.getY() <= pos.y + 50)){
                                    imageContainer.setActor(this);
                                    this.setPosition(0, 0);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int getQuantityLabel() {
        return quantityLabel;
    }

    public void setQuantityLabel(int quantityLabel) {
        this.quantityLabel = quantityLabel;
    }
}
