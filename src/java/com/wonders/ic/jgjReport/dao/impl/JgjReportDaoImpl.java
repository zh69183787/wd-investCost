package com.wonders.ic.jgjReport.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.wonders.ic.jgjReport.dao.JgjReportDao;
import com.wonders.ic.jgjReport.entity.bo.DwJgjMajor;
import com.wonders.ic.jgjReport.entity.bo.DwJgjMajorCount;
import com.wonders.ic.jgjReport.entity.bo.DwJgjMajorSeason;
import com.wonders.ic.jgjReport.entity.bo.DwJgjProject;
import com.wonders.ic.jgjReport.entity.bo.DwJgjProjectCount;
import com.wonders.ic.jgjReport.entity.bo.DwJgjProjectProgress;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class JgjReportDaoImpl extends AbstractHibernateDaoImpl<DwJgjMajor> implements JgjReportDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<DwJgjMajor> findByYearAndType(String year, String type) {
		String hql="from DwJgjMajor t where t.year=? and t.typename=? order by to_number(t.order_) ASC";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, year).setString(1, type);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllYearJgjMajor() {
		String sql="select t.year year from Dw_Jgj_Major t group by t.year order by to_number(t.year) DESC";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.addScalar("year",Hibernate.STRING);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllYearJgjProject() {
		String sql="select t.year year from Dw_Jgj_Project t group by t.year order by to_number(t.year) DESC";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.addScalar("year",Hibernate.STRING);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DwJgjProject> findByYearJgjProject(String year) {
		String hql="from DwJgjProject t where t.year=? order by t.professionalType,t.dispatchNo,t.projectName";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, year);
		return query.list();
	}

	@Override
	public void saveProject(DwJgjProject dwJgjProject) {
		getHibernateTemplate().saveOrUpdate(dwJgjProject);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DwJgjProject> findJgjProjectByYear(String year) {
		StringBuilder sql = new StringBuilder();
		sql.append("Select P.Dispatch_No dispatchNo, P.Professional_Type professionalType, P.Project_Name projectName, ");
		sql.append(" P.Project_Adddept mainExecute, C.Contract_Owner_Name feeDepartment, C.Contract_Name contractName, ");
		sql.append(" round(to_number(P.Project_Budget_All),6) approvalBugget, P.Project_Type maintainType, ");
		sql.append(" decode(C.Invite_Bid_Type, 3, C.Build_Supplier_Name, '') appointMaintain, ");
		sql.append(" decode(C.Invite_Bid_Type, 2, C.Build_Supplier_Name, '') bidInvaiting, ");
		sql.append(" Round(To_Number(C.Contract_Price),6) contractPrice, C.id contractId, ");
		sql.append(" Round(To_Number(C.NATION_VERIFY_PRICE),6) nationVerifyPrice, ");
		sql.append(" Round(To_Number(C.VERIFY_PRICE),6) verifyPrice, Round(To_Number(C.FINAL_PRICE),6) finalPrice, ");
		sql.append(" Progress.PROGRESS_TYPE projectProgress, :year year, sysdate createTime ");
		sql.append(" From C_Contract C Left Outer Join C_Project P On P.Id = C.Project_Id And P.Removed = 0 ");
		sql.append(" Left Outer Join (Select Object_Id, Progress_Type From (Select A.*, ");
		sql.append(" Row_Number() Over (Partition By Object_Id Order By Update_Date Desc, Create_Date Desc) Rn ");
		sql.append(" From C_Progress A) Where Rn <= 1 And Object_Type = 2  And Removed = 0) ");
		sql.append(" Progress On Progress.Object_Id = P.Id ");
		sql.append(" Where C.Removed = 0 and P.Professional_Type is not null and P.Project_Type in('2','3')");
		
		Query query = getSession().createSQLQuery(sql.toString())
			.addScalar("dispatchNo", Hibernate.STRING)
			.addScalar("professionalType", Hibernate.STRING)
			.addScalar("projectName", Hibernate.STRING)
			.addScalar("mainExecute", Hibernate.STRING)
			.addScalar("feeDepartment", Hibernate.STRING)
			.addScalar("contractName", Hibernate.STRING)
			.addScalar("approvalBugget", Hibernate.BIG_DECIMAL)
			.addScalar("maintainType", Hibernate.STRING)
			.addScalar("appointMaintain", Hibernate.STRING)
			.addScalar("bidInvaiting", Hibernate.STRING)
			.addScalar("contractPrice", Hibernate.BIG_DECIMAL)
			.addScalar("nationVerifyPrice", Hibernate.BIG_DECIMAL)
			.addScalar("verifyPrice", Hibernate.BIG_DECIMAL)
			.addScalar("finalPrice", Hibernate.BIG_DECIMAL)
			.addScalar("projectProgress", Hibernate.STRING)
			.addScalar("year", Hibernate.STRING)
			.addScalar("createTime", Hibernate.TIMESTAMP)
			.addScalar("contractId", Hibernate.STRING)
			.setResultTransformer(Transformers.aliasToBean(DwJgjProject.class))
			.setParameter("year", year);
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DwJgjMajor> findJgjMajorByYear(String year) {
		StringBuilder sql = new StringBuilder();
		sql.append("select T.Object_Name lineName, t.order_, sysdate createTime, :year year ");
		sql.append("from C_COMPANY_ROUTE t where t.type ='2' and t.pid ='01' and T.Removed = 0 ");
		Query query = getSession().createSQLQuery(sql.toString())
			.addScalar("lineName", Hibernate.STRING)
			.addScalar("order_", Hibernate.STRING)
			.addScalar("createTime", Hibernate.TIMESTAMP)
			.addScalar("year", Hibernate.STRING)
			.setResultTransformer(Transformers.aliasToBean(DwJgjMajor.class))
			.setParameter("year", year);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DwJgjProjectCount> findByYearJgjProjectCount(String year) {
		String hql="from DwJgjProjectCount t where t.year=? order by t.professionalType,t.dispatchNo,t.projectName";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, year);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllYearJgjProjectCount() {
		String sql="select t.year year from Dw_Jgj_Project_Count t group by t.year order by to_number(t.year) DESC";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.addScalar("year",Hibernate.STRING);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllYearJgjMajorCount() {
		String sql="select t.year year from Dw_Jgj_Major_count t group by t.year order by to_number(t.year) DESC";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.addScalar("year",Hibernate.STRING);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DwJgjMajorCount> findByYearJgjMajorCount(String year) {
		String hql="from DwJgjMajorCount t where t.year=? order by to_number(t.order_) ASC";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, year);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllYearJgjProjectProgress() {
		String sql="select t.year year from Dw_Jgj_Project_Progress t group by t.year order by to_number(t.year) DESC";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.addScalar("year",Hibernate.STRING);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DwJgjProjectProgress> findByYearJgjProjectProgress(String year,String season) {
		String hql="from DwJgjProjectProgress t where t.year=? and t.season=? order by t.professionalType,t.dispatchNo,t.projectName";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, year).setLong(1, Long.valueOf(season));
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllYearJgjMajorSeason() {
		String sql="select t.year year from Dw_Jgj_Major_Season t group by t.year order by to_number(t.year) DESC";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.addScalar("year",Hibernate.STRING);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DwJgjMajorSeason> findByYearJgjMajorSeason(String year,String season) {
		String hql="from DwJgjMajorSeason t where t.year=? and t.season=? order by to_number(t.order_) ASC";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, year).setLong(1, Long.valueOf(season));
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DwJgjMajor> findJgjMajorByYearAndType(String year, String type) {
		String hql="from DwJgjMajor t where t.year=? and t.typename=? order by ";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, year).setString(1, type);
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DwJgjMajorCount> findJgjMajorCountByYear(String year) {
		StringBuilder sql = new StringBuilder();
		sql.append("select T.Object_Name lineName, t.order_, sysdate createTime, :year year ");
		sql.append("from C_COMPANY_ROUTE t where t.type ='2' and t.pid ='01' and T.Removed = 0 ");
		Query query = getSession().createSQLQuery(sql.toString())
			.addScalar("lineName", Hibernate.STRING)
			.addScalar("order_", Hibernate.STRING)
			.addScalar("createTime", Hibernate.TIMESTAMP)
			.addScalar("year", Hibernate.STRING)
			.setResultTransformer(Transformers.aliasToBean(DwJgjMajorCount.class))
			.setParameter("year", year);
		return query.list();
	}

	@Override
	public void saveDwJgjMajorCount(DwJgjMajorCount dwJgjMajorCount) {
		getHibernateTemplate().saveOrUpdate(dwJgjMajorCount);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DwJgjMajorSeason> findJgjMajorSeasonByYearAndSeason(String year,
			Long season) {
		StringBuilder sql = new StringBuilder();
		sql.append("select T.Object_Name lineName, t.order_, sysdate createTime, :year year, :season season ");
		sql.append("from C_COMPANY_ROUTE t where t.type ='2' and t.pid ='01' and T.Removed = 0 ");
		Query query = getSession().createSQLQuery(sql.toString())
			.addScalar("lineName", Hibernate.STRING)
			.addScalar("order_", Hibernate.STRING)
			.addScalar("createTime", Hibernate.TIMESTAMP)
			.addScalar("year", Hibernate.STRING)
			.addScalar("season", Hibernate.LONG)
			.setResultTransformer(Transformers.aliasToBean(DwJgjMajorSeason.class))
			.setParameter("year", year)
			.setParameter("season", season);
		return query.list();
	}

	@Override
	public void saveDwJgjMajorSeason(DwJgjMajorSeason dwJgjMajorSeason) {
		getHibernateTemplate().saveOrUpdate(dwJgjMajorSeason);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DwJgjProjectCount> findJgjProjectCountByYear(String year) {
		StringBuilder sql = new StringBuilder();
		sql.append("Select P.Dispatch_No dispatchNo, P.Professional_Type professionalType, P.Project_Name projectName, ");
		sql.append(" P.Project_Adddept mainExecute, P.Project_Moneysource feeDepartment, P.id projectId, ");
		sql.append(" round(to_number(P.Project_Budget_All),6) approvalBugget, P.Project_Type maintainType, ");
		sql.append(" Round(To_Number(C.Contract_Price),6) contractPrice, C.id contractId, ");
		sql.append(" Round(To_Number(C.NATION_VERIFY_PRICE),6) nationVerifyPrice, ");
		sql.append(" Round(To_Number(C.VERIFY_PRICE),6) verifyPrice, Round(To_Number(C.FINAL_PRICE),6) finalPrice, ");
		sql.append(" :year year, sysdate createTime ");
		sql.append(" From C_Contract C Left Outer Join C_Project P On P.Id = C.Project_Id And P.Removed = 0 ");
		sql.append(" Where C.Removed = 0 and P.Professional_Type is not null and P.Project_Type in('2','3')");
		
		Query query = getSession().createSQLQuery(sql.toString())
			.addScalar("dispatchNo", Hibernate.STRING)
			.addScalar("professionalType", Hibernate.STRING)
			.addScalar("projectName", Hibernate.STRING)
			.addScalar("mainExecute", Hibernate.STRING)
			.addScalar("feeDepartment", Hibernate.STRING)
			.addScalar("approvalBugget", Hibernate.BIG_DECIMAL)
			.addScalar("maintainType", Hibernate.STRING)
			.addScalar("contractPrice", Hibernate.BIG_DECIMAL)
			.addScalar("nationVerifyPrice", Hibernate.BIG_DECIMAL)
			.addScalar("verifyPrice", Hibernate.BIG_DECIMAL)
			.addScalar("finalPrice", Hibernate.BIG_DECIMAL)
			.addScalar("year", Hibernate.STRING)
			.addScalar("createTime", Hibernate.TIMESTAMP)
			.addScalar("contractId", Hibernate.STRING)
			.addScalar("projectId", Hibernate.STRING)
			.setResultTransformer(Transformers.aliasToBean(DwJgjProjectCount.class))
			.setParameter("year", year);
		return query.list();
	}

	@Override
	public void saveProjectCount(DwJgjProjectCount dwJgjProjectCount) {
		getHibernateTemplate().saveOrUpdate(dwJgjProjectCount);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DwJgjProjectProgress> findJgjProjectProgressByYearAndSeason(String year, Long season) {
		StringBuilder sql = new StringBuilder();
		sql.append("Select P.Dispatch_No dispatchNo, P.Professional_Type professionalType, P.Project_Name projectName, ");
		sql.append(" P.Project_Adddept mainExecute, P.Project_Moneysource feeDepartment, P.id projectId, ");
		sql.append(" round(to_number(P.Project_Budget_All),6) approvalBugget, P.Project_Type maintainType, ");
		sql.append(" Round(To_Number(C.Contract_Price),6) contractPrice, C.id contractId, ");
		sql.append(" Round(To_Number(C.NATION_VERIFY_PRICE),6) nationVerifyPrice, ");
		sql.append(" Round(To_Number(C.VERIFY_PRICE),6) verifyPrice, Round(To_Number(C.FINAL_PRICE),6) finalPrice, ");
		sql.append(" Progress.PROGRESS_TYPE projectProgress, :year year, :season season, sysdate createTime ");
		sql.append(" From C_Contract C Left Outer Join C_Project P On P.Id = C.Project_Id And P.Removed = 0 ");
		sql.append(" Left Outer Join (Select Object_Id, Progress_Type From (Select A.*, ");
		sql.append(" Row_Number() Over (Partition By Object_Id Order By Update_Date Desc, Create_Date Desc) Rn ");
		sql.append(" From C_Progress A) Where Rn <= 1 And Object_Type = 2  And Removed = 0) ");
		sql.append(" Progress On Progress.Object_Id = P.Id ");
		sql.append(" Where C.Removed = 0 and P.Professional_Type is not null and P.Project_Type in('2','3')");
		
		Query query = getSession().createSQLQuery(sql.toString())
			.addScalar("dispatchNo", Hibernate.STRING)
			.addScalar("professionalType", Hibernate.STRING)
			.addScalar("projectName", Hibernate.STRING)
			.addScalar("mainExecute", Hibernate.STRING)
			.addScalar("feeDepartment", Hibernate.STRING)
			.addScalar("approvalBugget", Hibernate.BIG_DECIMAL)
			.addScalar("maintainType", Hibernate.STRING)
			.addScalar("contractPrice", Hibernate.BIG_DECIMAL)
			.addScalar("nationVerifyPrice", Hibernate.BIG_DECIMAL)
			.addScalar("verifyPrice", Hibernate.BIG_DECIMAL)
			.addScalar("finalPrice", Hibernate.BIG_DECIMAL)
			.addScalar("projectProgress", Hibernate.STRING)
			.addScalar("year", Hibernate.STRING)
			.addScalar("season", Hibernate.LONG)
			.addScalar("createTime", Hibernate.TIMESTAMP)
			.addScalar("contractId", Hibernate.STRING)
			.addScalar("projectId", Hibernate.STRING)
			.setResultTransformer(Transformers.aliasToBean(DwJgjProjectProgress.class))
			.setParameter("year", year)
			.setParameter("season", season);
		return query.list();
	}

	@Override
	public void saveProjectProgress(DwJgjProjectProgress dwJgjProjectProgress) {
		getHibernateTemplate().saveOrUpdate(dwJgjProjectProgress);
	}

	@Override
	public void deleteAllDwJgjProjectCountByYear(String year) {
		String hql="delete DwJgjProjectCount t where t.year=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, year);
		query.executeUpdate();
	}

	@Override
	public void deleteAllDwJgjMajorCountByYear(String year) {
		String hql="delete DwJgjMajorCount t where t.year=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, year);
		query.executeUpdate();
	}

	@Override
	public void deleteAllDwJgjMajorByYear(String year) {
		String hql="delete DwJgjMajor t where t.year=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, year);
		query.executeUpdate();
	}

	@Override
	public void deleteAllDwJgjProjectByYear(String year) {
		String hql="delete DwJgjProject t where t.year=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, year);
		query.executeUpdate();
	}

	@Override
	public void deleteAllDwJgjMajorSeasonByYear(String year) {
		String hql="delete DwJgjMajorSeason t where t.year=? ";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, year);
		query.executeUpdate();
	}

	@Override
	public void deleteAllDwJgjProjectProgressByYear(String year) {
		String hql="delete DwJgjProjectProgress t where t.year=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, year);
		query.executeUpdate();
	}

	
	
	
	
	
}
