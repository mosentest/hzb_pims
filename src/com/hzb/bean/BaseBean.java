package com.hzb.bean;

import java.io.Serializable;

public abstract class BaseBean implements Serializable {
	protected int id;// 自增id

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
