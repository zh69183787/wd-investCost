package com.wonders.ic.corporation.service.impl;

import com.wonders.ic.corporation.dao.PsSupplierDao;
import com.wonders.ic.corporation.entity.bo.PsSupplier;
import com.wonders.ic.corporation.service.PsSupplierService;
import com.wonders.webservice.datacenter.utils.DataCenterUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2014/8/13.
 */
public class PsSupplierServiceImpl implements PsSupplierService {

    private PsSupplierDao supplierDao;

    @Override
    public void save(List<PsSupplier> suppliers) {
        supplierDao.save(suppliers);
    }

    @Override
    public void saveSupplierData() throws Exception {
        DataCenterUtils dataCenter = new DataCenterUtils();
        Map<String, String> data = dataCenter.getDataInfo("mscp","mscp2013!","registerSupplier");
        Set<Map.Entry<String,String>> set =  data.entrySet();
        supplierDao.deleteAll();
        for (Map.Entry<String, String> entity : set) {
            saveSupplierXML(entity.getValue());
            dataCenter.confirmGetDataInfo("mscp","mscp2013!","registerSupplier",entity.getKey());
        }

    }

    public void deleteAll() {
        supplierDao.deleteAll();
    }


    private void saveSupplierXML(String xml) throws DocumentException, ParseException {

        Document document = DocumentHelper.parseText(xml);
        List<Node> list = document.selectNodes("//data");
        List<PsSupplier> supplierList = new ArrayList<PsSupplier>();
        for (Node node : list) {
            PsSupplier supplier = new PsSupplier();
            supplier.setId(geTextContent(node.selectSingleNode("id")));
            supplier.setChineseFullName(geTextContent(node.selectSingleNode("chineseFullname")));
            supplier.setEnglishFullName(geTextContent(node.selectSingleNode("englishFullname")));
            supplier.setCompanyType(geTextContent(node.selectSingleNode("companyType")));
            supplier.setCityCode(geTextContent(node.selectSingleNode("cityCode")));
            supplier.setRegCapital(geTextContent(node.selectSingleNode("regCapital")));
            supplier.setRegCapitalUnit(geTextContent(node.selectSingleNode("regCapitalUnit")));
            supplier.setTaxNum(geTextContent(node.selectSingleNode("taxNum")));
            supplier.setTaxStartDate(getDateContent(node.selectSingleNode("taxStartDate")));
            supplier.setTaxEndDate(getDateContent(node.selectSingleNode("taxEndDate")));
            supplier.setBusiStartDate(getDateContent(node.selectSingleNode("busiStartDate")));
            supplier.setBusiEndDate(getDateContent(node.selectSingleNode("busiEndDate")));
            supplier.setOrgNum(geTextContent(node.selectSingleNode("orgNum")));
            supplier.setOrgStartDate(getDateContent(node.selectSingleNode("orgStartDate")));
            supplier.setOrgEndDate(getDateContent(node.selectSingleNode("orgEndDate")));
            supplier.setEstablishmentTime(getDateContent(node.selectSingleNode("establishmentTime")));
            supplier.setEmployeeNum(geTextContent(node.selectSingleNode("employeeNum")));

            supplier.setWebSite(geTextContent(node.selectSingleNode("webSite")));
            supplier.setTradeCode(geTextContent(node.selectSingleNode("tradeCode")));
            supplier.setBusinessScope(geTextContent(node.selectSingleNode("businessScope")));
            supplier.setAccountNum(geTextContent(node.selectSingleNode("accountNum")));
            supplier.setOfficeAddress(geTextContent(node.selectSingleNode("officeAddress")));
            supplier.setRegisterAddress(geTextContent(node.selectSingleNode("registerAddress")));
            supplier.setCdmFaxNumber(geTextContent(node.selectSingleNode("cmdFaxNumber")));
            supplier.setCdmMainTelephoneNumber(geTextContent(node.selectSingleNode("cdmMainTelephoneNumber")));
            supplier.setMotMess(geTextContent(node.selectSingleNode("motMess")));
            supplier.setLawer(geTextContent(node.selectSingleNode("lawer")));
            supplier.setLawerIdType(geTextContent(node.selectSingleNode("lawerIDType")));

            supplier.setLawerIdCard(geTextContent(node.selectSingleNode("lawerIdCard")));
            supplier.setCustomField4(geTextContent(node.selectSingleNode("entrustArtificialPerson")));
            supplier.setBusiNum(geTextContent(node.selectSingleNode("busiNumAttachId")));//**
            supplier.setCountryCode(geTextContent(node.selectSingleNode("countryCode")));
            supplier.setProvinceCode(geTextContent(node.selectSingleNode("provinceCode")));
            supplier.setLinkmanName(geTextContent(node.selectSingleNode("linkmanName")));

            supplier.setLinkmanTelphone(geTextContent(node.selectSingleNode("linkmanTelphone")));
            //BUSI_LINK_MAN_TEL --> linkmanFixedTelphone 供应商固定电话
            supplier.setBusiLinkemanTel(geTextContent(node.selectSingleNode("linkmanFixedTelphone")));
            supplier.setLinkmanEmail(geTextContent(node.selectSingleNode("linkmanEmail")));
            supplier.setBankName(geTextContent(node.selectSingleNode("bankName")));
            supplier.setBankBranchName(geTextContent(node.selectSingleNode("bankBranchName")));
            supplier.setTaxNumAttach(geTextContent(node.selectSingleNode("taxNumAttach")));
            supplier.setBusiNumAttach(geTextContent(node.selectSingleNode("busiNumAttach")));
            supplier.setOrgNumAttach(geTextContent(node.selectSingleNode("orgNumAttach")));

            supplierList.add(supplier);

        }
        save(supplierList);
    }

    public void syncData(){

        supplierDao.batchUpdate();
        supplierDao.batchInsert();
    }

    private String geTextContent(Node node) throws ParseException {

        if (node != null && StringUtils.isNotBlank(node.getText())) {
           return node.getText();
        }
        return "";
    }
    private java.sql.Date getDateContent(Node node) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date date = null;
        if (node != null && StringUtils.isNotBlank(node.getText())) {
            Date d = sf.parse(node.getText());
            date = new java.sql.Date(d.getTime());
        }
        return date;
    }

    public static void main(String[] args) {
        PsSupplierServiceImpl service = new PsSupplierServiceImpl();
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File("e:\\test.xml"));

            service.saveSupplierXML(document.asXML());
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public PsSupplierDao getSupplierDao() {
        return supplierDao;
    }

    public void setSupplierDao(PsSupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }
}
