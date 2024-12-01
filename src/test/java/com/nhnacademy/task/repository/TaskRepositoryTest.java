package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.Assertions.assertThat;

@Sql(value = "task.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void findTaskTest(){
        long taskId = 1L;

        Task task = taskRepository.findById(taskId).orElse(null);

        assertThat(task).isNotNull();
        assertThat(task.getTaskId()).isEqualTo(taskId);
        assertThat(task.getContent()).isEqualTo("태스크내용1");


    }
}
