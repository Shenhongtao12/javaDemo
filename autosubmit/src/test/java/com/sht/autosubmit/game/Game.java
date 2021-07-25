package com.sht.autosubmit.game;

import java.util.Scanner;

/**
 * @author Aaron
 * @date 2021/6/20 23:10
 */
public class Game {

    private Person person;
    private Computer computer;
    private int count;

    public Game() {
        person = new Person();
        computer = new Computer();
        this.count = 0;
    }

    public void startGame() {
        System.out.println("--------欢 迎 进 入 游 戏 世 界-----------------\n");
        System.out.println("*********************");
        System.out.println("******猜拳，开始*****");
        System.out.println("*********************");
        System.out.println("出拳规则：1.剪刀 2.石头 3.布");

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("本局对方的角色:1.刘备 2.孙权 3.曹操");
            System.out.println("请选择对手（输入相应数字：）");
            int roleID = scanner.nextInt();
            switch(roleID) {
                case 1:{
                    computer.setName("刘备");
                    break;
                }
                case 2:{
                    computer.setName("孙权");
                    break;
                }
                case 3:{
                    computer.setName("曹操");
                    break;
                }
                default:{
                    System.out.println("角色选择错误...");
                    continue;
                }
            }

            if (this.person.getName() == null) {
                System.out.println("请输入您的名字:");
                String name = scanner.next();
                this.person.setName(name);
            }
            System.out.println(this.person.getName()+" VS "+this.computer.getName()+"对战");

            System.out.println("--------游戏开始--------");
            while(true) {

                int p = person.showFinger();
                int c = computer.showFinger();

                // 判断 ->1.剪刀 2.石头 3.布"
                if(p==c) {
                    System.out.println("和局!");
                }
                else if((p==1&&c==3) || (p==2&&c==1)||(p==3&&c==2)) {
                    System.out.println("恭喜！"+this.person.getName()+" 赢了...");
                    break;
                }
                else {
                    System.out.println("oops！"+this.computer.getName()+" 赢了...");
                }

                this.count++;

                System.out.println("是否开始下一轮?(y/n)");
                String start = scanner.next();
                if ("n".equalsIgnoreCase(start)) {
                    break;
                }
            }

            System.out.println("选择操作项:1.选择其他对手 2.结束游戏");
            int goOn = scanner.nextInt();
            // 如果用户不玩了
            if (goOn == 2) {
                System.out.printf("游戏结束，共计游戏%d局", count);
                return;
            }
        }while(true);

    }
}
