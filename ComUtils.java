package test1.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class ComUtils {

	public static void main(String[] args) {
		String str1 = "11,29,33,4,5";
		String str2 = "3";
		Map<String, String> map = getCompareStrPart(str1, str2);
		System.out.println(map.get("left").toString());
		System.out.println(map.get("same").toString());
		System.out.println(map.get("right").toString());
	}

	/* 数组转去重 */
	public static String[] getDistinctArray(String[] array) {
		if (array == null || array.length < 1) {
			return null;
		}
		Set<String> set = new HashSet<>();
		for (int i = 0; i < array.length; i++) {
			set.add(array[i]);
		}
		return (String[]) set.toArray(new String[set.size()]);
	}

	/* 数组转去重，并且返回重复项的坐标,发挥数组map */
	@SuppressWarnings("null")
	public static Map<String, String[]> getDistinctArrayAndNum(String[] array) {
		Map<String, String[]> map = new HashMap<>();
		String nums = "";
		if (array == null || array.length < 1) {
			map.put("array", null);
			map.put("nums", null);
			return map;
		}
		Set<String> set = new HashSet<>();
		Integer h = 0;
		for (int i = 0; i < array.length; i++) {
			Boolean bls = set.add(array[i]);
			if (!bls) {
				if ("".equals(nums)) {
					nums += "" + i;
				} else {
					nums += "," + i;
				}
				h++;
			}
		}
		array = (String[]) set.toArray(new String[set.size()]);
		map.put("array", array);
		map.put("nums", "".equals(nums) ? null : nums.split(","));
		return map;
	}

	/* 数组快速转string 逗号隔开 */
	public static String arrayToString(String[] arr) {
		if (arr == null || arr.length < 1) {
			return "";
		}
		return StringUtils.join(arr, ",");
	}

	/* 根据某一项或某几项 合并数组的某一项或者某几项（相加） */

	public static List<String[]> paramsAdd(List<String[]> list, Integer[] gene, Integer[] nums) {
		if (list == null || list.size() < 1) {
			return new ArrayList<>();
		}
		List<Map<String, Object>> listParam = new ArrayList<>();
		for (int i = 0; i < gene.length; i++) {
			Map<String, Object> mapRet = new HashMap<>();
			mapRet.put("name", "m" + gene[i]);
			mapRet.put("arr", "");
			listParam.add(mapRet);
		}
		for (int i = 0; i < list.size(); i++) {
			String[] arr = list.get(i);
			if ("".equals(listParam.get(0).get("arr").toString())) {
				for (Map<String, Object> mp : listParam) {
					String val = arr[Integer.valueOf(mp.get("name").toString().substring(1, 2))];
					mp.put("arr", val);
				}
			} else {
				for (Map<String, Object> mp : listParam) {
					String val = arr[Integer.valueOf(mp.get("name").toString().substring(1, 2))];
					mp.put("arr", mp.get("name").toString() + "," + val);
				}
			}
		}
		Set<String> set = new HashSet<>();
		String[] array = ids.split(",");
		for (int i = 0; i < array.length; i++) {
			Boolean bls = set.add(array[i]);
			if (!bls) {

			}
		}

	}

	/* a,b.c 和b,c,d中分别获取不同以及相同的部分 */
	public static Map<String, String> getCompareStrPart(String str1, String str2) {
		Map<String, String> map = new HashMap<>();
		if (StringUtils.isBlank(str1) || StringUtils.isBlank(str2)) {
			map.put("left", str1);
			map.put("same", "");
			map.put("right", str2);
			return map;
		}
		String[] arr1 = str1.split(",");
		String[] arr2 = str2.split(",");
		Set<String> setLeft = new HashSet<>();
		Set<String> setSame = new HashSet<>();
		Set<String> setRight = new HashSet<>();
		for (String str : arr1) {
			Set<String> set = new HashSet<String>(Arrays.asList(arr2));
			if (set.contains(str)) {
				setSame.add(str);
			} else {
				setLeft.add(str);
			}
		}
		map.put("same", StringUtils.join((String[]) setSame.toArray(new String[setSame.size()]), ","));// 顺序不能变！！！！！！！！
		Set<String> setSameCopy = setSame;
		for (String str : arr2) {
			if (setSameCopy.add(str)) {
				setRight.add(str);
			}
		}
		map.put("left", StringUtils.join((String[]) setLeft.toArray(new String[setLeft.size()]), ","));
		map.put("right", StringUtils.join((String[]) setRight.toArray(new String[setRight.size()]), ","));
		return map;
	}

}
