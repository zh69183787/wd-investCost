package com.wonders.ic.corporation.action;

import com.opensymphony.xwork2.Action;
import com.wonders.ic.corporation.service.PsSupplierService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.text.ParseException;

/**
 * Created by Administrator on 2014/8/13.
 */
public class PsSupplierAction {

    private PsSupplierService supplierService;

    public String syncData() throws Exception {


        supplierService.saveSupplierData();
        supplierService.syncData();
        return Action.NONE;
    }

    public PsSupplierService getSupplierService() {
        return supplierService;
    }

    public void setSupplierService(PsSupplierService supplierService) {
        this.supplierService = supplierService;
    }
}
