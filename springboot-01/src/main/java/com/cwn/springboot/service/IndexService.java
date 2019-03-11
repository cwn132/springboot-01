package com.cwn.springboot.service;

import com.cwn.springboot.bean.UserVO;
import com.cwn.springboot.Mapper.IndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Repository
@Service
public class IndexService {

    @Autowired
    private IndexMapper IndexMapper;

    public List<Map<String,Object>> listUser() {
        return IndexMapper.listUser();
    }

    public UserVO getUserInfo(String username){

        return IndexMapper.getUserInfo(username);
    }

    public UserVO getUserInfoById(Integer id){

        return IndexMapper.getUserInfoById(id);
    }


    public List<String> getImgpath(String username){
        return IndexMapper.getImgpath(username);
    }

    public  String  getHeadImgpath(String username){
        return IndexMapper.getHeadImgpath(username);
    }

    public  List<Map<String, Object>> getHeadImgpathList(String username)
    {
        return IndexMapper.getHeadImgpathList(username);
    }

    public List<Map<String, Object>> getTeams() {
        return IndexMapper.getTeams();
    }

    public  List<Map<String, Object>> getImgpathListbyUser(String username)
    {
        return IndexMapper.getImgpathListbyUser(username);
    }

    public  List<Map<String, Object>> getImgpathListbyImgid(Long imgid)
    {
        return IndexMapper.getImgpathListbyImgid(imgid);
    }

    public  List<Map<String, Object>> getImgpathListbyDocumentfile(Long documentfileid)
    {
        return IndexMapper.getImgpathListbyDocumentfile(documentfileid);
    }

    public UserVO getUserByUP(String username,String password){

        return IndexMapper.getUserByUP(username,password);
    }

}
