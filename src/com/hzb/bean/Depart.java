package com.hzb.bean;
/**
 * 
 * @author hzb
 *
 *CREATE TABLE `tb_depart` (
  `id` int(11) NOT NULL auto_increment COMMENT '自增id',
  `depart_id` varchar(20) default NULL COMMENT '部门id',
  `depart_name` varchar(20) default NULL COMMENT '部门名称',
  `is_del` tinyint(2) default NULL COMMENT '是否删除',
  PRIMARY KEY  (`id`),
  KEY `depart_id` (`depart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Depart extends BaseBean{

	private String departId;
	private String departName;
	private int isDel;

	public Depart() {
		super();
	}

	public Depart(int id,String departId, String departName, int isDel) {
		super();
		this.id=id;
		this.departId = departId;
		this.departName = departName;
		this.isDel = isDel;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int idDel) {
		this.isDel = idDel;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Depart [departId=");
		builder.append(departId);
		builder.append(", departName=");
		builder.append(departName);
		builder.append(", isDel=");
		builder.append(isDel);
		builder.append("]");
		return builder.toString();
	}

}
