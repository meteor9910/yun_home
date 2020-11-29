package com.hopu.web.controller.front;


import com.github.pagehelper.PageInfo;
import com.hopu.pojo.Region;
import com.hopu.pojo.Room;

import com.hopu.pojo.RoomImg;
import com.hopu.pojo.User;
import com.hopu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/front/room")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private RoomImgService roomImgService;
    @Autowired
    private UserService userService;
    @Autowired
    private FavoriteService favoriteService;

    /**
     * 前台分页显示房屋信息
     */

    @RequestMapping(value="/list" ,name="分页查询所有房屋信息页面")
    public String findAll(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                            @RequestParam(value = "regionId",required = false) Integer  regionId,
                            @RequestParam(value= "regionName",required = false) String regionName,
                            @RequestParam(value = "rent",required = false) String rent,
                            HttpServletRequest request){
       if(rent!=null&& rent !=""){
           //500_1000
           if(rent.contains("元")){
               rent = rent.substring(0,rent.indexOf("元"));


           }
       }
        PageInfo<Room> pageInfo = roomService.findPageFront(pageNum,pageSize,regionId,rent);

       //同步查询房源信息
        List<Region> regionList = regionService.findAll();

        //把请求数据放在域中
        request.setAttribute("page",pageInfo);
        request.setAttribute("regionList",regionList);

        request.setAttribute("pageNum",pageNum);
        request.setAttribute("pageSize",pageSize);
        // 筛选条件回显
        request.setAttribute("regionId",regionId);
        request.setAttribute("regionName",regionName);

        //对租金进行管理
        if(rent!=null&& rent!=""){
            if (rent.equals("0_500")){
                rent="500以下";
            }else if (rent.equals("4500_99999")){
                rent = "4500以上";
            }else {
                rent = rent+"元";
            }


        }
        request.setAttribute("rent",rent);
        return  "front/room_list";
    }

    /**
     * 跳转到房源详情页
     * @return
     */
    @RequestMapping("/toRoomDetails")
    public String toRoomDetailsPage(Integer id,HttpServletRequest request){
        Room room = roomService.findById(id);
        // 根据前端页面需要，还可以查询房源对应的图片信息、以及房东信息、区域信息
        List<RoomImg> roomImgList = roomImgService.findByRoomId(room.getId());
        room.setRoomImgList(roomImgList);


        User user = userService.findById(room.getUserId());
        room.setUser(user);

        Region region = regionService.findById(room.getRegionId());
        room.setRegion(region);

        //存储在域对象中
        request.setAttribute("room",room);
        return "front/room_details";


    }
    //房源详情页判断房屋收藏状态
    @RequestMapping("/ifFavorite")
    @ResponseBody
    public String ifFavorite(Integer roomId,HttpServletRequest request){
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            return "-1";
        }else {
            User user = (User)loginUser;
            boolean ifFavorite = favoriteService.findIfFavorite(roomId,user.getId());
            if (ifFavorite) {
                //表示已经恢复
                return "1";
            }else {
                return "0";//表示登录，未收藏
            }
        }
    }

    //取消收藏
    @RequestMapping("/cancleFavorite")
    @ResponseBody
    public String cancelFavorite(Integer roomId, HttpServletRequest request){
        try {
            User loginUser = (User) request.getSession().getAttribute("loginUser");
            favoriteService.cancleFavorite(roomId, loginUser.getId());
            return "0";
        }catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }

    //添加收藏
    @RequestMapping("/addFavorite")
    @ResponseBody
    public String addFavorite(Integer roomId, HttpServletRequest request){

        try {
            User loginUser = (User) request.getSession().getAttribute("loginUser");
            favoriteService.addFavorite(roomId,loginUser.getId());
            return "0";

        }catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }

    }
}
