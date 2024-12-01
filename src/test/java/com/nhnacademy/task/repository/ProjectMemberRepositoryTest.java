package com.nhnacademy.task.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ProjectMemberRepositoryTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ProjectMemberRepository repository;

    private static final String HASH_NAME = "project-members:";

    @BeforeEach
    void setUp() {
        redisTemplate.opsForHash().delete(HASH_NAME, "1");
    }

    @Test
    void addProjectMember() {
        repository.addProjectMember(1L, 1L);

        assertThrows(org.springframework.dao.InvalidDataAccessApiUsageException.class, () -> {
            repository.addProjectMember(1L, 1L);
        });
    }


    @Test
    void getProjectMember() {
        repository.addProjectMember(1L, 1L);
        repository.addProjectMember(2L, 1L);

        List<Long> members = repository.getProjectMember(1L);

        assertThat(members).containsExactly(1L, 2L);
    }

    @Test
    void deleteProjectMember() {
        repository.addProjectMember(1L, 1L);
        repository.addProjectMember(2L, 1L);

        repository.deleteProjectMember(1L, 1L);

        List<Long> members = repository.getProjectMember(1L);

        assertThat(members).containsExactly(2L);
    }
}
