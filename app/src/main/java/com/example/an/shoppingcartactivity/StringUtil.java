package com.example.an.shoppingcartactivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	@SuppressWarnings("unused")
	private static final String TAG = StringUtil.class.getSimpleName();

	public static boolean isBlank(String value) {
		return value == null || value.trim().length() <= 0;
	}

	public static boolean parseBoolean(String str) {
		if (str != null) {
			return Boolean.parseBoolean(str.trim());
		}
		return false;
	}

	public static final int GEN_UPPERCASE = 0;
	public static final int GEN_LOWERCASE = 1;
	public static final int GEN_CHINESE = 2;
	public static final int GEN_NUMBER = 3;

	/**
	 * 空值判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if (str == null) {
			return true;
		}
		return str.trim().length() == 0;
	}

	/**
	 * 对象转字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String valueOf(Object obj) {
		if (obj == null) {
			return "";
		}
		if ((obj instanceof String))
			return (String) obj;
		if ((obj instanceof Object[]))
			return join((Object[]) obj, ",");
		if ((obj instanceof String[]))
			return join((String[]) obj, ",");
		if ((obj instanceof double[]))
			return join((double[]) obj, ",");
		if ((obj instanceof long[]))
			return join((long[]) obj, ",");
		if ((obj instanceof float[]))
			return join((float[]) obj, ",");
		if ((obj instanceof int[]))
			return join((int[]) obj, ",");
		if ((obj instanceof short[]))
			return join((short[]) obj, ",");
		if ((obj instanceof byte[])) {
			return join((byte[]) obj, ",");
		}
		return String.valueOf(obj);
	}

	/**
	 * 字符串转整形
	 * 
	 * @param str
	 * @return
	 */
	public static Integer getInt(String str) {
		try {
			return Integer.valueOf(Integer.parseInt(str.trim()));
		} catch (Exception ex) {
		}
		return null;
	}

	/**
	 * 时间戳转字符串
	 * 
	 * @param str
	 * @param format
	 * @return
	 */
	public static String getTime(long timeMillis, String format) {
		try {
			return new SimpleDateFormat(format, Locale.getDefault())
					.format(timeMillis - TimeZone.getDefault().getRawOffset());
		} catch (Exception ex) {
		}
		return null;
	}

	/**
	 * 字符串转整形数组
	 * 
	 * @param str
	 * @param spliter
	 * @return
	 */
	public static Integer[] getIntArray(String str, String spliter) {
		if (isNull(str)) {
			return new Integer[0];
		}
		String[] strs = str.split(spliter);
		List<Integer> tmp = new ArrayList<Integer>();
		for (int i = 0; i < strs.length; i++) {
			Integer val = getInt(strs[i]);
			if (val != null)
				tmp.add(val);
		}
		return (Integer[]) tmp.toArray(new Integer[tmp.size()]);
	}

	/**
	 * 字符串转long型
	 * 
	 * @param str
	 * @return
	 */
	public static Long getLong(String str) {
		try {
			return Long.valueOf(Long.parseLong(str.trim()));
		} catch (Exception ex) {
		}
		return null;
	}

	public static Long[] getLongArray(String str, String spliter) {
		if (isNull(str)) {
			return new Long[0];
		}
		String[] strs = str.split(spliter);
		List<Long> tmp = new ArrayList<Long>();
		for (int i = 0; i < strs.length; i++) {
			Long val = getLong(strs[i]);
			if (val != null)
				tmp.add(val);
		}
		return (Long[]) tmp.toArray(new Long[tmp.size()]);
	}

	/**
	 * 字符串转布尔类型
	 * 
	 * @param str
	 * @return
	 */
	public static Boolean getBoolean(String str) {
		try {
			return Boolean.valueOf(Boolean.parseBoolean(str.trim()));
		} catch (Exception ex) {
		}
		return null;
	}

	public static Boolean[] getBooleanArray(String str, String spliter) {
		if (isNull(str)) {
			return new Boolean[0];
		}
		String[] strs = str.split(spliter);
		List<Boolean> tmp = new ArrayList<Boolean>();
		for (int i = 0; i < strs.length; i++) {
			Boolean val = getBoolean(strs[i]);
			if (val != null)
				tmp.add(val);
		}
		return (Boolean[]) tmp.toArray(new Boolean[tmp.size()]);
	}

	public static Float getFloat(String str) {
		try {
			return Float.valueOf(Float.parseFloat(str.trim()));
		} catch (Exception ex) {
		}
		return null;
	}

	public static Float[] getFloatArray(String str, String spliter) {
		if (isNull(str)) {
			return new Float[0];
		}
		String[] strs = str.split(spliter);
		List<Float> tmp = new ArrayList<Float>();
		for (int i = 0; i < strs.length; i++) {
			Float val = getFloat(strs[i]);
			if (val != null)
				tmp.add(val);
		}
		return (Float[]) tmp.toArray(new Float[tmp.size()]);
	}

	public static Double getDouble(String str) {
		try {
			return Double.valueOf(Double.parseDouble(str.trim()));
		} catch (Exception ex) {
		}
		return null;
	}

	public static Double[] getDouble(String str, String spliter) {
		if (isNull(str)) {
			return new Double[0];
		}
		String[] strs = str.split(spliter);
		List<Double> tmp = new ArrayList<Double>();
		for (int i = 0; i < strs.length; i++) {
			Double val = getDouble(strs[i]);
			if (val != null)
				tmp.add(val);
		}
		return (Double[]) tmp.toArray(new Double[tmp.size()]);
	}

	/**
	 * 字符串转日期
	 * 
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date getDate(String str, String format) {
		try {
			return new SimpleDateFormat(format, Locale.getDefault()).parse(str
					.trim());
		} catch (Exception ex) {
		}
		return null;
	}

	/**
	 * 日期转字符串
	 * 
	 * @param str
	 * @param format
	 * @return
	 */
	public static String getDate(Date str, String format) {
		try {
			return new SimpleDateFormat(format, Locale.getDefault())
					.format(str);
		} catch (Exception ex) {
		}
		return null;
	}

	/**
	 * 日期字符串转成指定格式的日期字符串
	 * 
	 * @param str
	 * @param format
	 * @return
	 */
	public static String getDateForFormat(String str, String format) {
		try {
			Date date = new SimpleDateFormat(format, Locale.getDefault())
					.parse(str.trim());
			return new SimpleDateFormat(format, Locale.getDefault())
					.format(date);
		} catch (Exception ex) {
		}
		return null;
	}

	/**
	 * 获取日期字符串中的时分秒
	 * 
	 * @param str
	 * @param format
	 * @return
	 */
	public static String getTime(String str) {
		try {
			Date date = getDate(str, "yyyy-MM-dd HH:mm:ss");
			DateFormat d3 = DateFormat.getTimeInstance();
			return d3.format(date);
		} catch (Exception ex) {
		}
		return null;
	}

	/**
	 * 日期（天）相加减
	 * 
	 * @param current
	 * @param count
	 * @return
	 */
	public static String dayCalculate(String current, int count) {
		// 日期字符串转换为date类型
		Date currentDate = getDate(current, "yyyy-MM-dd");

		// 获取Calendar对象
		Calendar mCalendar = Calendar.getInstance();
		// 设置日期为当前已经选择的时间
		mCalendar.setTime(currentDate);
		// 进行时间相加减操作
		mCalendar.add(Calendar.DAY_OF_MONTH, count);
		String date = StringUtil.getDate(mCalendar.getTime(), "yyyy-MM-dd");
		return date;
	}

	/**
	 * 日期（月份）相加减
	 * 
	 * @param current
	 * @param count
	 * @return
	 */
	public static String monthCalculate(String current, int count) {
		// 日期字符串转换为date类型
		Date currentDate = getDate(current, "yyyy-MM");

		// 获取Calendar对象
		Calendar mCalendar = Calendar.getInstance();
		// 设置日期为当前已经选择的时间
		mCalendar.setTime(currentDate);
		// 进行时间相加减操作
		mCalendar.add(Calendar.MONTH, count);
		String date = StringUtil.getDate(mCalendar.getTime(), "yyyy-MM");
		return date;
	}

	/**
	 * 日期大小比较 date1大于date2返回1； date1小于date2返回-1 date1等于date2返回0
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static int compareDate(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取年月份、星期
	 * 
	 * @return
	 */
	public static String StringData() {
		String mYear, mMonth, mDay, mWay;

		final Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
		mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
		mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
		mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		if ("1".equals(mWay)) {
			mWay = "天";
		} else if ("2".equals(mWay)) {
			mWay = "一";
		} else if ("3".equals(mWay)) {
			mWay = "二";
		} else if ("4".equals(mWay)) {
			mWay = "三";
		} else if ("5".equals(mWay)) {
			mWay = "四";
		} else if ("6".equals(mWay)) {
			mWay = "五";
		} else if ("7".equals(mWay)) {
			mWay = "六";
		}
		return mYear + "年" + mMonth + "月" + mDay + "日" + "/星期" + mWay;
	}

	/**
	 * 获取星期
	 * 
	 * @return
	 */
	public static String getDayOfWeek() {
		String mWay;
		final Calendar c = Calendar.getInstance();
		mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));

		if ("1".equals(mWay)) {
			mWay = "天";
		} else if ("2".equals(mWay)) {
			mWay = "一";
		} else if ("3".equals(mWay)) {
			mWay = "二";
		} else if ("4".equals(mWay)) {
			mWay = "三";
		} else if ("5".equals(mWay)) {
			mWay = "四";
		} else if ("6".equals(mWay)) {
			mWay = "五";
		} else if ("7".equals(mWay)) {
			mWay = "六";
		}
		return mWay;
	}

	@SuppressLint("DefaultLocale")
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ((c >= 0) && (c <= 'ÿ')) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					Log.e("StringUtil", ex.getMessage());
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0) {
						k += 256;
					}
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}

		return sb.toString();
	}

	public static String changeEncoding(String oldString, String oldCharset,
                                        String newCharset) {
		if (isNull(oldString)) {
			return oldString;
		}

		if (isNull(newCharset)) {
			return oldString;
		}
		if (oldCharset == null) {
			oldCharset = "";
		}
		if (newCharset.trim().equalsIgnoreCase(oldCharset.trim())) {
			return oldString;
		}
		try {
			if (isNull(oldCharset)) {
				return new String(oldString.getBytes(), newCharset);
			}
			return new String(oldString.getBytes(oldCharset), newCharset);
		} catch (UnsupportedEncodingException uee) {
			Log.e("由于系统不支持编码[" + oldCharset + "]或者[" + newCharset
					+ "]，因此未能进行转换，直接返回原字符串", uee.getMessage());
		}
		return oldString;
	}

	public static boolean matchPattern(String str, String[] patterns) {
		if ((str == null) || (patterns == null))
			return false;
		for (String p : patterns) {
			if (matchPattern(str, p))
				return true;
		}
		return false;
	}

	public static boolean matchPattern(String str, String pattern) {
		return pattern == null ? false : Pattern.matches(pattern, str);
	}

	public static String toUnicode(String strText) throws Exception {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < strText.length(); i++) {
			char c = strText.charAt(i);
			int intAsc = c;
			if (intAsc > 128) {
				sb.append("\\u" + Integer.toHexString(intAsc));
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static String random(int length, int[] types) {
		return generateRandomString(types, length);
	}

	public static String generateRandomString(int[] stringTypes, int length) {
		int[] startChars = new int[100];
		int[] endChars = new int[100];
		int actLength = 0;

		for (int i = 0; i < stringTypes.length; i++) {
			if (actLength > startChars.length)
				break;
			if (stringTypes[i] == 2) {
				startChars[actLength] = 19968;
				endChars[actLength] = 40880;

				actLength++;
			} else if (stringTypes[i] == 1) {
				startChars[actLength] = 97;
				endChars[actLength] = 122;
				actLength++;
			} else if (stringTypes[i] == 0) {
				startChars[actLength] = 65;
				endChars[actLength] = 90;
				actLength++;
			} else if (stringTypes[i] == 3) {
				startChars[actLength] = 48;
				endChars[actLength] = 57;
				actLength++;
			}
		}
		Random random = new Random();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < length; i++) {
			int idx = Math.abs(random.nextInt()) % actLength;
			int startChar = startChars[idx];
			int endChar = endChars[idx];
			char randChar = (char) (Math.abs(random.nextInt())
					% (endChar - startChar) + startChar);

			sb.append(randChar);
		}

		return sb.toString();
	}

	public static boolean isEquals(String str1, String str2, boolean trim) {
		str1 = trim(str1);
		str2 = trim(str2);

		if (str1 == null)
			return (str2 == null) || ((str2.length() == 0) && (trim));
		if (str1.length() == 0) {
			if (trim) {
				return isNull(str2);
			}
			return (str2 != null) && (str2.length() == 0);
		}
		if (trim) {
			return str1.equals(str2);
		}
		return str1.equals(str2);
	}

	public static String trim(String str) {
		if (str == null) {
			return str;
		}
		return str.trim();
	}

	/**
	 * double转字符串 0.00
	 * 
	 * @param str
	 * @return 0.00
	 */
	public static String getZeroDouble(double str) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(str);
	}

	/**
	 * double为0.00 显示 --
	 * 
	 * @param str
	 * @return 0.00
	 */
	public static String getZeroToStr(double str, String sdefault) {
		if (str == 0) {
			return sdefault;
		} else {
			DecimalFormat df = new DecimalFormat("0.00");
			return df.format(str);
		}
	}

	public static String toTimeStr(long time, boolean showZero) {
		long seconds = time / 1000L;
		int minutes = 0;
		int hours = 0;
		int days = 0;

		days = (int) seconds / 86400;

		seconds %= 86400L;

		hours = (int) seconds / 3600;
		seconds %= 3600L;
		minutes = (int) seconds / 60;
		seconds %= 60L;
		String str = "";
		if (days > 0)
			str = days
					+ "天"
					+ ((hours == 0) && (!showZero) ? "" : new StringBuilder(
							String.valueOf(hours)).append("小时").toString())
					+ ((minutes == 0) && (!showZero) ? "" : new StringBuilder(
							String.valueOf(minutes)).append("分").toString())
					+ ((seconds == 0L) && (!showZero) ? "" : new StringBuilder(
							String.valueOf(seconds)).append("秒").toString());
		else if (hours > 0)
			str = hours
					+ "小时"
					+ ((minutes == 0) && (!showZero) ? "" : new StringBuilder(
							String.valueOf(minutes)).append("分").toString())
					+ ((seconds == 0L) && (!showZero) ? "" : new StringBuilder(
							String.valueOf(seconds)).append("秒").toString());
		else if (minutes > 0)
			str = minutes
					+ "分"
					+ ((seconds == 0L) && (!showZero) ? "" : new StringBuilder(
							String.valueOf(seconds)).append("秒").toString());
		else {
			str = seconds + "秒";
		}
		return str;
	}

	// 09:08格式
	public static String toTimeStrSF(long time) {
		long times = time / 1000L;

		int minutes = (int) times / 60;
		int seconds = (int) times - 60 * minutes;

		String str = "";
		if (minutes > 59) {
			return str;
		}

		str = minutes < 10 ? ("0" + minutes + ":") : ("" + minutes + ":");
		str += seconds < 10 ? ("0" + seconds) : ("" + seconds);

		// if(minutes<10){
		// str = "0"+minutes+":";
		// }else{
		// str = ""+minutes+":";
		// }
		// if(seconds<10){
		// str = "0"+seconds;
		// }else{
		// str = ""+seconds;
		// }
		return str;
	}

	@SuppressLint("DefaultLocale")
	public static String generateGUID() {
		UUID uid = UUID.randomUUID();

		return uid.toString().replaceAll("-", "").toUpperCase();
	}

	public static String join(byte[] arr, String spliter) {
		StringBuilder sb = new StringBuilder();
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				if (i > 0)
					sb.append(spliter);
				sb.append(arr[i]);
			}
		}
		return sb.toString();
	}

	public static String join(short[] arr, String spliter) {
		StringBuilder sb = new StringBuilder();
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				if (i > 0)
					sb.append(spliter);
				sb.append(arr[i]);
			}
		}
		return sb.toString();
	}

	public static String join(double[] arr, String spliter) {
		StringBuilder sb = new StringBuilder();
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				if (i > 0)
					sb.append(spliter);
				sb.append(arr[i]);
			}
		}
		return sb.toString();
	}

	public static String join(float[] arr, String spliter) {
		StringBuilder sb = new StringBuilder();
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				if (i > 0)
					sb.append(spliter);
				sb.append(arr[i]);
			}
		}
		return sb.toString();
	}

	public static String join(int[] arr, String spliter) {
		StringBuilder sb = new StringBuilder();
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				if (i > 0)
					sb.append(spliter);
				sb.append(arr[i]);
			}
		}
		return sb.toString();
	}

	public static String join(long[] arr, String spliter) {
		StringBuilder sb = new StringBuilder();
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				if (i > 0)
					sb.append(spliter);
				sb.append(arr[i]);
			}
		}
		return sb.toString();
	}

	public static String join(String[] arr, String spliter) {
		StringBuilder sb = new StringBuilder();
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				if (i > 0)
					sb.append(spliter);
				sb.append(arr[i]);
			}
		}
		return sb.toString();
	}

	public static String join(Object[] arr, String spliter) {
		StringBuilder sb = new StringBuilder();
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				if (i > 0)
					sb.append(spliter);
				sb.append(valueOf(arr[i]));
			}
		}
		return sb.toString();
	}

	public static int length(String str) {
		if (str == null)
			return 0;
		int length = 0;
		char[] chars = str.toCharArray();

		for (char c : chars) {
			if (c >= '')
				length += 2;
			else {
				length++;
			}

		}

		return length;
	}

	public static String escape(String src, String pre) {
		if (src == null) {
			return null;
		}

		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (int i = 0; i < src.length(); i++) {
			char j = src.charAt(i);
			if ((Character.isDigit(j)) || (Character.isLowerCase(j))
					|| (Character.isUpperCase(j))) {
				tmp.append(j);
			} else if (j < 'Ā') {
				tmp.append(pre);
				if (j < '\020')
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append(pre + "u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String escape(String src) {
		return escape(src, "%");
	}

	public static String unescape(String src) {
		return unescape(src, "%");
	}

	public static String unescape(String src, String pre) {
		if (src == null)
			// return src;
			return null;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());

		int lastPos = 0;
		int pos = 0;

		while (lastPos < src.length()) {
			pos = src.indexOf(pre, lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					char ch = (char) Integer.parseInt(
							src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					char ch = (char) Integer.parseInt(
							src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else if (pos == -1) {
				tmp.append(src.substring(lastPos));
				lastPos = src.length();
			} else {
				tmp.append(src.substring(lastPos, pos));
				lastPos = pos;
			}
		}

		return tmp.toString();
	}

	/**
	 * 将字符串数组转化为用逗号连接的字符串
	 * 
	 * @param values
	 * @return
	 */
	public static String arrayToString(String[] values) {
		String result = "";
		if (values != null) {
			if (values.length > 0) {
				for (String value : values) {
					result += value + ",";
				}
				result = result.substring(0, result.length() - 1);
			}
		}
		return result;
	}

	/**
	 * 将字符串List转换为用逗号连接的字符串
	 * 
	 * @param list
	 * @return
	 */
	public static String listToString(List<String> list) {
		String result = "";
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				result += list.get(i) + ",";
			}
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	public static String numberAdd(int num) {
		String result = "";
		if (num >= 0 && num <= 9) {
			result = "0" + num;
		} else {
			result = "" + num;
		}
		return result;
	}

	/**
	 * 获取年月日格式日期
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		long time = System.currentTimeMillis();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd",
				Locale.getDefault());
		return sf.format(new Date(time));
	}

	/**
	 * 获取年月日格式日期,根据根式
	 * 
	 * @return
	 */
	public static String getCurrentDate(String format) {
		long time = System.currentTimeMillis();
		SimpleDateFormat sf = new SimpleDateFormat(format, Locale.getDefault());
		return sf.format(new Date(time));
	}

	public static String getCurrentTime() {
		long time = System.currentTimeMillis();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss",
				Locale.getDefault());
		return sf.format(new Date(time));
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {

		return null == str || "".equals(str) || str.length() == 0 ? true
				: false;
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		return null == obj || "" == obj ? true : false;
	}

	// 身份证号校验
	public static boolean IsIDcard(String str) {
		String regex = "(^\\d{18}$)|(^\\d{15}$)";
		return match(regex, str);
	}

	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	// 固定电话校验
	public static boolean isGuDingPhone(String str) {
		String regex = "^0\\d{2,3}(\\-)?\\d{7,8}$";
		return str.matches(regex);
	}

	// 手机号码校验
	public static boolean isCellPhone(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern
				.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(18[0-9]))\\d{8}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取手机唯一标识
	 * 
	 * @return
	 */
	public static String getPhoneUnique(Context context) {
		@SuppressWarnings("static-access")
        TelephonyManager TelephonyMgr = (TelephonyManager) context
				.getSystemService(context.TELEPHONY_SERVICE);
		String szImei = TelephonyMgr.getDeviceId();
		return szImei;
	}

	public static void main(String[] args) throws Exception {
		String[] strs = { "a", "b" };
		System.out.println(valueOf(strs));
	}

	/**
	 * 获取字符第几次出现的位置
	 * 
	 * @param string
	 * @param divider
	 * @param poi
	 * @return
	 */
	public static int getCharacterPosition(String string, String divider,
                                           int poi) {
		Matcher slashMatcher = Pattern.compile(divider).matcher(string);
		int mIdx = 0;
		while (slashMatcher.find()) {
			mIdx++;
			if (mIdx == poi) {
				break;
			}
		}
		return slashMatcher.start();
	}

	/**
	 * 传入Url地址及需要匹配的参数名
	 * 
	 * @param url
	 *            eg：http://www.autocarinn.com/Wap/index.aspx?province=guangdong
	 *            &mobile=13812345678
	 * @param paramName
	 *            eg：province
	 * @return 参数名对应的参数值 eg：guangdong
	 */
	public static String findParamValue(String url, String paramName) {
		Pattern pattern = Pattern.compile("(^|&|\\?)" + paramName
				+ "=([^&]*)(&|$)");
		Matcher matcher = pattern.matcher(url);
		if (matcher.find()) {
			/**
			 * matcher.group(0)返回?province=guangdong& matcher.group(1)返回?
			 * matcher.group(2)返回guangdong
			 */
			return matcher.group(2);
		}
		return null;
	}

	/**
	 * 字符串转整形
	 * 
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static int getInt(String str, int defaultValue) {
		try {
			return Integer.valueOf(Integer.parseInt(str.trim()));
		} catch (Exception ex) {
			return defaultValue;
		}
	}

	public static double getDouble(String str, double defaultValue) {
		try {
			return Double.parseDouble(str.trim());
		} catch (Exception ex) {
			return defaultValue;
		}
	}

}
