package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import hellojpa.entity.Member;
import hellojpa.entity.MemberType;

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
			Member member = new Member();
			member.setId(100L); // Long 타입은 뒤에 꼭 L을 붙여야.
			member.setName("안녕하세요.");
			member.setMemberType(MemberType.ADMIN);
			
			// member 객체 DB 저장 
			em.persist(member);
			
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
