package com.cwn.springboot.service;

import com.cwn.springboot.Mapper.AccountMapper;
import com.cwn.springboot.bean.Documentfile;
import com.cwn.springboot.bean.Img;
import com.cwn.springboot.bean.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

@Repository
@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public UserVO getUserInfoById(Integer id){

        return accountMapper.getUserInfoById(id);
    }

    void updateUserInfo(UserVO userVO) {
        accountMapper.updateUserInfo(userVO);
    }

    void updateHeadImgEmpty(Img img) {
        accountMapper.updateHeadImgEmpty(img);
    }

    void updateHeadImg(Img img) {
        accountMapper.updateHeadImg(img);
    }

    public List<Map<String, Object>> getDocumentfileByUser(Integer userid){
        return accountMapper.getDocumentfileByUser(userid);
    }

    public boolean insertDocumentfileByUser(Documentfile documentfile) {
        try {
            accountMapper.insertDocumentfileByUser(documentfile);
            return true;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }



    }

@Transactional(propagation = Propagation.REQUIRED)  //开启事务
    public boolean deleteDocumentfileById(Integer documentfileid)throws Exception {
        try {
            System.out.println("删除1");
            accountMapper.deleteDocumentfileById(documentfileid);
            System.out.println("删除2");
            accountMapper.deleteImgByDfId(documentfileid);
            System.out.println("******************结束");
throw new Exception("进来1111111");
//            return true;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }

    }

    public boolean deleteImgByDfId(Integer documentfileid) {
        try {
            accountMapper.deleteImgByDfId(documentfileid);
            return true;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    public boolean updateDocumentfileById(Integer documentfileid,String documentfilename) {

        try {
            accountMapper.updateDocumentfileById(documentfileid,documentfilename);
            return true;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }


    public List<Map<String, Object>> getImgByDocumentfile(Integer documentfileid){
        return accountMapper.getImgByDocumentfile(documentfileid);
    }

    public List<UserVO> selsetUserList(){

        return accountMapper.selsetUserList();
    }
}
