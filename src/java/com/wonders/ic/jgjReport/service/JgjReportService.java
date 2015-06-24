package com.wonders.ic.jgjReport.service;

import java.util.List;

import com.wonders.ic.jgjReport.entity.bo.DwJgjMajor;
import com.wonders.ic.jgjReport.entity.bo.DwJgjMajorCount;
import com.wonders.ic.jgjReport.entity.bo.DwJgjMajorSeason;
import com.wonders.ic.jgjReport.entity.bo.DwJgjProject;
import com.wonders.ic.jgjReport.entity.bo.DwJgjProjectCount;
import com.wonders.ic.jgjReport.entity.bo.DwJgjProjectProgress;

public interface JgjReportService {

	public List<DwJgjMajor> findByYearAndTypeJgjMajor(String year,String type);
	
	public List<String> findAllYearJgjMajor();
	
	public List<String> findAllYearJgjProject();
	
	public List<DwJgjProject> findByYearAndTypeJgjProject(String year);
	
	public void calculateDwJgjProject(String year);
	
	public void calculateDwJgjProjectCount(String year);
	
	public void calculateDwJgjProjectProgress(String year);
	
	public void calculateDwJgjMajor(String year);
	
	public void calculateDwJgjMajorCount(String year);
	
	public void calculateDwJgjMajorSeason(String year);
	
	public List<DwJgjProjectCount> findByYearAndTypeJgjProjectCount(String year);
	
	public List<String> findAllYearJgjProjectCount();
	
	
	public List<String> findAllYearJgjMajorCount();
	
	public List<DwJgjMajorCount> findByYearJgjMajorCount(String year);
	
	
	public List<String> findAllYearJgjProjectProgress();
	
	public List<DwJgjProjectProgress> findByYearJgjProjectProgress(String year,String season);
	
	public List<String> findAllYearJgjMajorSeason();
	
	public List<DwJgjMajorSeason> findByYearJgjMajorSeason(String year,String season);
	
	
	
	public void deleteAllDwJgjProjectCountByYear(String year);
	public void deleteAllDwJgjMajorCountByYear(String year);
	public void deleteAllDwJgjMajorByYear(String year);
	public void deleteAllDwJgjProjectByYear(String year);
	public void deleteAllDwJgjMajorSeasonByYear(String year);
	public void deleteAllDwJgjProjectProgressByYear(String year);
}
