package com.yin.wechat.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
public class RSOrders  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3526002561049226070L;
	
	@Id  
	@GeneratedValue  
	private int id;
	private String payuser;
	private String cpName;//项目名称
	private String paymoney;
	private Date paytime;
	private String tradeno;
	private String operator;
	private String terminal;
	private String userName;
	private String state;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCpName() {
		return cpName;
	}
	public void setCpName(String cpName) {
		this.cpName = cpName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPayuser() {
		return payuser;
	}
	public void setPayuser(String payuser) {
		this.payuser = payuser;
	}
	public String getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(String paymoney) {
		this.paymoney = paymoney;
	}
	public Date getPaytime() {
		return paytime;
	}
	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	public String getTradeno() {
		return tradeno;
	}
	public void setTradeno(String tradeno) {
		this.tradeno = tradeno;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	   public RSOrders() {  
	       super();  
	   }  
	 
	
	public RSOrders(int id,String payuser,String paymoney,Date paytime,String tradeno,String operator,String terminal,String state)
	{
	   super();  
       this.id = id;  
       this.payuser = payuser;  
       this.paymoney = paymoney;  
	   this.tradeno = tradeno; 
	   this.operator = operator;
	   this.terminal = terminal;
	   this.state = state;
	}
	
	
	
	

}
