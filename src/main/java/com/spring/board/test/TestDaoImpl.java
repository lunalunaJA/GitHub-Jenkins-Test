package com.spring.board.test;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl implements TestDao{

	@Autowired
	private SqlSessionTemplate mybatis;

	@Override
	public List<TestVo> select() {
		return mybatis.selectList("Board.Select");
	}

	@Override
	public void insert(TestVo vo) {
		mybatis.insert("Board.Insert", vo);
	}

	@Override
	public void delete(TestVo vo) {
		mybatis.delete("Board.Delete", vo);
	}

	@Override
	public TestVo content(TestVo vo) {
		return mybatis.selectOne("Board.Content", vo);
	}
}
