package houserent.service;

import houserent.domain.House;
import houserent.utils.Utility;

import java.util.Arrays;

/**
 * 业务类
 * 用于逻辑实现
 * 用于保存实例操作所需要的各种信息(数据库)
 */
public class HouseService {
    private House[] houses; //创建数组对象,存储House类实例
    private int houseNum = 1; //记录当前有多少个房屋信息
    private int idCounter = 1; //记录当前的id自增长到哪个值

    //构造器
    public HouseService(int size) {
        houses = new House[size];
        //初始化数组
        houses[0] = new House(1, "jack", "112", "海淀区", 2000, "未出租");
    }

    // 添加房屋信息
    public boolean addHouse(House house) {
        if (houseNum >= houses.length) {
            // 调用copyOf方法把原数组复制到新数组,扩容为原数组的2倍
            houses = Arrays.copyOf(houses, houses.length * 2);
        }
        house.setId(++idCounter); // 先自增再设置ID
        houses[houseNum++] = house;
        return true;
    }

    //查找房屋信息
    public House findHouse(int id) {
        for (int i = 0; i < houseNum; i++) {
            if (id == houses[i].getId()) {
                return houses[i];
            }
        }
        return null;
    }

    //删除房屋信息
    public boolean deleteHouse(int id) {
        int index = -1; // 记录要删除的房屋信息在数组中的索引位置
        //遍历数组,查找要删除的房屋信息的索引位置
        for (int i = 0; i < houseNum; i++) {
            if (id == houses[i].getId()) {
                index = i;
            }
        }
        if (index == -1) {
            return false;
        }
        // ** 这里类似顺序表的删除操作 **
        for (int i = index; i < houseNum - 1; i++) {
            houses[i] = houses[i + 1]; // 后一个元素覆盖前一个元素
            houses[i].setId(i + 1); // 把新的ID设置给房屋,ID和下标的关系是Id=i+1
        }
        houses[houseNum - 1] = null; // 把最后一个元素置为null
        houseNum--; // 房屋数量减1
        return true;
    }

    //修改房屋信息
    public void updateHouse(int id, House house) {
        for (int i = 0; i < houseNum; i++) {
            if (id == houses[i].getId()) {
                houses[i] = house; //通过查询id把原有house覆盖
            }
        }
    }

    //返回houses数组
    public House[] returnList() {
        return houses;
    }
}
