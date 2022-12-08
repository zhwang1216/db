package com.zhwang.drug.service;

import java.util.List;
import java.util.Map;

import com.zhwang.drug.entity.Drug;
import com.zhwang.drug.entity.DrugANDDrugCategory;
import com.zhwang.drug.entity.domain.PaginationVO;
import com.zhwang.drug.service.exception.BarCodeDuplicateException;
import com.zhwang.drug.service.exception.InsertException;

/**
 * 药品，业务层，接口
 * @author PHP
 *
 */
public interface IDrugService {
	/**
	 * 添加药品类别
	 * @param drug
	 * @param username
	 * @throws BarCodeDuplicateException
	 * @throws InsertException
	 */
	void addDrug(Drug drug, String username) throws BarCodeDuplicateException,InsertException;
	
	/**
	 * 查询药品数据（关联查询）药品类别表
	 * @return
	 */
	PaginationVO<DrugANDDrugCategory> getselectDrug(Map<String,Object> map);
	
	/**
	 * 根据uid查询药品全部数据
	 * @param uid
	 * @return
	 */
	Drug getfindId(Integer id);
	
	/**
	 * 修改药品数据
	 * @param drug
	 * @return
	 */
	void getupdateIdDrug(Drug drug,String username);
	
	/**
	 * 删除药品
	 * @param id
	 * @return 
	 */
	void getdeleteIdDrug(String[] ids,String username);
	
	/**
	 * 查询药品数量
	 * @return
	 */
	Long getselectIdCount();
	
	/**
	 * 查询药品所有数据
	 * @return
	 */
	List<DrugANDDrugCategory> findselectIsdelete(Map<String,Object> map);
}
