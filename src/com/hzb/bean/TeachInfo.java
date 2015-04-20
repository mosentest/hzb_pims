package com.hzb.bean;
/**
 * 
 * @author hzb
 * 
 * CREATE TABLE `tb_teach_info` (
  `id` int(11) NOT NULL auto_increment COMMENT '自增，教学信息id',
  `teacher_id` varchar(11) default NULL COMMENT '员工id',
  `course_id` varchar(11) default NULL COMMENT '课程id',
  PRIMARY KEY  (`id`),
  KEY `teacher_id` (`teacher_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `tb_teach_info_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `tb_staff` (`staff_id`),
  CONSTRAINT `tb_teach_info_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `tb_course` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 *
 */
public class TeachInfo extends BaseBean {
	
	private Staff staff;
	private Course course;
	private String courseId;
	private String teacherId;

	public TeachInfo() {
		super();
	}

	public TeachInfo(Staff staff, Course course) {
		super();
		this.staff = staff;
		this.course = course;
	}

	public TeachInfo(String courseId, String teacherId) {
		super();
		this.courseId = courseId;
		this.teacherId = teacherId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TeachInfo [courseId=");
		builder.append(courseId);
		builder.append(", teacherId=");
		builder.append(teacherId);
		builder.append("]");
		return builder.toString();
	}

}
