package DoubleBallProject;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class doubleball {

    private static int[] redBalls = {1,2,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16,
            17,18,19,20,21,22,23,24,
            25,26,27,28,29,30,31,32,33
            };
    private static int[] blueBalls = {1,2,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16
            };
    private static int[] aiRedSel;
    private static int aiBlueSel;
    private static int[] playerRedSel;
    private static int playerBlueSel;
    private static int[] winningRed;
    private static int winningBlue;
    private static int[] winningBallsCount;

    public static void main(String[] args) {

        boolean play = true;
        Scanner scanner = new Scanner(System.in);

        while(play) {

            mainPanel();
            int selection = scanner.nextInt();
            switch (selection) {
                case 1: aiRedSel = autoRedBallsSel(redBalls,1);
                        aiBlueSel = autoBlueBallsSel(blueBalls,1);
                        winningRed = autoRedBallsSel(redBalls,0);
                        winningBlue = autoBlueBallsSel(blueBalls,0);
                        winningBallsCount = judgeWin(aiRedSel,aiBlueSel,winningRed,winningBlue);
                        showResult(winningBallsCount);
                        break;
                case 2: playerRedSel = selectRedBalls();
                        playerBlueSel = selectBlueBalls();
                        winningRed = autoRedBallsSel(redBalls,0);
                        winningBlue = autoBlueBallsSel(blueBalls,0);
                        winningBallsCount = judgeWin(playerRedSel,playerBlueSel,winningRed,winningBlue);
                        showResult(winningBallsCount);
                        break;
                default: System.out.println("您的输入有误！请重新输入: ");
                        break;
            }

            System.out.println("您还想再下一注吗？(1.继续)(2.结束)");
            int continueToPlay = scanner.nextInt();
            if(continueToPlay == 2) {
                play = false;
            }
            if(continueToPlay != 1 && continueToPlay != 2) {
                do {
                    System.out.println("您的输入有误，请重新输入：(1.继续)(2.结束) ");
                    int correctInput = scanner.nextInt();
                    if(correctInput == 1) {
                        break;
                    } else if(correctInput == 2) return;
                    else continue;
                } while(continueToPlay != 1 && continueToPlay != 2);
            }
        }
    }

    public static void mainPanel() {
        System.out.println("*******************************************************");
        System.out.println("欢迎来到双色球游戏!");
        System.out.println("请选择您想机器选号还是手动选号？（1.机器选号）（2.手动选号）");
    }

    public static int[] autoRedBallsSel(int[] red,int flag) {
        int[] randomBalls = new int[6];
        Random r = new Random();
        for(int i = 0;i < randomBalls.length;i++) {
            int randomNum = r.nextInt(33) + 1;
            int count = 0;
            for(int j = 0;j < randomBalls.length;j++) {
                int ball = randomBalls[j];
                if(ball == randomNum) {
                    count += 1;
                }
            }
            if(count > 0) {
                i -= 1;
            } else {
                randomBalls[i] = randomNum;
            }
        }
        Arrays.sort(randomBalls);
        if(flag == 1) {
            System.out.println("机选出的红色号码球为: " + Arrays.toString(randomBalls));
        } else {
            System.out.println("本期中奖的红色号码球为: " + Arrays.toString(randomBalls));
        }
        return randomBalls;
    }

    public static int autoBlueBallsSel(int[] blue,int flag) {
        Random random = new Random();
        int max = blue[0];
        int min = blue[0];
        for(int index : blue) {
            if(index > max) {
                max = index;
            }
            if(index < min) {
                min = index;
            }
        }
        int randomNum = random.nextInt(max - min + 1) + min;
        if(flag == 1) {
            System.out.println("机选出的蓝色号码球为: " + randomNum);
        } else {
            System.out.println("本期中奖的蓝色号码球为: " + randomNum);
        }
        return randomNum;
    }

    public static int[] selectRedBalls() {
        int[] redBallsSel = new int[6];
        System.out.println("请输入您要选择的红色号码球(1-33): ");
        Scanner scanner = new Scanner(System.in);
        int ballsNum = 0;
        while(true) {
            boolean enter = false;
            int ball = scanner.nextInt();
            if(ball > 33 || ball < 1) {
                System.out.println("不存在该球，请重新选择(1-33): ");
            } else {
                for(int i = 0;i < redBallsSel.length;i++) {
                    if(redBallsSel[i] == ball) {
                        System.out.println("已经选过了，请重新选择: ");
                        enter = true;
                        break;
                    }
                }
                if(!enter) {
                    redBallsSel[ballsNum] = ball;
                    ballsNum += 1;
                }
                if(ballsNum == 6) break;
            }
        }
        Arrays.sort(redBallsSel);
        System.out.println("您选择的红色号码球为: " + Arrays.toString(redBallsSel));
        return redBallsSel;
    }

    public static int selectBlueBalls() {
        System.out.println("请输入您要选择的蓝色号码球(1-16): ");
        Scanner scanner = new Scanner(System.in);
        boolean enter = false;
        int blue = scanner.nextInt();
        int correctInput = 0;
        if(blue > 16 || blue < 1) {
            enter = true;
            do {
                System.out.println("不存在这个球，请重新输入(1-16): ");
                correctInput = scanner.nextInt();
            } while(correctInput > 16 || correctInput < 1);
        }
        if(enter) {
            System.out.println("您选择的蓝色号码球为: " + correctInput);
            return correctInput;
        } else {
            System.out.println("您选择的蓝色号码球为: " + blue);
            return blue;
        }
    }

    public static int[] judgeWin(int[] selRed,int selBlue,int[] winRed,int winBlue) {
        int redWin = 0,blueWin = 0;
        int iPointer = 0,jPointer = 0;
        int[] ballsArr = new int[2];
        while(iPointer < selRed.length && jPointer < winRed.length) {
            if(selRed[iPointer] == winRed[jPointer]) {
                redWin += 1;
                iPointer += 1;
                jPointer += 1;
            } else if(selRed[iPointer] < winRed[jPointer]) iPointer += 1;
            else jPointer += 1;
        }
        if(selBlue == winBlue) blueWin += 1;
        ballsArr[0] = redWin;
        ballsArr[1] = blueWin;
        return ballsArr;
    }

    public static void showResult(int[] analyzeRe) {
        switch(analyzeRe[0]) {
            case 0:
            case 1:
            case 2: if(analyzeRe[1] == 1) {
                    System.out.println("您中了六等奖，奖金为5元");
                     } else {
                    System.out.println("很遗憾，您没有中奖");
                     }
                    break;
            case 3: if(analyzeRe[1] == 1) {
                    System.out.println("您中了五等奖，奖金为10元");
                    } else {
                    System.out.println("很遗憾，您没有中奖");
                    }
                    break;
            case 4: if(analyzeRe[1] == 0) {
                    System.out.println("您中了五等奖。奖金为10元");
                    } else if(analyzeRe[1] == 1) {
                    System.out.println("您中了四等奖，奖金为200元");
                    } else {
                    System.out.println("很遗憾，您没有中奖");
                    }
                    break;
            case 5: if(analyzeRe[1] == 0) {
                    System.out.println("您中了四等奖。奖金为200元");
                    } else if(analyzeRe[1] == 1) {
                    System.out.println("恭喜您中了三等奖，奖金为2000元");
                    } else {
                    System.out.println("很遗憾，您没有中奖");
                    }
                    break;
            case 6: if(analyzeRe[1] == 0) {
                    System.out.println("恭喜您中了二等奖！奖金不定");
                    } else if(analyzeRe[1] == 1) {
                    System.out.println("恭喜您中了一等奖！奖金不定");
                    } else {
                    System.out.println("很遗憾，您没有中奖");
                    }
                    break;
            default: System.out.println("您没有中奖");
                    break;
        }
    }
}
