package com.pgj.s2bplantsimulator.model;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.pgj.s2bplantsimulator.inventory.Item;
import com.pgj.s2bplantsimulator.ultis.StageUltis;

public class MovingImage extends Image {
    private Item item;
    public MovingImage(Image image, Item item){
        super(image.getDrawable());
        this.item = item;
        createAction();
    }

    public Item getItem() {
        return item;
    }

    public void createAction(){
            MovingImage image = this;
            image.addListener(new DragListener(){
                private Container baseImageContainer;
                private Stage stage;

                @Override
                public void dragStart(InputEvent event, float x, float y, int pointer) {
                    if(baseImageContainer == null){
                        baseImageContainer = (Container) image.getParent();
                        stage = image.getStage();
                        stage.addActor(image);
                        image.toFront();
                        image.setPosition(event.getStageX(), event.getStageY());
                    }
                }
                public void drag(InputEvent event, float x, float y, int pointer) {
                    image.setPosition(event.getStageX(), event.getStageY());

                }

                @Override
                public void dragStop(InputEvent event, float x, float y, int pointer) {
                    updateOnScreenPos();
                        if(image.getParent() instanceof  Container == false){
                            baseImageContainer.setActor(image);
                        }
                        baseImageContainer = null;
                }
            });
    }
    public void updateOnScreenPos(){
        Stage stage = this.getStage();
        for(Actor actor : stage.getActors() ){
            if(actor instanceof Table && actor.isVisible()){
                Table slots = (Table) actor;
                for(Cell  cell : slots.getCells()){
                    Container imageContainer = (Container) cell.getActor();
                    if(imageContainer.getActor() == null){
                        Vector2 pos = StageUltis.getInstance().getStagePos(imageContainer);
                        if((pos.x < this.getX())
                                && (this.getX() <= pos.x + 50)
                                && (pos.y <= this.getY())
                                && (this.getY() <= pos.y + 50)){
                            imageContainer.setActor(this);
                        }
                    }
                }
            }

        }
    }
}
