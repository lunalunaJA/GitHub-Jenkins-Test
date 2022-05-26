package com.spring.board.test;

import java.util.List;

public interface TestService {
	List<TestVo> select();
	void insert(TestVo vo);
}
