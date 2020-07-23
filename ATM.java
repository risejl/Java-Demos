package ATMProject;

import java.util.Scanner;

public class ATM {

    private static String sysCard = "123456";
    private static String sysPassword = "123456";
    private static long initMoney = 10000;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        mainPanel();
    }

    public static void mainPanel() throws Exception {
        boolean flagC = true;
        boolean flagP = true;
        System.out.println("------------------银行欢迎您!-------------------");
        System.out.println("1.登录          2.退出");
        System.out.print("请选择菜单: ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("欢迎登录!");
            while (flagC) {
                System.out.print("请输入卡号: ");
                String cardNumber = scanner.next();
                if (cardNumber.equals(sysCard)) {
                    flagC = false;
                    while (flagP) {
                        System.out.print("请输入密码: ");
                        String password = scanner.next();
                        if (password.equals(sysPassword)) {
                            flagP = false;
                            System.out.println("登录成功");
                            operationPanel();
                        } else {
                            System.out.println("密码错误，请重新输入");
                        }
                    }
                } else {
                    System.out.println("卡号错误,请重新输入");
                }
            }
        }
    }

    public static void operationPanel() throws Exception {
        boolean next = true;
        while(next) {
            System.out.println("**************请选择要操作的信息对应数字****************");
            System.out.println("* 1.查询余额  2.ATM取款  3.ATM存款  4.修改密码  5.退出 *");
            System.out.println("****************************************************");
            System.out.print("请选择: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: check();
                        break;
                case 2: get();
                        break;
                case 3: store();
                        break;
                case 4: changePassword();
                        break;
                case 5: System.out.println("已退出系统");
                        next = false;
                        break;
                default: System.out.println("输入有误");
                        break;
            }
        }
    }

    public static void check() {
        System.out.println("当前的余额为: " + initMoney);
        System.out.println("返回上级菜单");
    }

    public static void store() {
        while(true) {
            System.out.print("请输入存入的金额: ");
            long money = scanner.nextLong();
            if(money >= 0) {
                initMoney += money;
                System.out.println("操作成功");
                break;
            } else {
                System.out.println("存款金额不能为负数");
            }
        }
    }

    public static void get() {
        while(true) {
            if(initMoney == 0) {
                System.out.println("余额为0");
            }
            System.out.print("请输入取钱的金额: ");
            long money = scanner.nextLong();
            if(money < 0) {
                System.out.println("取款金额不能为负数");
            } else if(money > 5000) {
                System.out.println("取款金额不能超过5000");
            } else if(money % 100 != 0) {
                System.out.println("取款金额必须是100的倍数");
            } else if(money > initMoney) {
                System.out.println("余额不足!");
            } else {
                initMoney -= money;
                System.out.println("操作成功");
                break;
            }
        }
    }

    public static void changePassword() throws Exception {
        String newPassword = null;
        boolean enter = false;
        while(true) {
            System.out.print("请输入原密码: ");
            String oldPassword = scanner.next();
            if(oldPassword.equals(sysPassword)) {
                System.out.print("请输入新密码: ");
                newPassword = scanner.next();
                if(newPassword.length() < 6) {
                    System.out.println("新密码长度不能小于6位");
                } else {
                    for(int i = 1;i < newPassword.length();i++) {
                        if(newPassword.charAt(i) != newPassword.charAt(i-1)) {
                            enter = true;
                            break;
                        }
                    }
                    if(enter) {
                        System.out.print("请再次输入密码: ");
                        String reenterPassword = scanner.next();
                        if(reenterPassword.equals(newPassword)) {
                            sysPassword = reenterPassword;
                            System.out.println("密码修改成功");
                            break;
                        } else {
                            System.out.println("输入错误,密码修改失败");
                        }
                    } else {
                        System.out.println("新密码不能完全相同");
                    }
                }
            } else {
                System.out.println("原密码输入错误");
            }
        }
    }
}
