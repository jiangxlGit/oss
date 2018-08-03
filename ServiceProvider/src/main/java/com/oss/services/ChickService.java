package com.oss.services;

import java.util.List;

import com.codingapi.tx.annotation.ITxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingapi.tx.annotation.TxTransaction;
import com.oss.mapper.ChickMapper;
import com.oss.model.Chick;

@Service
public class ChickService implements IChickService, ITxTransaction {
	@Autowired
	private ChickMapper chickMapper;
	@Override
	public List<Chick> getChicks() {
		List<Chick> chicks = chickMapper.getAll();
		return chicks;
	}

	@Override
	public Chick getChick(int weight) {
		Chick chick = chickMapper.getOne(weight);
		return chick;
	}

	@Override
	public void save(Chick chick) {
		chickMapper.insert(chick);
	}
	@Override
	@TxTransaction
	@Transactional
	public int update(Chick chick) {
		System.out.println("------------+++++++");
		return chickMapper.update(chick);
	}

	@Override
	public void delete(int weight) {
		chickMapper.delete(weight);
	}

}
