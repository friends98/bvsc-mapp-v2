package model.entity;

public class Student {
	private String name;
	private Integer age;
	public Student() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param name
	 * @param age
	 */
	public Student(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	

}
