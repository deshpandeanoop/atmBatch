package com.anoop.atmBatch.util;
/**
 * 
 * @author 532740
 * Holds set of constants that are been used in the application
 */
public class Constants {

// Initializing bean names that are been configured in spring bean configuration file	
public static String DATA_LOAD_BEAN="dataLoad";
public static String FIRST_NAME_GENERATE_BEAN="firstNameGenerate";
public static String LAST_NAME_GENERATE_BEAN="lastNameGenerate";
public static String ACC_NUMBER_GENERATE_BEAN="accNumberGenerate";
public static String DEBIT_CARD_GENERATE_BEAN="debitCardNumberGenerate";
public static String PERMUTATE_THREAD_BEAN ="permutateThread"; 
public static String ACCOUNT_HOLDER_BEAN = "accountHolder";
public static String TASK_UTIL_BEAN = "taskUtil";
public static String INSERT_THREAD_BEAN ="insertThread"; 

// Initializing Application constants
public static String EMPTY_STRING ="";
public static String INSERT_OPERATION="insert";

//Initializing SQL constants
public static String INSERT_ACCOUNT_HOLDERS = "INSERT INTO ACCOUNT_HOLDER_DETAIL (FIRST_NAME,LAST_NAME,ACCOUNT_NUMBER,DEBIT_CARD_NUMBER,PIN) VALUES (?,?,?,?,?)";


}
