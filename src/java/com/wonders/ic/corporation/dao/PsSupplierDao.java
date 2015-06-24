package com.wonders.ic.corporation.dao;

import com.wonders.ic.corporation.entity.bo.PsSupplier;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

import java.util.List;

/**
 * Created by Administrator on 2014/8/13.
 */
public interface PsSupplierDao extends AbstractHibernateDao<PsSupplier> {
    void save(List<PsSupplier> suppliers);

    void deleteAll();

    void batchInsert();

    void batchUpdate();
}
