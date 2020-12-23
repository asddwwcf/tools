package com.mine.captcha.generator;

import org.springframework.util.StringUtils;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机字符验证码生成器<br>
 * 可以通过传入的基础集合和长度随机生成验证码字符
 * 
 * @author looly
 * @since 4.1.2
 */
public class RandomGenerator extends AbstractGenerator {
	private static final long serialVersionUID = -7802758587765561876L;

	/**
	 * 构造，使用字母+数字做为基础
	 * 
	 * @param count 生成验证码长度
	 */
	public RandomGenerator(int count) {
		super(count);
	}

	/**
	 * 构造
	 * 
	 * @param baseStr 基础字符集合，用于随机获取字符串的字符集合
	 * @param length 生成验证码长度
	 */
	public RandomGenerator(String baseStr, int length) {
		super(baseStr, length);
	}

	@Override
	public String generate() {
		return randomString(this.baseStr, this.length);
	}
	
	@Override
	public boolean verify(String code, String userInputCode) {
		if (!StringUtils.isEmpty(userInputCode)) {
			return userInputCode.equalsIgnoreCase(code);
		}
		return false;
	}

	/**
	 * 获得一个随机的字符串
	 *
	 * @param baseString 随机字符选取的样本
	 * @param length     字符串的长度
	 * @return 随机字符串
	 */
	public static String randomString(String baseString, int length) {
		if (null == baseString || baseString.length() == 0) {
			return "";
		}
		final StringBuilder sb = new StringBuilder(length);
		if (length < 1) {
			length = 1;
		}
		int baseLength = baseString.length();
		for (int i = 0; i < length; i++) {
			int number = ThreadLocalRandom.current().nextInt(baseLength);
			sb.append(baseString.charAt(number));
		}
		return sb.toString();
	}

}
