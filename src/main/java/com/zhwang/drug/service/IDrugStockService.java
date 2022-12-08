package com.zhwang.drug.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import com.zhwang.drug.entity.DrugStock;
import com.zhwang.drug.entity.DrugStockFindAll;
import com.zhwang.drug.service.exception.InsertException;

/**
 * 进货业务层接口
 * @author mmt
 *
 */
public interface IDrugStockService {
	
	/**
	 * 修改数据
	 * @param drugStock 需要修改的数据
	 * @return 返回受影响的行数
	 * @throws InsertException 数据操作异常
	 */
	Integer modificationDrugStock(DrugStock drugStock) throws InsertException;
	
	/**
	 * 删除某条数据
	 * @param ids 需要删除的id字符串
	 * @param session session获取登入名
	 */
	void deleteDrugStock(String ids,HttpSession session);
	
	/**
	 * 查询所有的进货数据
	 * @return
	 */
	List<DrugStockFindAll> findDrugStock(Integer pageNoStr, Integer pageSizeStr, String documentNo);
	
	/**
	 * 添加进货清单
	 * @param drugstock
	 * @return
	 */
	Integer addDrugStock(DrugStock drugStock,HttpSession session)throws InsertException;
	
	/**
	 * 查询供货商表,员工表,药品表所有的id与姓名
	 * @return 查询到的id
	 */
	List<List<Map<Integer, String>>> findDrgEmpSupId();

}