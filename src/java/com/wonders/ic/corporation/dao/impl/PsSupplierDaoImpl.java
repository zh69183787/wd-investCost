package com.wonders.ic.corporation.dao.impl;

import com.wonders.ic.corporation.dao.PsSupplierDao;
import com.wonders.ic.corporation.entity.bo.PsSupplier;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2014/8/13.
 */
public class PsSupplierDaoImpl extends
        AbstractHibernateDaoImpl<PsSupplier> implements PsSupplierDao {
    @Override
    public void save(List<PsSupplier> suppliers) {
        for (int i = 0; i < suppliers.size(); i++) {
            getSession().save(suppliers.get(i));
            if (i % 50 == 0)

            {
                getSession().flush();
                getSession().clear();
            }
        }
    }

    @Override
    public void deleteAll() {
        getSession().createSQLQuery("delete from ps_supplier").executeUpdate();
    }

    public void batchInsert(){

        String insertSql = "insert into ps_corporation c" +
                "  (" +
                "   comp_id," +
                "   company_name_chn," +
                "   industry," +
                "   org_code," +
                "   business_license_code," +
                "   staff_population," +
                "   deposit_bank," +
                "   deposit_account," +
                "   website," +
                "   register_address," +
                "   office_address," +
                "   phone," +
                "   fax," +
                "   profile," +
                "   company_name_en," +
                "   national_code," +
                "   registered_fund," +
                "   registered_fund_unit," +
                "   tax_code," +
                "   company_type," +
                "   legal_name," +
                "   supplier_id," +
                "   city_code," +
                "   tax_start_date," +
                "   tax_end_date," +
                "   busi_start_date," +
                "   busi_end_date," +
                "   org_start_date," +
                "   org_end_date," +
                "   establishment_time," +
                "   mot_mess," +
                "   lawer_id_type," +
                "   lawer_id_card," +
                "   custom_field_4," +
                "   province_code," +
                "   linkman_name," +
                "   linkman_telphone," +
                "   busi_linkman_tel," +
                "   linkman_email," +
                "   bank_branch_name," +
                "   tax_num_attach," +
                "   busi_num_attach," +
                "   org_num_attach,removed,operate_time)" +
                "" +
                "  select SEQ_CORPORATION.NEXTVAL,s.chinese_full_name," +
                "          s.trade_Code," +
                "          s.org_num," +
                "          s.busi_num," +
                "          s.employee_num," +
                "          s.bank_name," +
                "          s.account_num," +
                "          s.web_site," +
                "          s.register_address," +
                "          s.office_address," +
                "          s.cdm_main_telephone_number," +
                "          s.cdm_fax_number," +
                "          s.business_scope," +
                "          s.english_full_name," +
                "          s.country_code," +
                "          s.reg_capital," +
                "          s.reg_capital_unit," +
                "          s.tax_num," +
                "          s.company_type," +
                "          s.lawer," +
                "          s.id," +
                "          s.city_code," +
                "          s.tax_start_date," +
                "          s.tax_end_date," +
                "          s.busi_start_date," +
                "          s.busi_end_date," +
                "          s.org_start_date," +
                "          s.org_end_date," +
                "          s.establishment_time," +
                "          s.mot_mess," +
                "          s.lawer_id_type," +
                "          s.lawer_id_card," +
                "          s.custom_field_4," +
                "          s.province_code," +
                "          s.linkman_name," +
                "          s.linkman_telphone," +
                "          s.busi_linkeman_tel," +
                "          s.linkman_email," +
                "          s.bank_branch_name," +
                "          s.tax_num_attach," +
                "          s.busi_num_attach," +
                "          s.org_num_attach,'0',sysdate " +
                "     from ps_supplier s " +
                "    where not exists (select 1" +
                "             from ps_corporation c " +
                "            where (s.org_num = c.org_code) " +
                "               or s.id = c.supplier_id)";
        getSession().createSQLQuery(insertSql).executeUpdate();
//        System.out.println(insertSql);
    }

    @Override
    public void batchUpdate() {
         String updateSql = "update ps_corporation c" +
                "   set (c.company_name_chn," +
                "        c.industry," +
                "        c.org_code," +
                "        c.business_license_code," +
                "        c.staff_population," +
                "        c.deposit_bank," +
                "        c.deposit_account," +
                "        c.website," +
                "        c.register_address," +
                "        c.office_address," +
                "        c.phone," +
                "        c.fax," +
                "        c.profile," +
                "        c.company_name_en," +
                "        c.national_code," +
                "        c.registered_fund," +
                "        c.registered_fund_unit," +
                "        c.tax_code," +
                "        c.company_type," +
                "        c.legal_name," +
                "        c.supplier_id," +
                "        c.city_code," +
                "        c.tax_start_date," +
                "        c.tax_end_date," +
                "        c.busi_start_date," +
                "        c.busi_end_date," +
                "        c.org_start_date," +
                "        c.org_end_date," +
                "        c.establishment_time," +
                "        c.mot_mess," +
                "        c.lawer_id_type," +
                "        c.lawer_id_card," +
                "        c.custom_field_4," +
                "        c.province_code," +
                "        c.linkman_name," +
                "        c.linkman_telphone," +
                "        c.busi_linkman_tel," +
                "        c.linkman_email," +
                "        c.bank_branch_name," +
                "        c.tax_num_attach," +
                "        c.busi_num_attach," +
                "        c.org_num_attach,c.operate_time) =" +
                "       (select s.chinese_full_name," +
                "               s.trade_Code," +
                "               s.org_num," +
                "               s.busi_num," +
                "               s.employee_num," +
                "               s.bank_name," +
                "               s.account_num," +
                "               s.web_site," +
                "               s.register_address," +
                "               s.office_address," +
                "               s.cdm_main_telephone_number," +
                "               s.cdm_fax_number," +
                "               s.business_scope," +
                "               s.english_full_name," +
                "               s.country_code," +
                "               s.reg_capital," +
                "               s.reg_capital_unit," +
                "               s.tax_num," +
                "               s.company_type," +
                "               s.lawer," +
                "               s.id," +
                "               s.city_code," +
                "               s.tax_start_date," +
                "               s.tax_end_date," +
                "               s.busi_start_date," +
                "               s.busi_end_date," +
                "               s.org_start_date," +
                "               s.org_end_date," +
                "               s.establishment_time," +
                "               s.mot_mess," +
                "               s.lawer_id_type," +
                "               s.lawer_id_card," +
                "               s.custom_field_4," +
                "               s.province_code," +
                "               s.linkman_name," +
                "               s.linkman_telphone," +
                "               s.busi_linkeman_tel," +
                "               s.linkman_email," +
                "               s.bank_branch_name," +
                "               s.tax_num_attach," +
                "               s.busi_num_attach," +
                "               s.org_num_attach,sysdate" +
                "          from ps_supplier s" +
                "         where (s.org_num = c.org_code)" +
                "            or s.id = c.supplier_id) " +
                " where exists (select 1" +
                "          from ps_supplier s" +
                "         where (s.org_num = c.org_code)" +
                "            or s.id = c.supplier_id)";


        getSession().createSQLQuery(updateSql).executeUpdate();
    }

    public static void main(String[] args) {

//        System.out.println(insertSql);
    }
}
