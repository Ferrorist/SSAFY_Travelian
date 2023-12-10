package com.ssafy.enjoytrip.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.service.BoardService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/board")
@CrossOrigin("*")
public class RestBoardController {
	private static final Logger logger = LoggerFactory.getLogger(RestBoardController.class);

	@Autowired
	private BoardService boardService;

	@ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<?> listBoard() {
		try {
			List<Board> list = boardService.getList();
			return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "게시글을 등록한다.")
	@PostMapping
	public ResponseEntity<?> createBoard(@RequestBody Board board) {
		try {
			boolean writeBoard = boardService.registBoard(board);

			if (writeBoard)
				return new ResponseEntity<Void>(HttpStatus.OK);

			else
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "입력받은 글번호에 해당하는 게시글을 반환한다.", response = Board.class)
	@GetMapping("/articleNo/{articleNo}")
	public ResponseEntity<?> getBoardByArticleNo(@PathVariable("articleNo") String articleNo) {
		try {
			Board board = boardService.getBoard(articleNo);

			if (board != null)
				return new ResponseEntity<Board>(board, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "입력받은 글제목을 포함하는 게시글을 반환한다.", response = Board.class)
	@GetMapping("/title/{title}")
	public ResponseEntity<?> getBoardByTitle(@PathVariable("title") String title) {
		try {
			List<Board> list = boardService.getBoardByTitle(title);

			if (list == null || list.size() == 0) {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			} else
				return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "입력받은 이름을 가진 사용자가 작성한 게시글을 반환한다.", response = Board.class)
	@GetMapping("/name/{name}")
	public ResponseEntity<?> getBoardByName(@PathVariable("name") String name) {
		try {
			List<Board> list = boardService.getBoardByName(name);

			if (list == null || list.size() == 0) {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			} else
				return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다.")
	@PutMapping("/{articleNo}")
	public ResponseEntity<?> modifyBoard(@PathVariable("articleNo") String articleNo, @RequestBody Board board) {
		try {
			board.setArticleNo(articleNo);
			boolean modify = boardService.modify(board);

			if (modify)
				return new ResponseEntity<Void>(HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "글번호에 해당하는 게시글을 삭제한다.")
	@DeleteMapping("/{articleNo}")
	public ResponseEntity<?> deleteBoard(@PathVariable("articleNo") String articleNo){
		try {
			boolean delete = boardService.delete(articleNo);
			
			if(delete)
				return new ResponseEntity<Void>(HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "글번호에 해당하는 게시글의 조회 수를 1 증가시킨다.")
	@PutMapping("/{articleNo}/hit")
	public ResponseEntity<?> updateHit(@PathVariable("articleNo") String articleNo){
		try {
			boolean update_hit = boardService.updateHit(articleNo);
			
			if(update_hit)
				return new ResponseEntity<Void>(HttpStatus.OK);
			
			else
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
