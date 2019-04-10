package com.cwn.springboot.Mapper;

import com.cwn.springboot.bean.Documentfile;
import com.cwn.springboot.bean.Img;
import com.cwn.springboot.bean.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccountMapper {

    public UserVO getUserInfoById(Integer userid);//获取会员信息

    void updateUserInfo(UserVO userVO);//更新用户信息

    void updateHeadImgEmpty(Img img);//更新头像为空

    void updateHeadImg(Img img);//更新头像图片

    List<Map<String, Object>>  getDocumentfileByUser(Integer password);//根据用户名ID获取文件夹


    public boolean insertDocumentfileByUser(Documentfile documentfile); //新建文件夹

    public boolean deleteDocumentfileById(int documentfileid); //删除文件夹

    public boolean deleteImgByDfId(int documentfileid); //删除图片

    public boolean updateDocumentfileById(int documentfileid,String documentfilename);//更新文件夹

    List<Map<String, Object>>  getImgByDocumentfile(Integer documentfileid);//根据文件夹ID获取图片

    public List<UserVO> selsetUserList();
}
