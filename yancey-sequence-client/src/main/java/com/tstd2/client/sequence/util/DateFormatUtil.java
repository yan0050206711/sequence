package com.tstd2.client.sequence.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateFormat工具类，用ThreadLocal解决DateFormat多线程问题
 * 
 * @author yancey
 * @date:2016年9月27日 下午1:26:31
 * @version V1.0
 * 
 */
public class DateFormatUtil {

	private String pattern;

	public DateFormatUtil(String pattern) {
		this.pattern = pattern;
	}

	private final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {

		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(pattern);
		}
	};

	public final String format(Date date) {
		return df.get().format(date);
	}

	public Date parse(String source) throws ParseException {
		return df.get().parse(source);
	}
}