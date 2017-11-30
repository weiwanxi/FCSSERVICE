package com.fcsservice.service;

import com.fcsservice.dao.*;
import com.fcsservice.model.pojo.*;
import com.fcsservice.utils.FcsserviceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by YE on 2017/11/30 16:17.
 */

@Service
@Transactional
public class CommentService {
    @Autowired
    CommentDao commentDao;
    @Autowired
    AccountDao accountDao;
    @Autowired
    FabulousDao fabulousDao;
    @Autowired
    InformationDao informationDao;
    @Autowired
    WorkDao workDao;
    @Autowired
    ClothDao clothDao;
    @Autowired
    CostumeDao costumeDao;

    public boolean deleteComment(String commentId){
        if (commentDao.getComment(commentId) != null){
            commentDao.deleteAComment(commentId);
            return true;
        }else {
            return false;
        }
    }

    public void deleteAllComment(String userId){
        commentDao.deleteAllCommentByUserId(userId);
    }

    public Map<String,String[]> getCommentList(String informationId,String userId){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,String[]> map = new HashMap<String, String[]>();

        List<Comment> commentList = commentDao.getCommentList(informationId);
        if (commentList != null){
            String[] id = new String[commentList.size()];
            String[] commentor = new String[commentList.size()];
            String[] comment = new String[commentList.size()];
            String[] time = new String[commentList.size()];
            String[] fabulousNumber = new String[commentList.size()];
            String[] fabulousType = new String[commentList.size()];

            for (int i=0;i<commentList.size();i++){
                id[i] = commentList.get(i).getCommentId();
                UserAccount account = accountDao.getUserAccountById(commentList.get(i).getCommentatorId());
                if (account != null){
                    commentor[i] = account.getUserAccount();
                }else {
                    commentor[i] = "用户";
                }
                comment[i] = commentList.get(i).getCommentContent();
                time[i] = format.format(commentList.get(i).getCommentReltime());
                fabulousNumber[i] = fabulousDao.getFabulous(commentList.get(i).getCommentId())+"";
                fabulousType[i] = fabulousDao.getFabulousType(commentList.get(i).getCommentId(),userId)? FcsserviceUtil.FabulousTrue:FcsserviceUtil.FabulousFalse;
            }
            map.put("id",id);
            map.put("commentor",commentor);
            map.put("comment",comment);
            map.put("time",time);
            map.put("number",fabulousNumber);
            map.put("type",fabulousType);
        }else {
            return null;
        }

        return map;
    }

    public void saveComment(String userid,String commentText,String informationId,String informationType){
        Comment comment = new Comment();
        String commentId = UUID.randomUUID().toString().replaceAll("-", "");
        comment.setCommentId(commentId);
        comment.setCommentatorId(userid);
        comment.setCommentContent(commentText);
        comment.setCommenttaryType(Integer.parseInt(informationType));
        comment.setCommentaryId(informationId);
        comment.setCommentReltime(new Date());

        commentDao.saveComment(comment);
    }

    public int getCommentNumber(String informationId){
        return commentDao.getCommentNumber(informationId);
    }

    public Map<String,String[]> getUserCommentList(String userId){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,String[]> map = new HashMap<String, String[]>();

        List<Comment> commentList = commentDao.getUserCommentList(userId);
        if (commentList != null){
            String[] id = new String[commentList.size()];
            String[] comment = new String[commentList.size()];
            String[] time = new String[commentList.size()];
            String[] informationId = new String[commentList.size()];
            String[] informationTitle = new String[commentList.size()];
            String[] informationType = new String[commentList.size()];

            for (int i=0;i<commentList.size();i++){
                id[i] = commentList.get(i).getCommentId();
                comment[i] = commentList.get(i).getCommentContent();
                time[i] = format.format(commentList.get(i).getCommentReltime());
                informationId[i] = commentList.get(i).getCommentaryId();
                if (commentList.get(i).getCommenttaryType() == 1){
                    Information information = informationDao.getInformationById(commentList.get(i).getCommentaryId());
                    if (information != null){
                        informationTitle[i] = "资讯："+information.getInformationTopic();
                    }else {
                        informationTitle[i] = "失效资讯";
                    }
                }else if(commentList.get(i).getCommenttaryType() == 2){
                    Work work = workDao.getWorkById(commentList.get(i).getCommentaryId());
                    if (work != null){
                        informationTitle[i] = "作品："+work.getWorkName();
                    }else {
                        informationTitle[i] = "失效作品";
                    }
                }else if(commentList.get(i).getCommenttaryType() == 3){
                    Cloth cloth = clothDao.getClothById(commentList.get(i).getCommentaryId());
                    if (cloth != null){
                        informationTitle[i] = "布料："+cloth.getClothName();
                    }else {
                        informationTitle[i] = "失效布料";
                    }
                }else if(commentList.get(i).getCommenttaryType() == 4){
                    Costume costume = costumeDao.getCostumeById(commentList.get(i).getCommentaryId());
                    if (costume != null){
                        informationTitle[i] = "服装："+costume.getCostumeName();
                    }else {
                        informationTitle[i] = "失效服装";
                    }
                }
                informationType[i] = commentList.get(i).getCommenttaryType()+"";
            }

            map.put("id",id);
            map.put("comment",comment);
            map.put("time",time);
            map.put("information",informationId);
            map.put("informationTitle",informationTitle);
            map.put("informationType",informationType);
        }else {
            return null;
        }

        return map;
    }
}
