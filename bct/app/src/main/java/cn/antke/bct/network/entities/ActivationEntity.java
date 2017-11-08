package cn.antke.bct.network.entities;

/**
 * Created by zhaoweiwei on 2017/5/18.
 */

public class ActivationEntity {
	private String name;
	private boolean isSelected;

	public ActivationEntity(String name, boolean isSelected) {
		this.name = name;
		this.isSelected = isSelected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean selected) {
		isSelected = selected;
	}
}
