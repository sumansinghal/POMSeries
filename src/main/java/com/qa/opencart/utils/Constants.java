package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
	
	//creating static because we are not creating object of constant and use by class name
	public static final int DEFAULT_TIME_OUT = 5;
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_VALUE = "account/login";
	
	public static final String ACC_PAGE_TITLE = "My Account";
	
	public static final List<String> EXP_ACCOUNTS_SECTIONS_LIST = new ArrayList<String>(Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter"));
	
	public static final int IMAC_IMAGE_COUNT = 3;

	public static final String REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	

}
