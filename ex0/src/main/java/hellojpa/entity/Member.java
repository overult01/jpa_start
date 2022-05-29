package hellojpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Member {

	@Id
	private Long id;
	
	@Column(name = "USERNAME") // db에서의 컬럼명 
	private String name; // 자바 객체에서 필드명 
	
	private int age;
	
	@Enumerated(EnumType.STRING) // ENUM 쓸 때. 현업에서 무조건 STRING 써야(글자가 들어감). 
	private MemberType memberType;
	
	// jpa는 기본적으로 getter, setter 와 생성자 기반으로 동작 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public MemberType getMemberType() {
		return memberType;
	}
	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}
}
