package com.oss.services;

import com.codingapi.tx.annotation.TxTransaction;
import com.oss.model.Chick;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/8/3.
 */
public interface IChickService {
	List<Chick> getChicks();

	Chick getChick(int weight);

	void save(Chick chick);

	int update(Chick chick);

	void delete(int weight);
}
