package com.java.classroom.dao;

import com.java.classroom.bean.Xiaoqu;
import com.java.classroom.bean.XiaoquExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XiaoquMapper {
	int countByExample(XiaoquExample example);

	int deleteByExample(XiaoquExample example);

	int deleteByPrimaryKey(Integer id);

	int deleteByIds(String delIds);

	int insert(Xiaoqu record);

	int insertSelective(Xiaoqu record);

	List<Xiaoqu> selectByExample(XiaoquExample example);

	Xiaoqu selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Xiaoqu record,
			@Param("example") XiaoquExample example);

	int updateByExample(@Param("record") Xiaoqu record,
			@Param("example") XiaoquExample example);

	int updateByPrimaryKeySelective(Xiaoqu record);

	int updateByPrimaryKey(Xiaoqu record);
}