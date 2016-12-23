package com.spaceraider.managers;

/**
 * Created by Kevin on 9/11/2016.
 */
public class GameKeys {

    private static boolean[] keys;
    private static boolean[] prevkeys;

    private static final int NUM_KEYS = 29;
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    public static final int ENTER = 4;
    public static final int ESCAPE = 5;
    public static final int SPACE = 6;
    public static final int SHIFT = 7;
    public static final int Z = 8;
    public static final int Q = 9;
    public static final int S = 10;
    public static final int D = 11;
    public static final int F1 = 12;
    public static final int F2 = 13;
    public static final int F3 = 14;
    public static final int F4 = 15;
    public static final int F5 = 17;
    public static final int F6 = 18;
    public static final int F7 = 19;
    public static final int F8 = 20;
    public static final int NUMPAD_1 = 21;
    public static final int NUMPAD_2 = 22;
    public static final int NUMPAD_3 = 23;
    public static final int NUMPAD_4 = 24;
    public static final int NUMPAD_5 = 25;
    public static final int NUMPAD_6 = 26;
    public static final int NUMPAD_7 = 27;
    public static final int NUMPAD_8 = 28;




    static{
        keys = new boolean[NUM_KEYS];
        prevkeys = new boolean[NUM_KEYS];
    }

    public static void update(){
        for(int i = 0; i < NUM_KEYS; i++){
            prevkeys[i] = keys[i];
        }
    }

    public static void setKey(int k, boolean b){
        keys[k] = b;
    }

    public static boolean isDown(int k){

        return keys[k];
    }
    public static boolean isPressed(int k){

        return keys[k] && !prevkeys[k];
    }

}
