package com.java.mycontrolbook;

public class UserClass {
	
    public static String sUsername;
    public static String sName;
    
    // Username
    public void setUsername(String username){
    	sUsername = username;

    }
    public static String getUsername(){
    	return sUsername;

    }
    
    // Name
    public void setName(String name){
    	sName = name;
    }
    public static String getName(){
    	return sName;
    }
    
}
