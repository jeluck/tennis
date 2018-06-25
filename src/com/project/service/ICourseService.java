package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Course;

public interface ICourseService {

	/**
	 * 获得所有课程
	 * @return
	 */
	 public List<Course> getAllCourse(int city_show_id,int stick,String name);

	 /***
     * 增加课程
     * @param c
     * @return
     */
    public Course saveCourse(Course o);
    
    /***
     * 更新课程
     * @param c
     * @return
     */
    public Course updateCourse(Course o);
    
    /**
     * 根据id获得课程
     * @param id
     * @return
     */
    public Course getCourseById(int id);
    
    /**
     * 获得课程列表(带分页)
     * @param c
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public  PageFinder<Course> getCourseList(Course o, int pageNumber, int pageSize );
    
    /**
     * 根据用户编号获得课程
     * @param userId
     * @return
     */
    public Course getCourseByUserId(int userId);
    
}
