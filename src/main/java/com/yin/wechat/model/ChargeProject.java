package com.yin.wechat.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 收费项目信息
 * @author yinleilei
 *
 */
@Entity
@Table(name="ChargeProject")
public class ChargeProject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id  
	@GeneratedValue 
	private Integer id;
	private String cpName;//项目名称
	private String paymoney;//支付金额
	private String state;
	private Date createtime;//创建时间;
	private String createUserName;//收费项目创建人
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCpName() {
		return cpName;
	}
	public void setCpName(String cpName) {
		this.cpName = cpName;
	}
	public String getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(String paymoney) {
		this.paymoney = paymoney;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
}
