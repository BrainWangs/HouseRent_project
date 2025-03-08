package houserent;

import houserent.view.HouseView;
/**
 * 程序入口
 * 创建HouseView对象，并调用其mainMenu方法
 */
public class HouseRentApp {
    public static void main(String[] args) {
        //这里是匿名对象的用法, 创建实例后直接调用方法
        new HouseView().mainMenu();
        //当do while退出时，显示退出信息
        System.out.println("退出");
    }
}
