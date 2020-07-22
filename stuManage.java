package StudentManagementSystem;

import java.util.Scanner;
import java.sql.*;

public class stuguanli {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/stu_management?autoReconnect=true&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";
    private static final String sysUsername = "admin";
    private static final String sysPassword = "admin";
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();

            mainPanel();

            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void mainPanel() {
        try {
            boolean flagU = true;
            boolean flagP = true;
            System.out.println("------------------欢迎 登录学生信息管理系统------------------");
            System.out.println("1.登录          2.退出");
            System.out.println("---------------------------------------------------------");
            System.out.print("请选择: ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("欢迎登录!");
                while (flagU) {
                    System.out.print("请输入用户名: ");
                    String username = scanner.next();
                    if (username.equals(sysUsername)) {
                        flagU = false;
                        while (flagP) {
                            System.out.print("请输入密码: ");
                            String password = scanner.next();
                            if (password.equals(sysPassword)) {
                                flagP = false;
                                System.out.println("登录成功");
                                System.out.println("欢迎您，admin");
                                operationPanel();
                            } else {
                                System.out.println("密码错误，请重新输入");
                            }
                        }
                    } else {
                        System.out.println("用户名错误,请重新输入");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void operationPanel() {
        System.out.println("**********************请选择要操作的信息对应数字**********************");
        System.out.println("* 1.查看学生信息  2.添加学生信息  3.删除学生信息  4.修改学生信息  5.退出 *");
        System.out.println("*****************************************************************");
        System.out.print("请选择: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                viewStudentInfo();
                break;
            case 2:
                addStudentInfo();
                break;
            case 3:
                deleteStudentInfo();
                break;
            case 4:
                editStudentInfo();
                break;
            case 5:
                System.out.println("已退出系统");
                break;
            default:
                System.out.println("输入有误");
                break;
        }
    }

    public static void viewStudentInfo() {
        try {
            boolean flag = true;
            while (flag) {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("+  1.查看所有学生信息     2.根据id查询学生信息     3.根据id查询学生姓名     4.返回上一层   +");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.print("请选择菜单: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        String sql = "select ID,StuName,Age,Gender,Grade,Tel,Email,Address from student";
                        resultSet = statement.executeQuery(sql);
                        System.out.println("------------------------------------------------------------------------------------");
                        System.out.println("|学号  |姓名   |年龄  |性别  |年级   |电话             |EMAIL            |地址            ");
                        System.out.println("------------------------------------------------------------------------------------");
                        while (resultSet.next()) {
                            int id = resultSet.getInt("ID");
                            String name = resultSet.getString("StuName");
                            int age = resultSet.getInt("Age");
                            String gender = resultSet.getString("Gender");
                            String grade = resultSet.getString("Grade");
                            long telephone = resultSet.getLong("Tel");
                            String email = resultSet.getString("Email");
                            String address = resultSet.getString("Address");
                            System.out.printf("|%-5d|%-5s|%-5d|%-5s|%-5s|%-16d|%-18s|%-14s\n", id, name, age, gender, grade, telephone, email, address);
                        }
                        System.out.println("数据查询完毕，系统将自动返回目录········");
                        break;

                    case 2:
                        boolean enter = false;
                        System.out.print("请输入要查询的学生id: ");
                        int seID = scanner.nextInt();
                        sql = "select ID,StuName,Age,Gender,Grade,Tel,Email,Address from student";
                        resultSet = statement.executeQuery(sql);
                        while (resultSet.next()) {
                            int id = resultSet.getInt("ID");
                            String name = resultSet.getString("StuName");
                            int age = resultSet.getInt("Age");
                            String gender = resultSet.getString("Gender");
                            String grade = resultSet.getString("Grade");
                            long telephone = resultSet.getLong("Tel");
                            String email = resultSet.getString("Email");
                            String address = resultSet.getString("Address");
                            if (id == seID) {
                                enter = true;
                                System.out.println("------------------------------------------------------------------------------------");
                                System.out.println("|学号  |姓名   |年龄  |性别  |年级   |电话             |EMAIL            |地址            ");
                                System.out.println("------------------------------------------------------------------------------------");
                                System.out.printf("|%-5d|%-5s|%-5d|%-5s|%-5s|%-16d|%-18s|%-14s\n", id, name, age, gender, grade, telephone, email, address);
                            }
                        }
                        if (enter) {
                            System.out.println("数据查询完毕，系统将自动返回目录········");
                        } else {
                            System.out.println("该ID不存在");
                        }
                        break;

                    case 3:
                        boolean enterN = false;
                        System.out.print("请输入你要查询的学生ID: ");
                        int seNameID = scanner.nextInt();
                        sql = "select ID,StuName,Age,Gender,Grade,Tel,Email,Address from student";
                        resultSet = statement.executeQuery(sql);
                        while (resultSet.next()) {
                            int id = resultSet.getInt("ID");
                            String name = resultSet.getString("StuName");
                            if (id == seNameID) {
                                enterN = true;
                                System.out.println("------------------------------------------------------------------------------------");
                                System.out.println("|学号  |姓名");
                                System.out.println("------------------------------------------------------------------------------------");
                                System.out.printf("|%-5d|%-5s\n", id, name);
                            }
                        }
                        if (enterN) {
                            System.out.println("数据查询完毕，系统将自动返回目录········");
                        } else {
                            System.out.println("该ID不存在");
                        }
                        break;
                    case 4:
                        System.out.println("返回上一级中·········");
                        flag = false;
                        operationPanel();
                        break;
                    default:
                        System.out.println("输入有误");
                        break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addStudentInfo() {
        try {
            boolean flag = true;
            while (flag) {
                boolean enter = false;
                System.out.println("***************************添加学生信息**********************************************");
                System.out.print("请输入学生id: ");
                int id = scanner.nextInt();
                String searchSql = "select ID from student";
                resultSet = statement.executeQuery(searchSql);
                while (resultSet.next()) {
                    int stuID = resultSet.getInt("ID");
                    if (stuID == id) {
                        enter = true;
                        System.out.println("此id" + stuID + "存在，请重新输入");
                        break;
                    }
                    if (!enter) {
                        System.out.print("请输入学生姓名: ");
                        String name = scanner.next();
                        System.out.print("请输入学生性别: ");
                        String gender = scanner.next();
                        System.out.print("请输入学生年龄(只能1-120之内的数字): ");
                        int age = scanner.nextInt();
                        System.out.print("请输入学生所属年级(只能初级、中级、高级): ");
                        String grade = scanner.next();
                        System.out.print("请输入学生地址: ");
                        String address = scanner.next();
                        System.out.print("请输入学生联系方式(11位手机号码): ");
                        long tel = scanner.nextLong();
                        System.out.print("请输入学生电子邮箱(包含@和.com): ");
                        String email = scanner.next();
                        String sql = "insert into student(ID,StuName,Age,Gender,Grade,Tel,Email,Address) values("+id+",'"+name+"',"+age+",'"+gender+"','"+grade+"',"+tel+",'"+email+"','"+address+"')";
                        statement.execute(sql);
                        System.out.println("数据保存成功,系统将自动返回上层目录~");
                        flag = false;
                        operationPanel();
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteStudentInfo() {
        try {
            boolean flag = true;
            while (flag) {
                System.out.println("***************************删除学生信息**********************************************");
                System.out.println("+  1.删除所有学生信息     2.根据id删除学生信息     3.根据姓名删除学生信息     4.返回上一层   +");
                System.out.println("***********************************************************************************");
                System.out.print("请选择菜单: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        String sql = "delete from student";
                        statement.execute(sql);
                        System.out.println("全部信息已删除!");
                        break;
                    case 2:
                        boolean exist = true;
                        boolean enter = false;
                        while (exist) {
                            System.out.print("请输入要删除学生的id: ");
                            int id = scanner.nextInt();
                            sql = "select ID from student";
                            resultSet = statement.executeQuery(sql);
                            while (resultSet.next()) {
                                int stuid = resultSet.getInt("ID");
                                if (stuid == id) {
                                    enter = true;
                                    sql = "delete from student where ID = " + stuid + "";
                                    statement = connection.createStatement();
                                    statement.execute(sql);
                                    System.out.println("已删除该学生信息");
                                    exist = false;
                                }
                            }
                            if (!enter) {
                                System.out.println("不存在该ID，请重新输入");
                            }
                        }
                        break;
                    case 3:
                        boolean exist2 = true;
                        boolean enter2 = false;
                        while (exist2) {
                            System.out.print("请输入要删除学生的姓名: ");
                            String name = scanner.next();
                            sql = "select StuName from student";
                            resultSet = statement.executeQuery(sql);
                            while (resultSet.next()) {
                                String stuname = resultSet.getString("StuName");
                                if (stuname.equals(name)) {
                                    enter2 = true;
                                    sql = "delete from student where StuName = '" + stuname + "'";
                                    statement = connection.createStatement();
                                    statement.execute(sql);
                                    System.out.println("已删除该学生信息");
                                    exist2 = false;
                                }
                            }
                            if (!enter2) {
                                System.out.println("不存在该学生，请重新输入");
                            }
                        }
                        break;
                    case 4:
                        System.out.println("返回上一级中·········");
                        flag = false;
                        operationPanel();
                        break;
                    default:
                        System.out.println("输入有误");
                        break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void editStudentInfo() {                
        try {
            boolean flag = true;
            while (flag) {
                System.out.println("-----------------------------------修改学生信息--------------------------------------");
                System.out.println("+  1.根据ID修改学生全部信息     2.根据ID修改学生部分信息     3.返回上级目录     4.系统退出   +");
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.print("请选择菜单: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        boolean exist = true;
                        boolean enter = false;
                        while (exist) {
                            System.out.print("请输入要修改学生的id: ");
                            int id = scanner.nextInt();
                            String sql = "select ID from student";
                            resultSet = statement.executeQuery(sql);
                            while (resultSet.next()) {
                                int stuid = resultSet.getInt("ID");
                                if (stuid == id) {
                                    enter = true;
                                    System.out.print("请输入修改后的ID: ");
                                    int newID = scanner.nextInt();
                                    System.out.print("请输入修改后的姓名: ");
                                    String newName = scanner.next();
                                    System.out.print("请输入修改后的性别: ");
                                    String newGender = scanner.next();
                                    System.out.print("请输入修改后的年龄(只能1-120之内的数字): ");
                                    int newAge = scanner.nextInt();
                                    System.out.print("请输入修改后的年级(只能初级、中级、高级): ");
                                    String newGrade = scanner.next();
                                    System.out.print("请输入修改后的地址: ");
                                    String newAddress = scanner.next();
                                    System.out.print("请输入修改后的联系方式(11位手机号码): ");
                                    long newTel = scanner.nextLong();
                                    System.out.print("请输入修改后的电子邮箱(包含@和.com): ");
                                    String newEmail = scanner.next();
                                    sql = "update student set ID = "+newID+",StuName = '"+newName+"',Age = "+newAge+",Gender = '"+newGender+"',Tel = "+newTel+",Email = '"+newEmail+"',Address = '"+newAddress+"' where ID = "+id+"";
                                    statement = connection.createStatement();
                                    statement.execute(sql);
                                    System.out.println("信息修改成功");
                                    exist = false;
                                }
                            }
                            if (!enter) {
                                System.out.println("不存在该ID，请重新输入");
                            }
                        }
                        break;
                    case 2:
                        boolean exist2 = true;
                        boolean enter2 = false;
                        while (exist2) {
                            System.out.print("请输入要修改学生的id: ");
                            int id = scanner.nextInt();
                            String sql = "select ID from student";
                            resultSet = statement.executeQuery(sql);
                            while (resultSet.next()) {
                                int stuid = resultSet.getInt("ID");
                                if (stuid == id) {
                                    enter2 = true;
                                    System.out.print("请输入要修改的属性(ID,Name,Age,Gender,Grade,Tel,Email,Address): ");
                                    String attribute = scanner.next();
                                    switch (attribute) {
                                        case "ID":
                                            System.out.print("请输入修改后的数据: ");
                                            int newID = scanner.nextInt();
                                            sql = "update student set ID = " + newID + " where ID = " + stuid + "";
                                            statement = connection.createStatement();
                                            statement.execute(sql);
                                            System.out.println("修改成功");
                                            System.out.println("系统自动返回上层目录");
                                            exist2 = false;
                                            break;
                                        case "Name":
                                            System.out.print("请输入修改后的数据: ");
                                            String newName = scanner.next();
                                            sql = "update student set StuName = '" + newName + "' where ID = " + stuid + "";
                                            statement = connection.createStatement();
                                            statement.execute(sql);
                                            System.out.println("修改成功");
                                            System.out.println("系统自动返回上层目录");
                                            exist2 = false;
                                            break;
                                        case "Age":
                                            System.out.print("请输入修改后的数据: ");
                                            int newAge = scanner.nextInt();
                                            sql = "update student set Age = " + newAge + " where ID = " + stuid + "";
                                            statement = connection.createStatement();
                                            statement.execute(sql);
                                            System.out.println("修改成功");
                                            System.out.println("系统自动返回上层目录");
                                            exist2 = false;
                                            break;
                                        case "Gender":
                                            System.out.print("请输入修改后的数据: ");
                                            String newGender = scanner.next();
                                            sql = "update student set Gender = '" + newGender + "' where ID = " + stuid + "";
                                            statement = connection.createStatement();
                                            statement.execute(sql);
                                            System.out.println("修改成功");
                                            System.out.println("系统自动返回上层目录");
                                            exist2 = false;
                                            break;
                                        case "Grade":
                                            System.out.print("请输入修改后的数据: ");
                                            String newGrade = scanner.next();
                                            sql = "update student set Grade = '" + newGrade + "' where ID = " + stuid + "";
                                            statement = connection.createStatement();
                                            statement.execute(sql);
                                            System.out.println("修改成功");
                                            System.out.println("系统自动返回上层目录");
                                            exist2 = false;
                                            break;
                                        case "Tel":
                                            System.out.print("请输入修改后的数据: ");
                                            long newTel = scanner.nextLong();
                                            sql = "update student set Tel = " + newTel + " where ID = " + stuid + "";
                                            statement = connection.createStatement();
                                            statement.execute(sql);
                                            System.out.println("修改成功");
                                            System.out.println("系统自动返回上层目录");
                                            exist2 = false;
                                            break;
                                        case "Email":
                                            System.out.print("请输入修改后的数据: ");
                                            String newEmail = scanner.next();
                                            sql = "update student set Email = '" + newEmail + "' where ID = " + stuid + "";
                                            statement = connection.createStatement();
                                            statement.execute(sql);
                                            System.out.println("修改成功");
                                            System.out.println("系统自动返回上层目录");
                                            exist2 = false;
                                            break;
                                        case "Address":
                                            System.out.print("请输入修改后的数据: ");
                                            String newAddress = scanner.next();
                                            sql = "update student set Address = '" + newAddress + "' where ID = " + stuid + "";
                                            statement = connection.createStatement();
                                            statement.execute(sql);
                                            System.out.println("修改成功");
                                            System.out.println("系统自动返回上层目录");
                                            exist2 = false;
                                            break;
                                        default:
                                            System.out.println("不存在该属性");
                                            break;
                                    }
                                }
                            }
                            if (!enter2) {
                                System.out.println("不存在该ID，请重新输入");
                            }
                        }
                        break;
                    case 3:
                        System.out.println("返回上一级中·········");
                        flag = false;
                        operationPanel();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("输出有误");
                        break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
