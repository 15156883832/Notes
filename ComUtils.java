package test1.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class ComUtils {

	public static void main(String[] args) {
		List<String[]> list = new ArrayList<>();
		String[] arr1 = new String[] { "1", "2", "2", "2", "1", "3", "1" };
		String[] arr2 = new String[] { "ds", "vc", "4", "cat", "2", "3", "6" };
		String[] arr3 = new String[] { "people", "ani", "ani", "ani", "people", "people", "people" };
		String[] arr4 = new String[] { "哈哈", "小狗", "4", "小狗", "嘿嘿", "3", "哈哈" };
		String[] arr5 = new String[] { "2", "3", "6", "2", "2", "3", "6" };
		String[] arr6 = new String[] { "1", "tony", "4", "cat", "2", "3", "6" };
		String[] arr7 = new String[] { "1", "2", "3", "6", "2", "4", "10" };
		list.add(arr1);
		list.add(arr2);
		list.add(arr3);
		list.add(arr4);
		list.add(arr5);
		list.add(arr6);
		list.add(arr7);
		List<String[]> listRet = repeatArray(list, new Integer[] { 0, 2, 3 }, new Integer[] { 4, 6 });
		for (String[] arr : listRet) {
			System.out.println(StringUtils.join(arr, ",") + "\n");
		}

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

	/*
	 * 根据某一项或某几项 合并数组的某一项或者某几项（相加）
	 * 
	 * { "001", "0", "a", "type", "2", "5", "00" } { "101", "0", "b", "type", "1",
	 * "2", "00" }; { "001", "1", "a", "type", "5", "5", "00" } { "001", "0", "a",
	 * "type", "7", "3", "00" } { "101", "0", "b", "type", "1", "10", "00" } {
	 * "001", "0", "c", "type", "3", "1", "00" } paramsAdd(list, new Integer[] { 0,
	 * 1, 2 }, new Integer[] { 4, 5 })
	 * 
	 */

	public static List<String[]> paramsAdd(List<String[]> list, Integer[] gene, Integer[] nums) {
		if (list == null || gene == null || nums == null || list.size() < 1 || gene.length < 1 || nums.length < 1) {
			return new ArrayList<>();
		}
		List<String[]> listRet = new ArrayList<>();
		for (String[] arr : list) {
			Integer len = gene.length + nums.length + 1;
			String[] newArr = new String[gene.length + nums.length + 1];
			int h = 0;
			String str = "";
			for (Integer m : gene) {
				str += "," + arr[m];
				newArr[h] = arr[m];
				h++;
			}
			Map<String, String> mapCheck = compareWith(str, listRet);
			String code = mapCheck.get("code");
			String sameNum = mapCheck.get("sameNum");
			if ("200".equals(code)) {// 直接加，不重复
				for (Integer n : nums) {
					newArr[h] = arr[n];
					h++;
				}
				newArr[len - 1] = str;
				listRet.add(newArr);
			} else {// 存在重复的数据
				String[] arry = listRet.get(Integer.valueOf(sameNum));// 获取已存在的数据
				int y = gene.length;
				for (Integer n : nums) {
					arry[y] = String.valueOf((Integer.valueOf(arry[y]) + Integer.valueOf(arr[n])));
					y++;
				}
			}
		}
		for (String[] arrRet : listRet) {
			arrRet[arrRet.length - 1] = "";
		}
		return listRet;
	}

	public static Map<String, String> compareWith(String str, List<String[]> listRet) {
		Map<String, String> map = new HashMap<>();
		String code = "200";
		map.put("sameNum", "");
		map.put("code", code);
		if (listRet.size() < 1) {
			return map;
		}

		int i = 0;
		for (String[] arr : listRet) {
			String str1 = arr[arr.length - 1];
			if (str1.equals(str)) {
				code = "420";
				map.put("code", code);
				map.put("sameNum", String.valueOf(i));
			}
			i++;
		}
		return map;
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

	/*
	 * 
	 * 去重组合相加 {a1,a2,a3,a4,a5},{b1,b2,b3,b4,b5},{c1,c2,c3,c4,c5},{d1,d2,d3,d4,d5},
	 * {e1,e2,e3,e4,e5},{f1,f2,f3,f4,f5},{g1,g2,g3,g4,g5}
	 * sameNum={0,2,4}————a、c、e相同判别 ；addNum={1,5}————————b、f相加
	 * 
	 * 
	 **/
	public static List<String[]> repeatArray(List<String[]> lists, Integer[] sameNum, Integer[] addNum) {
		if (lists == null || sameNum == null || addNum == null || lists.size() < 1 || sameNum.length < 1 || addNum.length < 1) {
			return new ArrayList<String[]>();
		}
		List<String[]> list = inversonControl(lists);// 数组重组
		Set<String> set = new HashSet<>();
		List<String[]> listRet = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			String[] arr1 = list.get(i);

			String newArr = "";
			for (int m = 0; m < sameNum.length; m++) {
				newArr = "," + arr1[sameNum[m]];
			}
			if (!set.add(newArr)) {// 已存在
				continue;
			}

			for (int j = i + 1; j < list.size(); j++) {
				String[] arr2 = list.get(j);
				Boolean addMark = true;
				for (int n = 0; n < sameNum.length; n++) {
					if (!arr1[sameNum[n]].equals(arr2[sameNum[n]])) {
						addMark = false;
						break;
					}
				}
				if (addMark) {// 需要相同的项都相同，则对应项相加
					for (int n = 0; n < addNum.length; n++) {
						arr1[addNum[n]] = String.valueOf(Integer.valueOf(arr1[addNum[n]]) + Integer.valueOf(arr2[addNum[n]]));
					}
				}
			}

			listRet.add(arr1);
		}
		return inversonControl(listRet);
	}

	public static List<String[]> inversonControl(List<String[]> list) {
		if (list == null || list.size() < 1) {
			return new ArrayList<>();
		}
		String[] arrOne = list.get(0);// 获取其中的一组
		List<String[]> listRet = new ArrayList<>();
		for (int i = 0; i < arrOne.length; i++) {
			String[] newArr = new String[list.size()];
			for (int j = 0; j < list.size(); j++) {
				newArr[j] = list.get(j)[i];
			}
			listRet.add(newArr);
		}

		return listRet;
	}

}
