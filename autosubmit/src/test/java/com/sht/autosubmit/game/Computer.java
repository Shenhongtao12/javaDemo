package com.sht.autosubmit.game;

/**
 * @author Aaron
 * @date 2021/6/20 23:08
 */
public class Computer implements Player {

    private String name;
    private int score;

    @Override
    public int showFinger() {
        int num = ((int)(Math.random()*10)) %3 + 1;

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



    public Computer() {
        super();
    }


    public Computer(String name) {
        super();
        this.name = name;
    }

}
