package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.BoardDao;
import com.example.demo.entity.Board;

// Junit 은 단위 (unit) 테스트 도구 = 메소드의 동작을 확인


@SpringBootTest
public class BoardDaoTest {
	@Autowired
	private BoardDao boardDao;
	
	// Test 케이스 1. dao 생성 -> null 이아님
	
	@Test
	public void initTest() {
		assertNotNull(boardDao);
		
	}
	
	// insert , delete , update 의 실행 결과는 변경된 행의 개수
	// Test 케이스 2 . save : Board -> 결과값이 1 이다
	
	@Transactional
	//@Test
	public void saveTest() {
		Board board = Board.builder().title("xxx").content("yyyy").writer("zzz").build();
		assertEquals(1, boardDao.save(board));
	}
	
	
	// Test 케이스 3. count : 개수를 수동으로 확인해서 assert 한다
	//@Test
	public void countTest() {
		assertEquals(10 , boardDao.count(null));	
	}
	
	
	// Test 케이스 4. findAll : 글이 8개 있다 .
	//@Test
	public void findAllTest() {
		assertEquals(8, boardDao.findAll(null, 1, 8).size());
	}
		
	
	// Test 케이스 5 . (내용,제목),조회수 좋아요, 싫어요에 대해 값을 주면 update -> 결과가 0 이 아니다아
	//@Transactional	
	//@Test
	public void updateTest() {
		assertNotEquals(0,boardDao.update(Board.builder().bno(1).readCnt(1).build()));
		assertNotEquals(0,boardDao.update(Board.builder().bno(1).goodCnt(1).build()));
		assertNotEquals(0,boardDao.update(Board.builder().bno(1).commentCnt(15).build()));
		assertNotEquals(0,boardDao.update(Board.builder().bno(1).title("변경").content("변경").build()));
	}
		
	
	
	// 바람직하지 않은 결과도 테스트해야 한다
	//Test 케이스 6. 1번글을 읽으면 비어있다 , 2번글을 읽으면 존재한다
	//@Test
	public void findByIdTest() {
		assertEquals(true, boardDao.findById(1).isEmpty());
		assertEquals(true, boardDao.findById(2).isPresent());
	}
	
	
	//Test 케이스 7 . 1번글이없다, 2번글의 글쓴이는 있다
	//@Test
	public void findWriterTest() {
		assertEquals(true, boardDao.findWriterById(1).isEmpty());
		assertEquals(true, boardDao.findWriterById(2).isPresent());
	
	}
	
	@Transactional
	//@Test
	public void deleteByIdTest() {
	assertEquals(0, boardDao.deleteById(1));
	assertEquals(1, boardDao.deleteById(2));
	}
}




















