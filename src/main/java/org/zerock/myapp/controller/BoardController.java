package org.zerock.myapp.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.common.CommonBeanCallbacks;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.entity.Board;
import org.zerock.myapp.service.BoardService;
import org.zerock.myapp.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

@RequestMapping("/board/")
@Controller
public class BoardController extends CommonBeanCallbacks {
	// 표현계층은, 바로 뒤의 비지니스 계층(Business Layer)의 서비스 빈을 이용해서, 수신받은 요청을 의뢰하고, 그 결과인 모델을 받고
	// 뷰의 이름을 반환하면 됩니다.(즉, Model and View를 반환하는 것이, Controller의 각 handler 메소드의 역할)
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@Override
	public void afterPropertiesSet() {
		log.trace("afterPropertiesSet() invoked.");
		
		Objects.requireNonNull(this.service);
		assert this.service != null;
		
		log.info("\t+ this.service : {}", this.service);
	} // afterPropertiesSet
	
	@GetMapping("/getBoardList")
	void getBoardList(BoardDTO dto, Model model) {
		log.trace("getBoardList({}) invoked.", dto);
		
		Page<Board> page = this.service.findAllBoards(dto);
		
		model.addAttribute(SharedAttributes.CURRENT_PAGE, page);
		
		log.info("\t+1. getTotalPages : {}", page.getTotalPages());
		log.info("\t+2. getTotalElements : {}", page.getTotalElements());
		log.info("\t+3. getNumber : {}", page.getNumber());
		log.info("\t+4. isFirst : {}", page.isFirst());
		log.info("\t+5. isLast : {}", page.isLast());
		log.info("\t+6. hasPrevious : {}", page.hasPrevious());
		log.info("\t+7. hasNext : {}", page.hasNext());
		log.info("\t+8. previousPageable : {}", page.previousPageable());
		log.info("\t+9. previousOrFirstPageable : {}", page.previousOrFirstPageable());
		log.info("\t+10. nextOrLastPageable : {}", page.nextOrLastPageable());
	} // getBoardList
	
	@GetMapping("/registerBoard")
	String registerBoardView() {
		log.trace("registerBoardView() invoked.");
		
		return "board/registerBoardView";
	} // registerBoardView
	
	@PostMapping(value = "/registerBoard", params = { "title", "writer", "content" })
	String registerBoard(BoardDTO dto, RedirectAttributes rttrs) {
		log.trace("registerBoard({}) invoked.", dto);
		
		boolean isRegisterd = this.service.registerBoard(dto);
		
		log.info("\t+ isRegistered : {}", isRegisterd);
		
		rttrs.addAttribute(SharedAttributes.IS_REGISTERED, isRegisterd);
		rttrs.addAttribute(SharedAttributes.CURRENT_PAGE, dto.getCurrPage());
		
		return "redirect:getBoardList";
	} // registerBoard
	
	@GetMapping("/getBoard")
	void getBoard(BoardDTO dto, Model model) {
		log.trace("getBoard() invoked.");
		
		Board foundBoard = this.service.findBoard(dto);
		
		model.addAttribute(SharedAttributes.BOARD, foundBoard);
	} // getBoard
	
	@GetMapping(value = "/updateBoardView")
	String updateBoardView() {
		log.trace("updateBoard() invoked.");
		
		return "board/updateBoardView";
	} // updateBoardView
	
	@PostMapping(value = "/updateBoard")
	String updateBoard(BoardDTO dto, RedirectAttributes rttrs) {
		log.trace("updateBoard({}) invoked.", dto);
		
		boolean isModified = this.service.modifyBoard(dto);
		
		rttrs.addAttribute(SharedAttributes.IS_MODIFIED, isModified);
		rttrs.addAttribute(SharedAttributes.CURRENT_PAGE, dto.getCurrPage());
		
		return "redirect:getBoardList";
	} // updateBoard
	
	@PostMapping("/deleteBoard")
	String deleteBoard(BoardDTO dto, RedirectAttributes rttrs) {
		log.trace("deleteBoard({}) invoked.", dto);
		
		boolean isRemoved = this.service.removeBoard(dto);
		
		rttrs.addAttribute(SharedAttributes.IS_REMOVEWD, isRemoved);
		rttrs.addAttribute(SharedAttributes.CURRENT_PAGE, dto.getCurrPage());
		
		return "redirect:getBoardList";
	} // deleteBoard
	
} // end class


