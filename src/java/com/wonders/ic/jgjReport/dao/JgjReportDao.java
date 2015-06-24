package com.wonders.ic.jgjReport.dao;

import java.util.List;

import com.wonders.ic.jgjReport.entity.bo.DwJgjMajor;
import com.wonders.ic.jgjReport.entity.bo.DwJgjMajorCount;
import com.wonders.ic.jgjReport.entity.bo.DwJgjMajorSeason;
import com.wonders.ic.jgjReport.entity.bo.DwJgjProject;
import com.wonders.ic.jgjReport.entity.bo.DwJgjProjectCount;
import com.wonders.ic.jgjReport.entity.bo.DwJgjProjectProgress;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface JgjReportDao extends AbstractHibernateDao<DwJgjMajor>{

	/**
	 * 查询
	 * @param year 年份
	 * @param type 类型
	 * @return
	 */
	public List<DwJgjMajor> findByYearAndType(String year,String type);
	
	/**
	 * 查询所有年份
	 * @return
	 */
	public List<String> findAllYearJgjMajor();
	
	/**
	 * 查询所有年份
	 * @return
	 */
	public List<String> findAllYearJgjProject();
	
	/**
	 * 查询 项目详细
	 * @param year
	 * @return
	 */
	public List<DwJgjProject> findByYearJgjProject(String year);
	
	public void saveProject(DwJgjProject dwJgjProject);
	public void saveProjectCount(DwJgjProjectCount dwJgjProjectCount);
	public void saveProjectProgress(DwJgjProjectProgress dwJgjProjectProgress);
	public void saveDwJgjMajorCount(DwJgjMajorCount dwJgjMajorCount);
	public void saveDwJgjMajorSeason(DwJgjMajorSeason dwJgjMajorSeason);
	
	/**
	 * 查询JgjProject(后台)
	 * @param year
	 * @return
	 */
	public List<DwJgjProject> findJgjProjectByYear(String year);
	
	/**
	 * 查询JgjProjectCount(后台)
	 * @param year
	 * @return
	 */
	public List<DwJgjProjectCount> findJgjProjectCountByYear(String year);
	
	/**
	 * 查询JgjProjectProgress(后台)
	 * @param year
	 * @param season
	 * @return
	 */
	public List<DwJgjProjectProgress> findJgjProjectProgressByYearAndSeason(String year, Long season);
	
	/**
	 * 查询JgjMajor(后台)
	 * @param year
	 * @return
	 */
	public List<DwJgjMajor> findJgjMajorByYear(String year);
	
	/**
	 * 查询JgjMajorCount(后台)
	 * @param year
	 * @return
	 */
	public List<DwJgjMajorCount> findJgjMajorCountByYear(String year);
	
	/**
	 * 查询JgjMajorSeason(后台)
	 * @param year
	 * @param season
	 * @return
	 */
	public List<DwJgjMajorSeason> findJgjMajorSeasonByYearAndSeason(String year, Long season);
	
	/**
	 * 查询JgjMajor
	 * @param year
	 * @param type
	 * @return
	 */
	public List<DwJgjMajor> findJgjMajorByYearAndType(String year, String type);
	
	/**
	 * 查询 项目详细
	 * @param year
	 * @return
	 */
	public List<DwJgjProjectCount> findByYearJgjProjectCount(String year); 

	/**
	 * 查询所有年份
	 * @return
	 */
	public List<String> findAllYearJgjProjectCount();
	

	/**
	 * 查询所有年份
	 * @return
	 */
	public List<String> findAllYearJgjMajorCount();
	
	public List<DwJgjMajorCount> findByYearJgjMajorCount(String year);
	
	/**
	 * 查询所有年份
	 * @return
	 */
	public List<String> findAllYearJgjProjectProgress();
	
	public List<DwJgjProjectProgress> findByYearJgjProjectProgress(String year,String season);
	
	
	/**
	 * 查询所有年份
	 * @return
	 */
	public List<String> findAllYearJgjMajorSeason();
	
	public List<DwJgjMajorSeason> findByYearJgjMajorSeason(String year,String season);
	
	
	public void deleteAllDwJgjProjectCountByYear(String year);
	public void deleteAllDwJgjMajorCountByYear(String year);
	public void deleteAllDwJgjMajorByYear(String year);
	public void deleteAllDwJgjProjectByYear(String year);
	public void deleteAllDwJgjMajorSeasonByYear(String year);
	public void deleteAllDwJgjProjectProgressByYear(String year);
}
