package cn.antke.bct.network.entities;

import android.support.annotation.NonNull;

import cn.antke.bct.utils.Cn2Spell;

/**
 * Created by zhaoweiwei on 2017/5/16.
 */

public class ShareMemberEntity implements Comparable<ShareMemberEntity>{
	private String name;
	private String firstLetter;
	private String pinyin;//姓名对应的拼音

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public ShareMemberEntity(String name) {
		this.name = name;
		pinyin = Cn2Spell.getPinYin(name); // 根据姓名获取拼音
		firstLetter = pinyin.substring(0, 1).toUpperCase(); // 获取拼音首字母并转成大写
		if (!firstLetter.matches("[A-Z]")) { // 如果不在A-Z中则默认为“#”
			firstLetter = "#";
		}
	}

	@Override
	public int compareTo(@NonNull ShareMemberEntity o) {
		if (firstLetter.equals("#") && !o.getFirstLetter().equals("#")) {
			return 1;
		} else if (!firstLetter.equals("#") && o.getFirstLetter().equals("#")){
			return -1;
		} else {
			return pinyin.compareToIgnoreCase(o.getPinyin());
		}
	}
}
