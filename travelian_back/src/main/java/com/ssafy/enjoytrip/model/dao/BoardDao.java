package com.ssafy.enjoytrip.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.model.dto.Board;

@Mapper
public interface BoardDao {

	List<Board> getList();

	int write(Board board);

	Board getBoard(String articleNo);

	List<Board> getBoardByTitle(String title);

	List<Board> getBoardByName(String name);

	int modify(Board board);

	int delete(String articleNo);

	int updateHit(String articleNo);
}
