package org.zerock.myapp.persistence;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

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
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.entity.Board;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class BoardRepositoryTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private BoardRepository dao;
	
	@PostConstruct
	void postContstruct() {
		log.trace("postContstruct() invoked.");
		
		Objects.requireNonNull(this.mockMvc);
		log.info("\t+ this.mockMvc : {}", this.mockMvc);
		
		Objects.requireNonNull(this.dao);
		log.info("\t+ this.dao : {}", this.dao);
	} // postContstruct
	

//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("1. testCreateBoard")
	@Timeout(value = 1L, unit = TimeUnit.SECONDS)
	void testCreateBoard() {
		log.trace("testCreateBoard() invoked.");
		
		IntStream.rangeClosed(1, 200).forEachOrdered(seq ->{
			
			Board transientBoard = new Board();
			
			transientBoard.setTitle("TITLE-" + seq);
			transientBoard.setWriter("WRITER-"+ seq);
			transientBoard.setContent("CONTENT-" + seq);
	//		transientBoard.setCnt(0);
			
			Board savedBoard = this.dao.<Board>save(transientBoard);
			
			assert savedBoard != null;
			log.info("\t+ savedBoard : {}", savedBoard);
			
		}); // .forEachOrdered
	} // testCreateBoard
	
	//	@Disabled
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("2. testReadBoard")
	@Timeout(value = 1L, unit = TimeUnit.SECONDS)
	void testReadBoard() {
		log.trace("testReadBoard() invoked.");
		
		final Long id = 30L;
		Optional<Board> foundBoard = this.dao.findById(id);
		
		foundBoard.ifPresent(b -> {
			log.info("\t+ foundBoard : {}", b);
		});
		
		foundBoard.orElseThrow();
	} // testReadBoard
	
//	@Disabled
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("3. testUpdateBoard")
	@Timeout(value = 1L, unit = TimeUnit.SECONDS)
	void testUpdateBoard() {
		log.trace("testUpdateBoard() invoked.");
		
		final Long id = 30L;
		Optional<Board> foundBoard = this.dao.findById(id);
		
		foundBoard.ifPresent(b -> {
			b.setCnt(100);
			b.setTitle("MODIFIED");
			b.setContent("MODIFIED_CONTENT");
			
			this.dao.save(b);
		}); // .ifPresent
		
	} // testUpdateBoard
	
//	@Disabled
	@Order(3)
	@Test
//	@RepeatedTest(1)
	@DisplayName("3. testDeleteBoard")
	@Timeout(value = 1L, unit = TimeUnit.SECONDS)
	void testDeleteBoard() {
		log.trace("testDeleteBoard() invoked.");
		
		final Long id = 30L;
		Optional<Board> foundBoard = this.dao.findById(id);
		
		foundBoard.ifPresent(b -> {
			this.dao.deleteById(id);
		}); // .ifPresent
		
	} // testDeleteBoard
	
//	@Disabled
	@Order(4)
	@Test
//	@RepeatedTest(1)
	@DisplayName("4. testFindAllBoard")
	@Timeout(value = 1L, unit = TimeUnit.SECONDS)
	void testFindAllBoard() {
		log.trace("testFindAllBoard() invoked.");
		
		List<Board> foundList = this.dao.findAll();
		
		foundList.forEach(l -> {log.info(l.toString());});
	} // testFindAllBoard
} // end class
