package com.leu.littleweather.util;

import com.leu.littleweather.bean.GroupMemberBean;

import java.util.Comparator;

/**
 * 对ListView中的数据进行排序。
 * @author xiaanming
 *定义临时的比较规则
 */
public class PinyinComparator implements Comparator<GroupMemberBean> {
	//这里主要是用来对ListView里面的数据根据ABCDEFG...来排序
	//根据第一个参数小于、等于或大于第二个参数分别返回负整数、零或正整数。这里重写了。
	public int compare(GroupMemberBean o1, GroupMemberBean o2) {
		//前面两个if判断主要是将不是以汉字开头的数据放在后面
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			//相等返回0，第一个比第二个对象大返回大于零
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
