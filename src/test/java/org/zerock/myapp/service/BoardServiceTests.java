package org.zerock.myapp.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.entity.Board;
import org.zerock.myapp.util.RandomNumberGenerator;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class BoardServiceTests {
	@Autowired(required = false)
	private MockMvc mockMvc;
	
	@Autowired(required = false)
	private BoardService service;
	
	@PostConstruct
	void postConstruct() {
		log.trace("postConstruct() invoked.");
		
		Objects.requireNonNull(this.mockMvc);
		log.info("\t+ tihs.mockMvc : {}", this.mockMvc);
		
		Objects.requireNonNull(this.service);
		log.info("\t+ tihs.service : {}", this.service);
		
	} // postConstruct
	
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("1. testFindAllBoards")
	@Timeout(value = 1L, unit = TimeUnit.SECONDS)
	void testFindAllBoards() {
		log.trace("testFindAllBoards() invoked.");
		
		final int currPage = RandomNumberGenerator.generateOneInt(1, 10);
		final int pageSize = RandomNumberGenerator.generateOneInt(1, 15);
		
		BoardDTO dto = new BoardDTO();
		dto.setCurrPage(currPage);
		dto.setPageSize(pageSize);
		
		log.info("\t+ currPage : {}, pageSize : {}", currPage, pageSize);
		
		Page<Board> foundList = this.service.findAllBoards(dto);
		
		assert foundList !=null;
		
		foundList.forEach(l -> {log.info("\t+ foundList : {}", l);});
	} // testFindAllBoards
	
//	@Disabled
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("2. testRegisterBoard")
	@Timeout(value = 1L, unit = TimeUnit.SECONDS)
	void testRegisterBoard() {
		log.trace("testRegisterBoard() invoked.");
		
		BoardDTO dto = new BoardDTO();
		
		dto.setContent("registedCONTENT");
		dto.setTitle("registedTITLE");
		dto.setWriter("registedWRITER");
		dto.setCnt(100);
		
		boolean isRegistered = this.service.registerBoard(dto);
		
		log.info("\t isRegistered : {}", isRegistered);
	} // testRegisterBoard
	
//	@Disabled
	@Order(3)
	@Test
//	@RepeatedTest(1)
	@DisplayName("3. testFindBoard")
	@Timeout(value = 1L, unit = TimeUnit.SECONDS)
	void testFindBoard() {
		log.trace("testFindBoard() invoked.");
//		
//		BoardDTO dto = new BoardDTO();
//		
//		dto.setId(101L);
//		
//		Board foundBoard = this.service.findBoard(dto);
//		
//		log.info("\t+ foundBoard : {}", foundBoard);
		
		long randomId = RandomNumberGenerator.generateOneLong(1, 500);
		
		BoardDTO dto = new BoardDTO();
		
		dto.setId(randomId);
		
		Board foundBoard = this.service.findBoard(dto);
		
		Objects.requireNonNull(foundBoard);
		assertEquals(randomId, foundBoard.getId());
		
		log.info("\t+ foundBoard : {}", foundBoard);
	} // testFindBoard
	
//	@Disabled
	@Order(4)
	@Test
//	@RepeatedTest(1)
	@DisplayName("4. testModifyBoard")
	@Timeout(value = 1L, unit = TimeUnit.SECONDS)
	void testModifyBoard() {
		log.trace("testModifyBoard() invoked.");
		
		long randomId = RandomNumberGenerator.generateOneLong(1, 400);
		
		BoardDTO dto = new BoardDTO();
		
		dto.setId(randomId);
		
		Board foundBoard = this.service.findBoard(dto);
		
		Objects.requireNonNull(foundBoard);
		
		BoardDTO updateDTO = new BoardDTO();
		updateDTO.setId(foundBoard.getId());
		updateDTO.setContent("MODIFIED_CONTENT");
		updateDTO.setTitle(foundBoard.getTitle());
		updateDTO.setWriter(foundBoard.getWriter());
		updateDTO.setCnt(foundBoard.getCnt());
		
		boolean isModified = this.service.modifyBoard(updateDTO);
		
		log.info("\t isRegistered : {}", isModified);
	} // testModifyBoard
	
//	@Disabled
	@Order(5)
	@Test
//	@RepeatedTest(1)
	@DisplayName("5. testRemoveBoard")
	@Timeout(value = 1L, unit = TimeUnit.SECONDS)
	void testRemoveBoard() {
		log.trace("testModifyBoard() invoked.");
		
		long randomId = RandomNumberGenerator.generateOneLong(1, 400);
		
		BoardDTO dto = new BoardDTO();
		
		dto.setId(randomId);
		
		boolean isRemoved = this.service.removeBoard(dto);
		
		log.info("\t+ isRemoved : {}", isRemoved);
	} // testRemoveBoard
} // end class
