package jpabook.jpashop2.service;

import jpabook.jpashop2.domain.Member;
import jpabook.jpashop2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; //Spring과 javax에서 제공하는 2가지가 있는데 Spring이 제공하는 것을 써야 더 다양한 기능을 쓸 수 있다.

import java.util.List;

@Service
@Transactional(readOnly = true) //기본 값을 false, 클래스 안에 메소드들이 더 많이 쓰는 것을 설정하고, 해당 안되는 메소드는 따로 설정
//@AllArgsConstructor //필드를 가지는 생성자를 자동으로 만들어줌
@RequiredArgsConstructor //final로 선언된 필드만 가지고 생성자를 만들어줌
public class MemberService {

    //@Autowired //Spring이 Spring bean에 있는 MemberRepository를 injection 해줌
    private final MemberRepository memberRepository; //변경 할 일이 없기 때문에 final로 선언하는 것을 권장

    /*@Autowired //위에 방법과 Setter Injection 방법보다 좋은 방법, 최신 스프링은 생성자가 하나라면 Autowired 생략해도 자동으로 인젝션 해준다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /**
     * 회원 가입
     * */
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
