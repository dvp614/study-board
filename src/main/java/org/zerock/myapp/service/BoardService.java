package org.zerock.myapp.service;

import org.springframework.data.domain.Page;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.entity.Board;

public interface BoardService {
	public abstract Page<Board> findAllBoards(BoardDTO dto);
	
	public abstract boolean registerBoard(BoardDTO dto);
	
	public abstract Board findBoard(BoardDTO dto);
	
	public abstract boolean modifyBoard(BoardDTO dto);
	
	public abstract boolean removeBoard(BoardDTO dto);
} // end interface
