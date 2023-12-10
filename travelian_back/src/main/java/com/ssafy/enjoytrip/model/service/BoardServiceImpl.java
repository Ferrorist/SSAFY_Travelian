package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.model.dao.BoardDao;
import com.ssafy.enjoytrip.model.dto.Board;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;
	
	@Override
	public List<Board> getList() {
		return boardDao.getList();
	}

	@Override
	public boolean registBoard(Board board) {
		return boardDao.write(board) == 1;
	}

	@Override
	public Board getBoard(String articleNo) {
		return boardDao.getBoard(articleNo);
	}

	@Override
	public List<Board> getBoardByTitle(String title) {
		return boardDao.getBoardByTitle(title);
	}

	@Override
	public List<Board> getBoardByName(String name) {
		return boardDao.getBoardByName(name);
	}

	@Override
	public boolean modify(Board board) {
		return boardDao.modify(board) == 1;
	}

	@Override
	public boolean delete(String articleNo) {
		return boardDao.delete(articleNo) == 1;
	}

	@Override
	public boolean updateHit(String articleNo) {
		return boardDao.updateHit(articleNo) == 1;
	}
	
}
