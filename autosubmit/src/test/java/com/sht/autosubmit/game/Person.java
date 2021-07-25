package com.sht.autosubmit.game;

import java.util.Scanner;

/**
 * @author Aaron
 * @date 2021/6/20 23:09
 */
public class Person implements Player {

    private String name;
    private int score;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }


    public void setScore(int score) {
        this.score = score;
    }



    public Person() {
        super();
    }


    public Person(String name) {
        super();
        this.name = name;
    }

    @Override
    public int showFinger() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请出拳：1.剪刀 2.石头 3.布（输入相应数字：）");
        int num = scanner.nextInt();

        switch (num) {
            case 1:
            {
                System.out.println(name+" 出拳:剪刀");
                break;
            }
            case 2:
            {
                System.out.println(name+" 出拳:石头");
                break;
            }
            case 3:
            {
                System.out.println(name+" 出拳:布");
                break;
            }
            default:
                break;
        }

        return num;
    }
}
