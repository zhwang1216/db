package com.zhwang.drug.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zhwang.drug.entity.Drug;
import com.zhwang.drug.entity.DrugSales;
import com.zhwang.drug.entity.DrugSalesANDCustomer;
import com.zhwang.drug.entity.domain.PaginationVO;
import com.zhwang.drug.service.IDrugSalesService;
import com.zhwang.drug.service.ILogOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhwang.drug.dao.DrugDao;
import com.zhwang.drug.dao.DrugSalesDao;
import com.zhwang.drug.service.exception.InsertException;
import com.zhwang.drug.service.exception.InventoryException;
import com.zhwang.drug.service.exception.InventoryFoundException;

@Service
public class DrugSalesServiceImpl implements IDrugSalesService {
	
	@Autowired
	private DrugSalesDao drugSalesDao;//销售表持久层
	
	@Autowired
	private DrugDao drugDao;//药品表持久层
	
	ILogOperationService logOPerationServiceImpl = new LogOperationServiceImpl();
	
	/**
	 * 添加药品购买信息
	 * @param operation 日志
	 */
	public void addDrugSales(String[] ids, DrugSales drugSales, String username)
			throws InsertException,InventoryFoundException,InventoryException{
		for (String id : ids) {
			Drug drug = drugDao.findId(Integer.parseInt(id));
			//判断库存是否大于购买数量
			if( drug.getInventory() < drugSales.getInventoryQuantity() ) {	
				throw new InventoryFoundException("购买失败，库存数量不足");
			}
		}
		//修改药品库存
		for (String id : ids) {
			Drug drug = drugDao.findId(Integer.parseInt(id));
			Integer countDrug = drug.getInventory(); //查询药品库存
			Integer countSales = drugSales.getInventoryQuantity();	//查询购买的数量
			Integer countTotalSales = drug.getTotalSales();	//药品销售总量
			updateByIdInventory( Integer.parseInt(id),(countDrug-countSales) ,(countTotalSales+countSales));
		}
		//添加销售信息
		for (String id : ids) {
			//查询药品相应数据
			Drug drug = drugDao.findId(Integer.parseInt(id));
			Double price = drug.getSalesPrice();	//销售单价
			drugSales.setInventory( (double)drugSales.getInventoryQuantity()*price ); //销售金额
			drugSales.setDocumentNo( number(drug,drugSales) );	//销售单据号
			drugSales.setDrugName( drug.getDrugName() );	//添加药品名称
			drugSales.setPrice(price);				//添加购买单价
			drugSales.setStorageTime(new Date());	//添加购买时间
			//设置is_delete
			drugSales.setIsDelete(0);
			//设置四项日志
			if( drugSales != null ) {	//杜绝Null字符串一场
				Date time = new Date();
				drugSales.setCreatedUser(username);
				drugSales.setCreatedTime(time);
				drugSales.setModifiedUser(username);
				drugSales.setModifiedTime(time);
			}
			insertDrugSales(drugSales);
		}
	};
	
	/**
	 * 根据条件查询所有数据
	 */
	public PaginationVO<DrugSales> getSelectDrugSales(Map<String,Object> map){
		//查询信息
		List<DrugSales> list = selectDrugSalesPage(map);
		//查询数量
		long count = selectDrugSalesCount(map);
		PaginationVO<DrugSales> VO = new PaginationVO<DrugSales>();
		//把List和long封装成VO
		VO.setCount(count);
		VO.setDataList(list);
		return VO;
	};
	
	/**
	 * 根据条件查询所有数据,账单查询
	 */
	public PaginationVO<DrugSalesANDCustomer> getfindDrugSales(Map<String,Object> map){
		//查询信息
		List<DrugSalesANDCustomer> list = getselectDrugSalesPage(map);
		//查询数量
		long count = getselectDrugSalesCount(map);
		PaginationVO<DrugSalesANDCustomer> VO = new PaginationVO<DrugSalesANDCustomer>();
		//把List和long封装成VO
		VO.setCount(count);
		VO.setDataList(list);
		return VO;
	};
	
	/**
	 * 查询销售数量
	 * @return
	 */
	public Long getselectIdCount(){
		return selectIdCount();
	};
	
	
	
	
	
	
	/*****************************************************************************************************************/
	
	
	
	/**
	 * 查询销售数量
	 * @return
	 */
	private Long selectIdCount(){
		return drugSalesDao.selectIdCount();
	};
	
	/**
	 * 添加药品购买信息
	 * @param drugSalse
	 * @return
	 */
	private void insertDrugSales(DrugSales drugSales){
		Integer count = drugSalesDao.insertDrugSales(drugSales);
		if( count != 1 ){
			throw new InsertException("插入数据异常");
		}
	};
	
	/**
	 * 根据客户id查询以购买的药品信息
	 * @param customerId
	 * @return
	 */
	private List<DrugSales> selectDrugSalesPage(Map<String,Object> map){
		return drugSalesDao.selectDrugSalesPage(map);
	};
	
	/**
	 * 根据条件，查询信息数量
	 */
	private Long selectDrugSalesCount(Map<String,Object> map){
		return drugSalesDao.selectDrugSalesCount(map);
	};

	/**
	 * 销售单据号
	 * @return
	 */
	private String number(Drug drug,DrugSales drugSales){
		String str = "";
		String barCord = drug.getBarCode();	//条形码
		Integer inv = drugSales.getInventoryQuantity();	//购买数量
		Integer id = drugSales.getCustomerId();		//客户id
		str = barCord+String.valueOf(inv)+String.valueOf(id);
		return str;
	};
	
	/**
	 * 根据id修改库存
	 * @param id
	 * @return
	 */
	private void updateByIdInventory(Integer id,Integer inventory,Integer totalSales){
		Integer count = drugDao.updateByIdInventory(id,inventory,totalSales);
		if( count != 1 ){
			throw new InventoryException("购买出现数据异常");
		}
	};
	
	/**
	 * 条件查询以购买的药品信息
	 * @param customerId
	 * @return
	 */
	private List<DrugSalesANDCustomer> getselectDrugSalesPage(Map<String,Object> map){
		return drugSalesDao.getselectDrugSalesPage(map);
	};
	
	/**
	 * 根据条件，查询信息数量
	 */
	private Long getselectDrugSalesCount(Map<String,Object> map){
		return drugSalesDao.getselectDrugSalesCount(map);
	};
}