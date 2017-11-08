package cn.antke.bct.network.entities;

/**
 * Created by zhaoweiwei on 2017/5/24.
 */

public class ListDialogEntity {
	private String itemContent;
	private boolean isChecked;

	public ListDialogEntity(String itemContent, boolean isChecked) {
		this.itemContent = itemContent;
		this.isChecked = isChecked;
	}

	public String getItemContent() {
		return itemContent;
	}

	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean checked) {
		isChecked = checked;
	}
}
