package com.spring.board.test;

import java.util.List;

public interface TestService {
	List<TestVo> select();
	TestVo content(TestVo vo);
	
	void insert(TestVo vo);
	void delete(TestVo vo);
}
