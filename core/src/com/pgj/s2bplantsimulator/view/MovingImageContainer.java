package com.pgj.s2bplantsimulator.view;



import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.Align;
import com.pgj.s2bplantsimulator.model.MovingImage;
import com.pgj.s2bplantsimulator.ultis.ResourceLoader;

public class MovingImageContainer extends Container {
   private Stack stack;
   Label label;
   public MovingImageContainer(){
       stack = new Stack();
       setActor(stack);
   }
   public void setActor(MovingImage movingImage){
       stack.clearChildren();
       stack.add(movingImage);
       label = new Label(String.valueOf(movingImage.getQuantityLabel()), ResourceLoader.getInstance().getSkin());
//       label.setDebug(true);
       label.setAlignment(Align.bottomRight);
       label.setTouchable(Touchable.disabled);
       label.toFront();
       stack.add(label);
   }
   public Actor getActor(){
       if(stack.getChildren().size <= 1) return null;
         return stack.getChildren().get(0);
   }
   public void update(float dt){
       int quantity = ((MovingImage)stack.getChildren().get(0)).getQuantityLabel();
       if(quantity == 0){
           stack.clearChildren();
           return;
       }
       label.setText(quantity);

   }

    public Stack getStack() {
        return stack;
    }
}
