package com.oss.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.oss.model.Chick;
@Mapper
public interface ChickMapper {
	
	List<Chick> getAll();
	
	Chick getOne(int weight);
	
	void insert(Chick chick);
	
	int update(Chick chick);
	
	void delete(int weight);


}
