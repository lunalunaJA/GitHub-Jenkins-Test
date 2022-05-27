package com.spring.board.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao dao;

	@Override
	public List<TestVo> select() {
		return dao.select();
	}

	@Override
	public void insert(TestVo vo) {
		dao.insert(vo);
	}

	@Override
	public void delete(TestVo vo) {
		dao.delete(vo);
	}

	@Override
	public TestVo content(TestVo vo) {
		return dao.content(vo);
	}
	
}
