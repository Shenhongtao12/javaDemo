package com.sht.autosubmit.game;

import java.util.Scanner;

/**
 * @author Aaron
 * @date 2021/6/20 23:18
 */
public interface Player {

    public String name = "匿名";
    public int score = 0;

    //出拳
    public int showFinger();
}
