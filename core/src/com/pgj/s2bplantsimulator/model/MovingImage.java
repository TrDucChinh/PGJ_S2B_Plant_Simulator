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
import com.pgj.s2bplantsimulator.ultis.StageUltis;

public class MovingImage extends Image {
    public MovingImage(Image image){
        super(image.getDrawable());
        createAction();
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
                        image.setBounds(event.getStageX(), event.getStageY(), image.getWidth(), image.getHeight());
                    }
                }
                public void drag(InputEvent event, float x, float y, int pointer) {
                    image.setBounds(event.getStageX(), event.getStageY(), image.getWidth(), image.getHeight());

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
        Table equipmentSlot = new Table();
        Stage stage = this.getStage();
        for(Actor actor : stage.getActors()){
            if(actor instanceof Table){
                equipmentSlot = (Table) actor;
            }

        }
        for(Actor actor : stage.getActors()){
            if(actor instanceof MovingImage){
                MovingImage image = (MovingImage) actor;
                for(Cell  cell : equipmentSlot.getCells()){
                    Container imageContainer = (Container) cell.getActor();
                    if(imageContainer.getActor() == null){
                        Vector2 pos = StageUltis.getInstance().getStagePos(imageContainer);
                        System.out.println(pos.x + " " + pos.y + " " + actor.getX() + " " + actor.getY());
                        if((pos.x < image.getX())
                                && (image.getX() <= pos.x + 50)
                                && (pos.y <= image.getY())
                                && (image.getY() <= pos.y + 50)){
                            imageContainer.setActor(image);
                        }
                    }
                }
            }
        }
    }
}
