package hellojpa;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import hellojpa.entity.Member;
import hellojpa.entity.MemberType;
import hellojpa.entity.Team;

public class Main {

	public static void main(String[] args) {

		// EntityManagerFactory는 서버 띄울 때 딱 1번을 로딩.(딱 1개만 생성해서 웹 어플리케이션 전체 공유)
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("hello");
		// persistence.xml (jpa 설정파일의 persistence-unit태그의 name속성이 하단의 hello)

		// 그리고 실제 유저가 접근해서 비즈니스 로직을 태울 때 마다 팩토리에서 EntityManager라는 것 꺼내서 사용.
		// EntityManager가 흔히 생각하는 jpa 그 자체.
		// EntityManager는 쓰레드간에 공유하면 x.(사용후 버려야)
		// 실제 웹 어플리케이션에서 고객의 요청이 들어올 때 마다 EntityManager 생성 
		EntityManager em = emf.createEntityManager();
		
		// EntityManager 로부터 트랜잭션 얻기
		// jpa의 모든 데이터 변경은 트랜잭션 안에서 실행 
		EntityTransaction tx = em.getTransaction();
		// 트랜잭션 시작. (db에 접근해서 커넥션을 가져온 뒤, 실제 트랜잭션 시작)
		tx.begin();
		
		try {
			
			// 팀 저장
			Team team = new Team();
			team.setName("teamA");

			em.persist(team);
			
			// 회원저장 
			Member member = new Member();
			// member.setId(100L); // Long 타입은 뒤에 꼭 L을 붙여야.
			member.setName("hello");
//			member.setTeamId(team.getId());
			
			// 양방향 매핑: 양쪽 다 세팅 
			
			// 1. 연관관계 주인에 값 설정 
			member.setTeam(team);
			
			// 2. 무시 
			team.getMembers().add(member);
			
//			member.setMemberType(MemberType.ADMIN);
			
			// member 객체 DB 저장 
			em.persist(member);
			
			// DB에 쿼리를 다 보내기 
			em.flush();
			
			// 캐시를 깨끗하게 지움 
			em.clear();
			
			// 조회(연관관계가 없어서 직접 하나하나 가져와야 함) -> 객체지향적 x
			Member findMember = em.find(Member.class, member.getId());
//			Long teamId = findMember.getTeamId();
//			Team findTeam = em.find(Team.class, teamId);
			
			// 객체 지향 모델링 
			Team findTeam = findMember.getTeam();
			
			findTeam.getName();
			
			// 양방향 매핑 조회 
			List<Member> members = findTeam.getMembers();
			for (Member member1 : members) {
				System.out.println("member1 = " + member1);
			}
			
			// 커밋
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			// EntityManager는 사용후 닫기 필수(DB 접근 리소스 관리 차원)
			em.close();
		}
		
		// EntityManagerFactory 종료 필수(웹 어플리케이션이 전체적으로 끝나면 내리는 것) 
		emf.close();
	}

}
