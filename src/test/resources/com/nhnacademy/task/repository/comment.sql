insert into project(project_name, status, admin_id) values('프로젝트1', '활성', 100);
insert into mile_stone(project_project_id, milestone_name) values (1, '마일스톤1');
insert into task(project_project_id, content, mile_stone_milestone_id) values (1, '태스크 내용1', 1);
insert into tag(project_project_id, tag_name) values(1, '태그1');
insert into tag_task(tag_tag_id, task_task_id) values(1, 1);
INSERT INTO comment (task_task_id,writer_id, content) values (1,'user1', 'test1');


