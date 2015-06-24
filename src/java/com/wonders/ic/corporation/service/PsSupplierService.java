package com.wonders.ic.corporation.service;

import com.wonders.ic.corporation.entity.bo.PsSupplier;
import org.dom4j.DocumentException;
import org.xml.sax.SAXException;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2014/8/13.
 */
public interface PsSupplierService {

    void save(List<PsSupplier> suppliers);

    void saveSupplierData() throws Exception;

    void syncData();
}
