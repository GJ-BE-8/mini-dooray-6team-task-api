package com.nhnacademy.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task.domain.MileStone;
import com.nhnacademy.task.request.MileStoneRequest;
import com.nhnacademy.task.service.Impl.MileStoneServiceImpl;
import com.nhnacademy.task.service.MileStoneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MileStoneController.class)  // 컨트롤러만 테스트
class MileStoneControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private MileStoneServiceImpl mileStoneService;  // @MockBean 사용





    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new MileStoneController(mileStoneService)).build();
   }

    @Test
    void testCreateMileStone() throws Exception {
        // given
        MileStoneRequest mileStoneRequest = new MileStoneRequest(1L, "New MileStone");
        MileStone mileStone = new MileStone(1L, "New MileStone", null);  // Mocked MileStone 객체

        // Mocking MileStoneService의 addMilestoneToProject 메서드
        when(mileStoneService.addMilestoneToProject(anyLong(), any())).thenReturn(mileStone);

        // when & then
        mockMvc.perform(post("/milestone")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(mileStoneRequest)))  // 요청 본문 설정
                .andExpect(status().isOk())  // 상태 코드 OK 확인
                .andExpect(jsonPath("$.milestoneName").value("New MileStone"))  // milestoneName 확인
                .andExpect(jsonPath("$.milestoneId").value(1L));  // milestoneId 확인

        // Verify that the service method was called
        verify(mileStoneService).addMilestoneToProject(anyLong(), any());
    }

    @Test
    void testGetMileStone() throws Exception {
        // given
        MileStone mileStone = new MileStone(1L, "Existing MileStone", null);

        // Mocking MileStoneService의 findById 메서드
        when(mileStoneService.findById(anyLong())).thenReturn(mileStone);

        // when & then
        mockMvc.perform(get("/milestones/{milestoneId}", 1L))  // GET 요청 보내기
                .andExpect(status().isOk())  // 상태 코드 OK 확인
                .andExpect(jsonPath("$.milestoneName").value("Existing MileStone"))  // milestoneName 확인
                .andExpect(jsonPath("$.milestoneId").value(1L));  // milestoneId 확인

        // Verify that the service method was called
        verify(mileStoneService).findById(anyLong());
    }

    @Test
    void testDeleteMileStone() throws Exception {
        // given
        long milestoneId = 1L;

        // when & then
        mockMvc.perform(delete("/milestones/delete/{milestoneId}", milestoneId))  // DELETE 요청 보내기
                .andExpect(status().isOk());  // 상태 코드 OK 확인

        // Verify that the service method was called
        verify(mileStoneService).deleteMileStone(milestoneId);
    }
}
