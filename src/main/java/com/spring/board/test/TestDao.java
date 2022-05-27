package com.spring.board.test;

import java.util.List;

public interface TestDao {
	List<TestVo> select();
	TestVo content(TestVo vo); //수정,삭제를 위한 pk 가져오기
	
	void insert(TestVo vo);
	void delete(TestVo vo);
}
