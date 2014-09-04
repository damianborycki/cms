package com.portal.util;

public class ClassPassword {
	private String login;
	private String oldPassword;
	private String newPassword;	

	public void setLogin(String login){
		this.login = login;
	}
	
	public void setOldPassword(String oldPassword){
		this.oldPassword = oldPassword;
	}
	
	public void setNewPassword(String newPassword){
		this.newPassword = newPassword;
	}
	
	public String getLogin(){
		return this.login;
	}
	
	public String getOldPassword(){
		return this.oldPassword;		
	}
	
	public String getNewPassword(){
		return this.newPassword;
	}
}
