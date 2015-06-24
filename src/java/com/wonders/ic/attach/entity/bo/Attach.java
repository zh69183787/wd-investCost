/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.ic.attach.entity.bo;

import java.util.Date;
import java.util.Set;
import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * Attachʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-1
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_ATTACH")
public class Attach implements Serializable, BusinessObject {

	private Long id; // id

	private String appname; // appname

	private String fileextname; // fileextname

	private String filename; // filename

	private Long filesize; // filesize

	private String filetype; // filetype

	private String groupid; // groupid

	private String groupname; // groupname

	private String keyWord; // keyWord

	private String memo; // memo

	private Long operateTime; // operateTime

	private String operator; // operator

	private String path; // path

	private Long removed; // removed

	private String savefilename; // savefilename

	private String status; // status

	private String uploaddate; // uploaddate

	private String uploader; // uploader

	private String uploaderLoginName; // uploaderLoginName

	private String version; // version

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	@Column(name = "APPNAME", nullable = true, length = 50)
	public String getAppname() {
		return appname;
	}

	public void setFileextname(String fileextname) {
		this.fileextname = fileextname;
	}

	@Column(name = "FILEEXTNAME", nullable = true, length = 10)
	public String getFileextname() {
		return fileextname;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "FILENAME", nullable = true, length = 200)
	public String getFilename() {
		return filename;
	}

	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}

	@Column(name = "FILESIZE", nullable = true, length = 22)
	public Long getFilesize() {
		return filesize;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	@Column(name = "FILETYPE", nullable = true, length = 20)
	public String getFiletype() {
		return filetype;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	@Column(name = "GROUPID", nullable = true, length = 100)
	public String getGroupid() {
		return groupid;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	@Column(name = "GROUPNAME", nullable = true, length = 50)
	public String getGroupname() {
		return groupname;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	@Column(name = "KEY_WORD", nullable = true, length = 200)
	public String getKeyWord() {
		return keyWord;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "MEMO", nullable = true, length = 200)
	public String getMemo() {
		return memo;
	}

	public void setOperateTime(Long operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 22)
	public Long getOperateTime() {
		return operateTime;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "OPERATOR", nullable = true, length = 200)
	public String getOperator() {
		return operator;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "PATH", nullable = true, length = 200)
	public String getPath() {
		return path;
	}

	public void setRemoved(Long removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 22)
	public Long getRemoved() {
		return removed;
	}

	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}

	@Column(name = "SAVEFILENAME", nullable = true, length = 50)
	public String getSavefilename() {
		return savefilename;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "STATUS", nullable = true, length = 20)
	public String getStatus() {
		return status;
	}

	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}

	@Column(name = "UPLOADDATE", nullable = true, length = 19)
	public String getUploaddate() {
		return uploaddate;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	@Column(name = "UPLOADER", nullable = true, length = 30)
	public String getUploader() {
		return uploader;
	}

	public void setUploaderLoginName(String uploaderLoginName) {
		this.uploaderLoginName = uploaderLoginName;
	}

	@Column(name = "UPLOADER_LOGIN_NAME", nullable = true, length = 50)
	public String getUploaderLoginName() {
		return uploaderLoginName;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "VERSION", nullable = true, length = 10)
	public String getVersion() {
		return version;
	}

}
