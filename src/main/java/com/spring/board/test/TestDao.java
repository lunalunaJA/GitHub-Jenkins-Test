package com.spring.board.test;

import java.util.List;

public interface TestDao {
	List<TestVo> select();
	TestVo content(TestVo vo); //����,������ ���� pk ��������
	
	void insert(TestVo vo);
	void delete(TestVo vo);
}
