package com.spaceraider.managers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

/**
 * Created by Kevin on 9/11/2016.
 */
public class GameInputProcessor extends InputAdapter {

    public boolean keyDown(int k){
        if(k == Input.Keys.UP){
            GameKeys.setKey(GameKeys.UP, true);
        }
        if(k == Input.Keys.LEFT){
            GameKeys.setKey(GameKeys.LEFT, true);
        }
        if(k == Input.Keys.DOWN){
            GameKeys.setKey(GameKeys.DOWN, true);
        }
        if(k == Input.Keys.RIGHT){
            GameKeys.setKey(GameKeys.RIGHT, true);
        }
        if(k == Input.Keys.ENTER){
            GameKeys.setKey(GameKeys.ENTER, true);
        }
        if(k == Input.Keys.ESCAPE){
            GameKeys.setKey(GameKeys.ESCAPE, true);
        }
        if(k == Input.Keys.SPACE){
            GameKeys.setKey(GameKeys.SPACE, true);
        }
        if(k == Input.Keys.SHIFT_LEFT || k == Input.Keys.SHIFT_RIGHT){
            GameKeys.setKey(GameKeys.SHIFT, true);
        }
        if(k == Input.Keys.Z){
            GameKeys.setKey(GameKeys.Z ,true);
        }
        if(k == Input.Keys.Q){
            GameKeys.setKey(GameKeys.Q ,true);
        }
        if(k == Input.Keys.S){
            GameKeys.setKey(GameKeys.S ,true);
        }
        if(k == Input.Keys.D){
            GameKeys.setKey(GameKeys.D, true);
        }
        if(k == Input.Keys.F1){
            GameKeys.setKey(GameKeys.F1,true);
        }
        if(k == Input.Keys.F2){
            GameKeys.setKey(GameKeys.F2,true);
        }
        if(k == Input.Keys.F3){
            GameKeys.setKey(GameKeys.F3,true);
        }
        if(k == Input.Keys.F4){
            GameKeys.setKey(GameKeys.F4,true);
        }
        if(k == Input.Keys.F5){
            GameKeys.setKey(GameKeys.F5,true);
        }
        if(k == Input.Keys.F6){
            GameKeys.setKey(GameKeys.F6,true);
        }
        if(k == Input.Keys.F7){
            GameKeys.setKey(GameKeys.F7,true);
        }
        if(k == Input.Keys.F8){
            GameKeys.setKey(GameKeys.F8,true);
        }
        if(k ==Input.Keys.NUMPAD_1){
            GameKeys.setKey(GameKeys.NUMPAD_1,true);
        }
        if(k ==Input.Keys.NUMPAD_2){
            GameKeys.setKey(GameKeys.NUMPAD_2,true);
        }
        if(k ==Input.Keys.NUMPAD_3){
            GameKeys.setKey(GameKeys.NUMPAD_3,true);
        }
        if(k ==Input.Keys.NUMPAD_4){
            GameKeys.setKey(GameKeys.NUMPAD_4,true);
        }
        if(k ==Input.Keys.NUMPAD_5){
            GameKeys.setKey(GameKeys.NUMPAD_5,true);
        }
        if(k ==Input.Keys.NUMPAD_6){
            GameKeys.setKey(GameKeys.NUMPAD_6,true);
        }
        if(k ==Input.Keys.NUMPAD_7){
            GameKeys.setKey(GameKeys.NUMPAD_7,true);
        }
        if(k ==Input.Keys.NUMPAD_8){
            GameKeys.setKey(GameKeys.NUMPAD_8,true);
        }


        return true;
    }


    public boolean keyUp(int k){
        if(k == Input.Keys.UP){
            GameKeys.setKey(GameKeys.UP, false);
        }
        if(k == Input.Keys.LEFT){
            GameKeys.setKey(GameKeys.LEFT, false);
        }
        if(k == Input.Keys.DOWN){
            GameKeys.setKey(GameKeys.DOWN, false);
        }
        if(k == Input.Keys.RIGHT){
            GameKeys.setKey(GameKeys.RIGHT, false);
        }
        if(k == Input.Keys.ENTER){
            GameKeys.setKey(GameKeys.ENTER, false);
        }
        if(k == Input.Keys.ESCAPE){
            GameKeys.setKey(GameKeys.ESCAPE, false);
        }
        if(k == Input.Keys.SPACE){
            GameKeys.setKey(GameKeys.SPACE, false);
        }
        if(k == Input.Keys.SHIFT_LEFT || k == Input.Keys.SHIFT_RIGHT){
            GameKeys.setKey(GameKeys.SHIFT, false);
        }
        if(k == Input.Keys.Z){
            GameKeys.setKey(GameKeys.Z ,false);
        }
        if(k == Input.Keys.Q){
            GameKeys.setKey(GameKeys.Q ,false);
        }
        if(k == Input.Keys.S){
            GameKeys.setKey(GameKeys.S ,false);
        }
        if(k == Input.Keys.D){
            GameKeys.setKey(GameKeys.D, false);
        }
        if(k == Input.Keys.F1){
            GameKeys.setKey(GameKeys.F1,false);
        }
        if(k == Input.Keys.F2){
            GameKeys.setKey(GameKeys.F2,false);
        }
        if(k == Input.Keys.F3){
            GameKeys.setKey(GameKeys.F3,false);
        }
        if(k == Input.Keys.F4){
            GameKeys.setKey(GameKeys.F4,false);
        }
        if(k == Input.Keys.F5){
            GameKeys.setKey(GameKeys.F5,false);
        }
        if(k == Input.Keys.F6){
            GameKeys.setKey(GameKeys.F6,false);
        }
        if(k == Input.Keys.F7){
            GameKeys.setKey(GameKeys.F7,false);
        }
        if(k == Input.Keys.F8){
            GameKeys.setKey(GameKeys.F8,false);
        }
        if(k ==Input.Keys.NUMPAD_1){
            GameKeys.setKey(GameKeys.NUMPAD_1,false);
        }
        if(k ==Input.Keys.NUMPAD_2){
            GameKeys.setKey(GameKeys.NUMPAD_2,false);
        }
        if(k ==Input.Keys.NUMPAD_3){
            GameKeys.setKey(GameKeys.NUMPAD_3,false);
        }
        if(k ==Input.Keys.NUMPAD_4){
            GameKeys.setKey(GameKeys.NUMPAD_4,false);
        }
        if(k ==Input.Keys.NUMPAD_5){
            GameKeys.setKey(GameKeys.NUMPAD_5,false);
        }
        if(k ==Input.Keys.NUMPAD_6){
            GameKeys.setKey(GameKeys.NUMPAD_6,false);
        }
        if(k ==Input.Keys.NUMPAD_7){
            GameKeys.setKey(GameKeys.NUMPAD_7,false);
        }
        if(k ==Input.Keys.NUMPAD_8){
            GameKeys.setKey(GameKeys.NUMPAD_8,false);
        }


        return true;
    }


    

}
