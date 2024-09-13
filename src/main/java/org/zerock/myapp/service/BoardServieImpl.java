package org.zerock.myapp.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.entity.Board;
import org.zerock.myapp.persistence.BoardRepository;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@NoArgsConstructor

@Service
public class BoardServieImpl implements BoardService{
	@Autowired(required = false)
	private BoardRepository dao;
	
	@PostConstruct
	void postConstruct() {
		log.trace("postConstruct() invoked.");
		
		Objects.requireNonNull(this.dao);
		log.info("\t+ this.dao : {}", this.dao);
	} // postConstruct
	
	@Override
	public Page<Board> findAllBoards(BoardDTO dto) {
		log.trace("findAllBoards({}) invoked.", dto);
		
		Integer currPage = dto.getCurrPage();
		Integer pageSize = dto.getPageSize();		
		
		if(currPage == null || currPage< 1)currPage = 0; 
		if(pageSize == null) pageSize = 10;
		
		Pageable paging = (Pageable) PageRequest.of(currPage,pageSize, Sort.Direction.DESC, "id");
		
		return this.dao.findAll(paging);
	} // findAllBoards

	@Override
	public boolean registerBoard(BoardDTO dto) {
		log.trace("registerBoard({}) invoked.", dto);
		
		Board transientBoard = new Board();
		
		transientBoard.setTitle(dto.getTitle());
		transientBoard.setWriter(dto.getWriter());
		transientBoard.setContent(dto.getContent());
		transientBoard.setCnt(dto.getCnt());
		
		Board savedBoard = this.dao.<Board>save(transientBoard);
		
		return (savedBoard != null);
	} // registerBoard

	@Override
	public Board findBoard(BoardDTO dto) {
		log.trace("findById({}) invoked.", dto);
		
		Board foundBoard = this.dao.findById(dto.getId()).orElse(null);
		
		if(foundBoard != null) {
			foundBoard.setCnt(foundBoard.getCnt()+1);
			
			this.dao.save(foundBoard);
		} // if
		
		return foundBoard;
	} // findBoardById

	@Override
	public boolean modifyBoard(BoardDTO dto) {
		log.trace("modifyBoard({}) invoked.", dto);
		
		Optional<Board> optional = this.dao.findById(dto.getId());
		
		optional.ifPresent(b -> {
			b.setTitle(dto.getTitle());
			b.setWriter(dto.getWriter());
			b.setContent(dto.getContent());
			b.setCnt(dto.getCnt());
			
			this.dao.save(b);
		}); //.ifPresent
		
		return (optional.isPresent() == true);
	} // modifyBoard

	@Override
	public boolean removeBoard(BoardDTO dto) {
		log.trace("removeBoard({}) invoked.", dto);
		
		try{ 
			this.dao.deleteById(dto.getId()); 
			return true;
		} catch(Exception e){ 
			return false; 
		} // try-catch
	} // removeBoard

} // end class
