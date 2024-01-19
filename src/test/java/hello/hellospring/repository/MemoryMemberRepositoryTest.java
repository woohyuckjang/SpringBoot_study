package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach          // Test 클리어 코드
    public void afterEach(){

        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("WooHyuck");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("WooHyuck1");
        repository.save(member1);

        Member member2 = new Member();          // Shift + F6 -> 일괄 변경
        member2.setName("WooHyuck2");
        repository.save(member2);

        Member result = repository.findByName("WooHyuck1").get();

        assertThat(result).isEqualTo(member1);
    }
    // Test가 끝날 시 데이터를 클리어 해줘야한다.

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("WooHyuck1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("WooHyuck2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}

// Test는 순서 의존 관계 X
