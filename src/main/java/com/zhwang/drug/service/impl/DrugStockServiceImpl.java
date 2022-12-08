package com.zhwang.drug.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import com.zhwang.drug.entity.Drug;
import com.zhwang.drug.entity.DrugStock;
import com.zhwang.drug.entity.DrugStockFindAll;
import com.zhwang.drug.entity.Supplier;
import com.zhwang.drug.service.IDrugStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhwang.drug.dao.DrugDao;
import com.zhwang.drug.dao.DrugStockDao;
import com.zhwang.drug.dao.EmployeesDao;
import com.zhwang.drug.dao.SupplierDao;
import com.zhwang.drug.entity.Employees;
import com.zhwang.drug.service.exception.InsertException;
import com.zhwang.drug.service.exception.OnExistException;

/**
 * 进货业务层实现类
 * @author mmt
 *
 */
@Service
public class DrugStockServiceImpl implements IDrugStockService {

	@Autowired
	private DrugStockDao drugstock;//进货表持久层
	
	@Autowired
	private DrugDao drugDao;//药品表持久层
	
	@Autowired
	private SupplierDao supplier;//供货商持久层
	
	@Autowired
	private EmployeesDao employees;//员工持久层
	
	/**
	 * 修改数据
	 */
	@Override
	public Integer modificationDrugStock(DrugStock drugStock) throws InsertException{
		Integer upd = updateDrugStock(drugStock);
		return upd;
	}
	
	/**
	 * 添加进货信息
	 */
	@Override
	public Integer addDrugStock(DrugStock drugStock , HttpSession session)throws InsertException,OnExistException{
		//先根据入库单据号查询数据库是否有这条数据
		String documentNo = drugStock.getDocumentNo();
		if(findBydocumentNo(documentNo) != 0){
			throw new OnExistException("入库单据号已存在!!!");
		}
		//因为前段穿过来的数据不完整,需要在后台补上完整的信息(是否删除,创建人,创建时间,修改人,修改时间)
		drugStock.setIsDelete(0);
		//创建人需要登入后在session中获取
		drugStock.setCreatedUser(session.getAttribute("username").toString());
		drugStock.setCreatedTime(new Date());
		drugStock.setModifiedUser(session.getAttribute("username").toString());
		drugStock.setModifiedTime(new Date());
		Integer upd = insertDrugStock(drugStock);
		return upd;
	}

	/**
	 * 查询进货表中所有的信息
	 */
	@Override
	public List<DrugStockFindAll> findDrugStock(Integer pageNoStr, Integer pageSizeStr, String documentNo) {
		List<DrugStockFindAll> drugStocks = findAll(pageNoStr,pageSizeStr,documentNo);
		Integer del = 1;
		List<DrugStockFindAll> allDrugStock = new ArrayList<>();
		for(DrugStockFindAll d : drugStocks){
			if(!del.equals(d.getIsDelete())){
				d.setIsDelete(null);
				allDrugStock.add(d);
			}
		}
		return allDrugStock;
	}

	/**
	 * 删除某条数据
	 */
	@Override
	public void deleteDrugStock(String ids,HttpSession session) {
		String[] id = ids.split(",");
		String username = session.getAttribute("username").toString();
		Date now = new Date();
		for(int i=0;i<id.length;i++){
			System.err.println(Integer.parseInt(id[i]));
			Integer upd = updateIsDelete(Integer.parseInt(id[i]),username,now);
			if(upd!=1){
				throw new InsertException("删除数据时出现未知错误!!");
			}
		}
	}
	
	/**
	 * 在员工表和供货商表药品表中查询所有存在的id,名字,并且返回出去
	 */
	@Override
	public List<List<Map<Integer, String>>> findDrgEmpSupId() {
		List<List<Map<Integer, String>>> UAN = new ArrayList<List<Map<Integer, String>>>();
		List<Map<Integer, String>> drgId = new ArrayList<Map<Integer, String>>();
		List<Map<Integer, String>> supId = new ArrayList<Map<Integer, String>>();
		List<Map<Integer, String>> empId = new ArrayList<Map<Integer, String>>();
		Integer count = 1;
		
		List<Drug> drg = findDrgByUidUsername();
		for(Drug d : drg){
			if(!count.equals(d.getIsDelete())){
				Map<Integer, String> drgmap = new HashMap<Integer, String>();
				drgmap.put(d.getId(), d.getDrugName());
				drgId.add(drgmap);
			}
		}
		UAN.add(drgId);
		
		List<Supplier> sup = findSupByUidUsername();
		for(Supplier s : sup){
			if(!count.equals(s.getIsDelete())){
				Map<Integer, String> supmap = new HashMap<Integer, String>();
				supmap.put(s.getUid(), s.getUsername());
				supId.add(supmap);
			}
		}
		UAN.add(supId);
		
		List<Employees> emp = findEmpByUidUsername();
		for(Employees e : emp){
			if(!count.equals(e.getIsDelete())){
				Map<Integer, String> empmap = new HashMap<Integer, String>();
				empmap.put(e.getUid(), e.getUsername());
				empId.add(empmap);
			}
		}
		UAN.add(empId);
		return UAN;
	}
	
	/**
	 * 查询员工表uid，username
	 * @return
	 */
	private List<Employees> findEmpByUidUsername(){
		List<Employees> list = employees.findByUidUsername();
		return list;
	}
	
	/**
	 * 查询药品里面所有的id以及药品名字
	 * @return
	 */
	private List<Drug> findDrgByUidUsername(){
		List<Drug> list = drugDao.findUidName();
		return list;
	}
	
	/**
	 * 查询供货商表的uid,username
	 * @return
	 */
	private List<Supplier> findSupByUidUsername(){
		//测试正常
		List<Supplier> list = supplier.findByUidUsername();
		return list;
	}
	
	/**
	 * 添加进货清单
	 * @param drugstock
	 * @return
	 */
	private Integer insertDrugStock(DrugStock drugStock){
		Integer upd = drugstock.insertDrugStock(drugStock);
		if(upd == 0){
			throw new InsertException("插入数据错误!!");
		}
		return upd;
	}
	
	/**
	 * 根据入库单号查询是否有重复数据
	 * @param documentNo 单号
	 * @return 返回受影响的条数
	 */
	private Integer findBydocumentNo(String documentNo){
		return drugstock.findBydocumentNo(documentNo);
	}

	/**
	 * 查询所有进货数据
	 * @return 返回查询到的数据
	 */
	private List<DrugStockFindAll> findAll(Integer pageNoStr,Integer pageSizeStr,String documentNo){
		return drugstock.findAll(pageNoStr,pageSizeStr,documentNo);
	}
	
	/**
	 * 删除某条数据(设置isDelete为1)
	 * @param id 需要删除的数据的id
	 * @return 返回受影响的行数
	 */
	private Integer updateIsDelete(Integer id,String modifiedUser,Date modifiedTime){
		Integer upd = drugstock.updateIsDelete(id,modifiedUser,modifiedTime);
		return upd;
	}
	
	/**
	 * 修改进货数据
	 * @return 受影响的行数
	 */
	private Integer updateDrugStock(DrugStock drug){
		Integer upd = drugstock.updateDrugStock(drug);
		if(upd!=1){
			throw new InsertException("修改数据异常!!!");
		}
		return upd;
	}

}