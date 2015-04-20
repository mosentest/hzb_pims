package com.hzb.bean;
/**
 * 
 * @author hzb
 *
CREATE TABLE `tb_staff` (
  `id` int(11) NOT NULL auto_increment COMMENT '自增长id',
  `staff_id` varchar(20) default NULL COMMENT '员工编号',
  `name` varchar(32) default NULL COMMENT '姓名',
  `sex` tinyint(2) default NULL COMMENT '性别',
  `educational` tinyint(2) default NULL COMMENT '学历，0小学以下，1初中，2高中，3中专，4大专，5大学，6研究生，7博士生，8博士后，9社会人士，10其他',
  `depart_id` varchar(20) default NULL COMMENT '部门id',
  `college` varchar(20) default NULL COMMENT '毕业院校',
  `health` varchar(20) default NULL COMMENT '健康情况',
  `job_name` varchar(20) default NULL COMMENT '职称',
  `position` varchar(20) default NULL COMMENT '职务',
  `rewards_punishment` varchar(255) default NULL COMMENT '奖惩',
  `state` tinyint(2) default '0' COMMENT '0在职，1转出，2辞职，3退休',
  `is_del` tinyint(2) default '0' COMMENT '是否删除，0为未删除，1为删除',
  PRIMARY KEY  (`id`),
  KEY `staff_id` (`staff_id`),
  CONSTRAINT `tb_staff_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `tb_depart` (`depart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Staff extends BaseBean{
	
	private String staffId;
	private String name;
	private int sex;
	private int education;
	private Depart depart;
	private String college;
	private String health;
	private String jobName;
	private String position;
	private String rewardsPunishment;
	private int state;
	private int isDel;
	
	public Staff() {
		super();
	}
	
	public Staff(int id,String staffId, String name, int sex, int education,
			String depart_id, String college, String health, String jobName,
			String position, String rewardsPunishment,int state, int isDel) {
		super();
		this.id=id;
		this.staffId = staffId;
		this.name = name;
		this.sex = sex;
		this.education = education;
		//创建对象
		this.depart = new Depart();
		this.depart.setDepartId(depart_id);
		this.college = college;
		this.health = health;
		this.jobName = jobName;
		this.position = position;
		this.rewardsPunishment = rewardsPunishment;
		this.state = state;
		this.isDel = isDel;
	}

	public String getStaffId() {
		return staffId;
	}


	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getSex() {
		return sex;
	}


	public void setSex(int sex) {
		this.sex = sex;
	}


	public int getEducation() {
		return education;
	}


	public void setEducation(int education) {
		this.education = education;
	}


	public Depart getDepart() {
		return depart;
	}


	public void setDepart(Depart depart) {
		this.depart = depart;
	}


	public String getCollege() {
		return college;
	}


	public void setCollege(String college) {
		this.college = college;
	}


	public String getHealth() {
		return health;
	}


	public void setHealth(String health) {
		this.health = health;
	}


	public String getJobName() {
		return jobName;
	}


	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getRewardsPunishment() {
		return rewardsPunishment;
	}


	public void setRewardsPunishment(String rewardsPunishment) {
		this.rewardsPunishment = rewardsPunishment;
	}


	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getIsDel() {
		return isDel;
	}


	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", name=" + name + ", sex=" + sex
				+ ", education=" + education + ", depart=" + depart
				+ ", college=" + college + ", health=" + health + ", jobName="
				+ jobName + ", position=" + position + ", rewardsPunishment="
				+ rewardsPunishment + ", state=" + state + ", isDel=" + isDel
				+ "]";
	}


	
}
