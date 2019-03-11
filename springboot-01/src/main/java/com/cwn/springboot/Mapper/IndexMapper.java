package com.cwn.springboot.Mapper;

import com.cwn.springboot.bean.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface IndexMapper {

    List<Map<String,Object>> listUser();

    public UserVO getUserInfo(String username);//获取会员信息
    public UserVO getUserInfoById(Integer userid);//获取会员信息

    List<String>  getImgpath(String username); //主页面展示图片
    List<Map<String, Object>> getImgpathListbyUser(String username); //主页面展示图片List
    List<Map<String, Object>> getImgpathListbyImgid(Long imgid); //获取单图片信息

    String getHeadImgpath(String username); //头像图片

    List<Map<String, Object>> getHeadImgpathList(String username); //头像图片List

    List<Map<String, Object>> getTeams();//获取团队会员

    List<Map<String, Object>> getImgpathListbyDocumentfile(Long documentfileid); //获取文件夹图片信息


    public UserVO getUserByUP(String username,String password);//根据用户名和密码获取会员
}
