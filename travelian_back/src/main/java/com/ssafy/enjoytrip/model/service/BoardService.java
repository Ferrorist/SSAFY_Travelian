package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.model.dto.Board;

public interface BoardService {
	List<Board> getList();
	
	boolean registBoard(Board board);
	
	Board getBoard(String articleNo);

	List<Board> getBoardByTitle(String title);

	List<Board> getBoardByName(String name);

	boolean modify(Board board);

	boolean delete(String articleNo);
	
	boolean updateHit(String articleNo);
}
