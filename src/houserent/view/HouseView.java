package houserent.view;

import houserent.domain.House;
import houserent.service.HouseService;
import houserent.utils.Utility;

/**
 * house_view类
 * 显示界面
 */

public class HouseView {
    private boolean loop = true;
    private char key = ' '; //接收用户输入
    private HouseService houseService = new HouseService(2);

    /**
     * 显示主菜单
     */
    public void mainMenu() {
        do {
            System.out.println("===================房屋出租系统==============");
            System.out.println("\t\t\t1 新 增 房 源");
            System.out.println("\t\t\t2 查 找 房 屋");
            System.out.println("\t\t\t3 删 除 房 屋 信 息");
            System.out.println("\t\t\t4 修 改 房 屋 信 息");
            System.out.println("\t\t\t5 房 屋 列 表");
            System.out.println("\t\t\t6 退      出");
            System.out.println("请输入你的选择(1-6)");
            key = Utility.readChar();
            switch (key) {
                case '1':
                    addHouse();
                    break;
                    case '2':
                    findHouse();
                    break;
                    case '3':
                    deleteHouse();
                    break;
                    case '4':
                    updateHouse();
                    break;
                    case '5':
                    listHouse();
                    break;
                    case '6':

                    break;

            }
        } while (loop);
    }

    /**
     * 显示房屋列表
     */
    public void listHouse() {
        System.out.println("===================房屋列表====================");
        System.out.println("编号\t房主\t电话\t地址\t月租\t状态(未出租/已出租)");
        House[] houses = houseService.returnList(); //获取houses数组信息
        for (int i = 0; i < houses.length; i++) { //遍历houses数组打印房屋信息
            if (houses[i] == null) {
                break;
            }
            System.out.println(houses[i]);
        }
        System.out.println("===================房屋列表完成====================");

    }

    /**
     * 增加房屋
     */
    public boolean addHouse() {
        System.out.println("===================添加房屋====================");
        System.out.println("姓名：");
        String name = Utility.readString(8);
        System.out.println("电话：");
        String phone = Utility.readString(12);
        System.out.println("地址：");
        String address = Utility.readString(16);
        System.out.println("月租：");
        int rent = Utility.readInt();
        System.out.println("状态：");
        String state = Utility.readString(3);
        House newHouse = new House(0, name, phone, address, rent, state);
        if (houseService.addHouse(newHouse)) {
            System.out.println("添加成功");
            return true;
        } else {
            System.out.println("添加失败");
            return false;
        }
    }

    public void findHouse() {
        System.out.println("===================查找房屋====================");
        System.out.println("请输入待查找房屋的编号ID (-1退出)：");
        int findId = Utility.readInt();
        if (findId == -1) {
            System.out.println("放弃查找房屋信息");
            return; //结束方法
        }
        //把id传递到业务逻辑层处理
        House house = houseService.findHouse(findId);
        if (house != null) {
            System.out.println(house);
        } else {
            System.out.println("查找房屋信息ID=" + findId + "不存在");
        }
    }


    /**
     * 删除房屋
     */
    public void deleteHouse() {
        System.out.println("===================删除房屋====================");
        System.out.println("请输入待删除房屋的编号ID (-1退出)：");
        int delId = Utility.readInt();
        if (delId == -1) {
            System.out.println("放弃删除房屋信息");
            return; //结束方法
        }
        System.out.println("确认是否删除(Y/N):");
        char choice = Utility.readConfirmSelection();
        if (choice == 'N') {
            System.out.println("放弃删除房屋信息");
            return; //结束方法
        } else if (choice == 'Y') {
            if (houseService.deleteHouse(delId)) {
                System.out.println("===================删除房屋成功====================");
            } else {
                System.out.println("===================房屋编号不存在，删除房屋失败====================");
            }
        }
    }

    /**
     * 修改房屋信息
     */
    public void updateHouse() {
        System.out.println("===================修改房屋====================");
        System.out.println("请输入待修改房屋的编号ID (-1退出)：");
        int updateId = Utility.readInt();
        if (updateId == -1) {
            System.out.println("放弃修改房屋信息");
            return; //结束方法
        }
        //把id传递到业务逻辑层处理
        House house = houseService.findHouse(updateId);
        if (house != null) {
            System.out.println("姓名：");
            house.setName(Utility.readString(8));
            System.out.println("电话：");
            house.setPhone(Utility.readString(12));
            System.out.println("地址：");
            house.setAddress(Utility.readString(16));
            System.out.println("月租：");
            house.setRent(Utility.readInt());
            System.out.println("状态：");
            house.setState(Utility.readString(3));
            houseService.updateHouse(house.getId(), house);
            System.out.println("===================修改房屋成功====================");
        } else {
            System.out.println("查找房屋信息ID=" + updateId + "不存在");
        }
    }

    /**
     * 退出程序确认
     */
    public void exit() {
     char c = Utility.readConfirmSelection();
     if (c == 'Y') {
        loop = false;
     }
    }

}
