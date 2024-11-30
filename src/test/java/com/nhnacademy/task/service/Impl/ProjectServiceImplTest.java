package com.nhnacademy.task.service.Impl;

import com.nhnacademy.task.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProjectServiceImplTest {

    @Autowired
    private ProjectService projectService;

    @Sql("comment.sql")
    @Test
    void saveProject() {
    }
}