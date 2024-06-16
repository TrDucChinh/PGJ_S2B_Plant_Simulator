package com.pgj.s2bplantsimulator.common.constant;

import java.util.HashMap;
import java.util.Map;

public class GameConstant {
    public static final int WINDOW_WIDTH = 1024;
    public static final int WINDOW_HEIGHT = 720;
    public static final float PPM = 32.0f;
    public static final float CHARACTER_WIDTH = 16;
    public static final float CHARACTER_HEIGHT = 16;
    public static final int FRAME_COLS_CHARACTER = 4;
    public static final int FRAME_ROWS_CHARACTER = 4;
    public static final String PLAY_BUTTON = "button/play.png";
    public static final String ABOUT_BUTTON = "button/about.png";
    public static final String REPLAY_BUTTON = "button/replay.png";
    public static final String PLAY_PRESS_BUTTON = "button/playpress.png";
    public static final String ABOUT_PRESS_BUTTON = "button/aboutpress.png";
    public static final String REPLAY_PRESS_BUTTON = "button/replaypress.png";
    public static final int MIN_PRICE = 50, MAX_PRICE = 100;
    public static final Map<String, Float> GROW_SPEED = new HashMap<String, Float>() {{
        put("corn", 100.0f);
        put("tomato", 0.7f);
    }};
    public static final Map<String, Integer> MAX_AGE = new HashMap<String, Integer>() {{
        put("corn", 100);
        put("tomato", 70);
    }};
}
