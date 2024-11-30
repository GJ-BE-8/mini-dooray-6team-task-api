insert into project(project_name, status, admin_id) values('프로젝트1', '활성', 100);
insert into mile_stone(project_project_id, milestone_name) values (1, '마일스톤1');
insert into task(project_project_id, content, mile_stone_milestone_id) values
                                (1, '태스크내용1', 1);

