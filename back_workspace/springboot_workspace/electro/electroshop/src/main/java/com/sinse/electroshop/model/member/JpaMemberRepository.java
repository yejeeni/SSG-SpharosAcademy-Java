package com.sinse.electroshop.model.member;


import com.sinse.electroshop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<Member, Integer> {
    //거의 대부분의 CRUD 메서드를 JpaRepository가 보유하고 있으므로, 개발자는
    //이 인터페이스가 지원하지 않는 메서드만을 정의하거나, 또는 메서드명을 바꾸고자할때만 메서드를
    //재정이하면 됨다.
    // 메서드명 정의는 자유로우나, 매개변수와 메서드명에서 사용되는 필드명은 반드시
    // 모델 객체의 멤버변수와 일치해야 한다..
    public Member findByIdAndPassword(String id, String password);
}








