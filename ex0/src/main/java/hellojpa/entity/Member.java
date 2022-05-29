package hellojpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.AUTO) // @GeneratedValue(strategy = GenerationType.AUTO):auto로 하면 방언에 맞는 값이 자동으로 생성된다. ex.mysql - auto increment / oracle - 시퀀스 
	private Long id;
	
	@Column(name = "USERNAME", nullable = false, length = 20) // db에서의 컬럼명 
	private String name; // 자바 객체에서 필드명 
	
	private int age;
	
	@ManyToOne
	@JoinColumn(name = "TEAM_ID") // TEAM_ID 컬럼이 pk, fk 관계 
	private Team team;
	
//	// 참조 대신에 외래키를 그대로 넣은 경우 
//	@Column(name = "TEAM_ID")
//	private Long teamId;
	
//	@Enumerated(EnumType.STRING) // ENUM 쓸 때. 현업에서 무조건 STRING 써야(글자가 들어감). 
//	private MemberType memberType;
	
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
//	public MemberType getMemberType() {
//		return memberType;
//	}
//	public void setMemberType(MemberType memberType) {
//		this.memberType = memberType;
//	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
}
