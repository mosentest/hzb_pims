package com.hzb.bean;
/**
 * 
 * @author hzb
 *
 *CREATE TABLE `tb_research` (
  `id` int(11) NOT NULL auto_increment COMMENT '自增，研究id',
  `teacher_id` varchar(11) default NULL COMMENT '员工id',
  `direction` varchar(255) default NULL COMMENT '研究方向',
  `situation` varchar(255) default NULL COMMENT '课题研究情况',
  `patent` varchar(255) default NULL COMMENT '专利',
  `other` varchar(255) default NULL COMMENT '论文及著作发表情况',
  `is_del` tinyint(2) default '0' COMMENT '是否删除，0为未删除，1为删除',
  PRIMARY KEY  (`id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `tb_research_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `tb_staff` (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Research extends BaseBean{
	private Staff staff;
	private String direction;
	private String situation;
	private String patent;
	private String other;
	private int is_del;
	
	
	public Research() {
		super();
	}


	public Research(int id,String teacherId, String direction, String situation,
			String patent, String other, int is_del) {
		super();
		this.id=id;
		this.staff = new Staff();
		this.staff.setStaffId(teacherId);
		this.direction = direction;
		this.situation = situation;
		this.patent = patent;
		this.other = other;
		this.is_del = is_del;
	}


	public Staff getStaff() {
		return staff;
	}


	public void setStaff(Staff staff) {
		this.staff = staff;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}


	public String getSituation() {
		return situation;
	}


	public void setSituation(String situation) {
		this.situation = situation;
	}


	public String getPatent() {
		return patent;
	}


	public void setPatent(String patent) {
		this.patent = patent;
	}


	public String getOther() {
		return other;
	}


	public void setOther(String other) {
		this.other = other;
	}


	public int getIs_del() {
		return is_del;
	}


	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}


	@Override
	public String toString() {
		return "Research [staff=" + staff.toString() + ", direction=" + direction
				+ ", situation=" + situation + ", patent=" + patent
				+ ", other=" + other + ", is_del=" + is_del + "]";
	}


	

}
