package com.glut.learningplatform.util.common;

import java.util.Random;

public class RandomUtil {
	public RandomUtil() {

	}

//获取4位数字+字母的随机验证码
public String getRandom() {
	String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	StringBuilder sb=new StringBuilder(4);
	for(int i=0;i<4;i++)
	{
		char ch=str.charAt(new Random().nextInt(str.length()));
		sb.append(ch);
	}

	return sb+"";
}

	/*public static String getRandom() {
		String num = "";
		for(int i = 0;i<6;i++) {
			num = num + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
		}
		return num;
	}*/

	public static String getRandom15() {
		String num = "";
		for(int i = 0;i<15;i++) {
			num = num + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
		}
		return num;
	}

}
