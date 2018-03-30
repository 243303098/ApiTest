package com.fkapi.utils;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class Assertion {
	public static boolean flag = true;
    
    public static List<Error> errors = new ArrayList<Error>();
     
    public static void verifyEquals(Object actual, Object expected){
        try{
            Assert.assertEquals(actual, expected);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }
     
    public static void verifyEquals(Object actual, Object expected, String message){
        try{
            Assert.assertEquals(actual, expected, message);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }
    
    public static void verifyTure(Boolean boolean1){
    	try {
    		Assert.assertTrue(boolean1);
		} catch (Error e) {
			errors.add(e);
			flag = false;
		}
    }
}