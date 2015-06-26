package com.example.util;

import android.content.Context;

public class Resource {
	
	public static float getDimens(Context context,int id){
		return context.getResources().getDimension(id);
	}

}
