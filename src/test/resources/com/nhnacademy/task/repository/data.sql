-- project
insert into project(project_name, status, admin_id) values("프로젝트1", "활성", 100);
insert into project(project_name, status, admin_id) values("프로젝트2", "휴면", 200);
insert into project(project_name, status, admin_id) values("프로젝트3", "종료", 300);

--milestone
insert into mileStone(project_id, milestone_name) values
    (1, "마일스톤1")


insert into mileStone(project_id, milestone_name) values
    (2, "마일스톤2")


--task
insert into task(project_id, content, milestone_id) values
    (1, "태스크 내용1", 1),
    (2, "태스크 내용2", 2);

--comment
INSERT INTO comment (task_id, writer_id, content) values (1, 'user1', 'test1'), (2,'user2','test2');



--tag
    insert into tag(project_id, tag_name) values(1, "태그1");
insert into tag(project_id, tag_name) values(1, "태그2");
insert into tag(project_id, tag_name) values(1, "태그3");


insert into tag(project_id, tag_name) values(2, "태그1")
    insert into tag(project_id, tag_name) values(2, "태그2");
insert into tag(project_id, tag_name) values(2, "태그3");



--tag-task
insert into tagtask(tag_id, task_id) values(1, 1);
insert into tagtask(tag_id, task_id) values(1, 2);
insert into tagtask(tag_id, task_id) values(1, 1);
insert into tagtask(tag_id, task_id) values(2, 1);
insert into tagtask(tag_id, task_id) values(3, 2);
insert into tagtask(tag_id, task_id) values(4, 2);