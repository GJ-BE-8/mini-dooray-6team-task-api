package com.nhnacademy.task.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class ProjectMemberRepository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String HASH_NAME = "project-members:";

    // add
    public List<Long> addProjectMember(long memberId, long projectId) {
        String projectKey = String.valueOf(projectId); // Long to String

        Object object = redisTemplate.opsForHash().get(HASH_NAME, projectKey);

        List<Long> list = (object != null) ? (List<Long>) object : new ArrayList<>();

        if (list.contains(memberId)) {
            throw new IllegalArgumentException("memberId already exist");
        }
        list.add(memberId);

        redisTemplate.opsForHash().put(HASH_NAME, projectKey, list);
        return list;
    }

    // 멤버가 가진 프로젝트들 가져오기
    public List<Long> getProjectMember(long projectId) {
        String projectKey = String.valueOf(projectId); // Long to String

        Map<Object, Object> entries = redisTemplate.opsForHash().entries(HASH_NAME);
        List<Long> list = new ArrayList<>(entries.size());
        for (Object o : entries.keySet()) {
            if (o.equals(projectKey)) {
                list = (List<Long>) entries.get(projectKey);
            }
        }
        return list;
    }

    // 삭제
    public void deleteProjectMember(long memberId, long projectId) {
        String projectKey = String.valueOf(projectId); // Long to String
        List<Long> memberProjects = getProjectMember(projectId);
        Iterator<Long> iterator = memberProjects.iterator();
            Long memberProject = iterator.next();
            if (memberProject.equals(memberId)) {
                iterator.remove();
                redisTemplate.opsForHash().put(HASH_NAME, projectKey, memberProjects);
            }
        }
    }