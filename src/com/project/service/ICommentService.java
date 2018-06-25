package com.project.service;


import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Comment;


public interface ICommentService {
	
    /***
     * 保存
     * @param c
     * @return
     */
    public Comment saveComment(Comment c);
    
    /**
     * 更新
     * @param c
     * @return
     */
	public Comment mergeComment(Comment c);
	
	/**
	 * 删除评论
	 * @param id
	 */
	public void deleteComment(int id);
	
	/**
	 * 得到评论
	 * @param id
	 * @return
	 */
	public Comment getCommentById(int id);
	
	public PageFinder<Comment> getPageFinderComment(Comment c,int pageNumber, int pageSize);
	
	/**
	 * 根据传递过来的ID和类型去查询相应的评论数据
	 * @param activeId
	 * @param orderType
	 * @return
	 */
	public List<Comment> getComment(Integer activeId,Integer orderType);
    
}
