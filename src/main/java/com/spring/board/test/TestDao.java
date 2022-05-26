package com.spring.board.test;

import java.util.List;

public interface TestDao {
	List<TestVo> select();
	void insert(TestVo vo);
}
