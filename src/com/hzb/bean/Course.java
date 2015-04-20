package com.hzb.bean;
/**
 * 
 * @author hzb
 * 
 * CREATE TABLE `tb_course` (
  `id` int(11) NOT NULL auto_increment COMMENT '自增id',
  `course_id` varchar(11) default NULL COMMENT '课程id',
  `name` varchar(20) default NULL COMMENT '课程名称',
  `hours` varchar(20) default NULL COMMENT '课程时数',
  `credit` varchar(20) default NULL COMMENT '课程学分',
  `nature` varchar(20) default NULL COMMENT '课程性质',
  `is_del` tinyint(2) default NULL COMMENT '是否删除',
  PRIMARY KEY  (`id`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 *
 */
public class Course extends BaseBean {
	
	private String course_id;
	private String name;
	private String hours;
	private String credit;
	private String nature;
	private int isDel;
	
	
	
	public Course() {
		super();
	}

	public Course(int id,String course_id, String name, String hours, String credit,
			String nature, int isDel) {
		super();
		this.id = id;
		this.course_id = course_id;
		this.name = name;
		this.hours = hours;
		this.credit = credit;
		this.nature = nature;
		this.isDel = isDel;
	}
	
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Course [course_id=");
		builder.append(course_id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", hours=");
		builder.append(hours);
		builder.append(", credit=");
		builder.append(credit);
		builder.append(", nature=");
		builder.append(nature);
		builder.append(", isDel=");
		builder.append(isDel);
		builder.append("]");
		return builder.toString();
	}
	
}
