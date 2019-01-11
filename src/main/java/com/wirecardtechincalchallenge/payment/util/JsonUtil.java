package com.wirecardtechincalchallenge.payment.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
	
	private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	
	public static String toJson(Object object) {
		return gson.toJson(object);
	}

}
