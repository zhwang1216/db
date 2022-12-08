package com.zhwang.drug.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zhwang.drug.entity.Drug;
import com.zhwang.drug.entity.domain.PaginationVO;
import com.zhwang.drug.service.IDrugService;
import com.zhwang.drug.service.ILogOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhwang.drug.dao.DrugDao;
import com.zhwang.drug.entity.DrugANDDrugCategory;
import com.zhwang.drug.service.exception.BarCodeDuplicateException;
import com.zhwang.drug.service.exception.DeleteException;
import com.zhwang.drug.service.exception.ForeignKeyReferenceException;
import com.zhwang.drug.service.exception.InsertException;
import com.zhwang.drug.service.exception.UpdateException;
/**
 * 药品，业务层，实现类
 * @author PHP
 *
 */
@Service
public class DrugServiceImpl implements IDrugService {
	@Autowired private DrugDao drugdao;
	
	ILogOperationService logOPerationServiceImpl = new LogOperationServiceImpl();
	
	/**
	 * 添加药品类别
	 * @param drug
	 * @param username
	 * @throws BarCodeDuplicateException
	 * @throws InsertException
	 */
	@Override
	public void addDrug(Drug drug, String username) throws BarCodeDuplicateException, InsertException {
		String barCode = drug.getBarCode();
		Drug resultBarCode = findByBarCode(barCode);
		if( resultBarCode != null ) {
			throw new BarCodeDuplicateException("你添加的药品,条形码（"+barCode+"）已经被使用");
		}
		//判断药品信息是否存在
		
		//设置is_delete
		drug.setIsDelete(0);
		//设置四项日志
		if( drug != null ) {	//杜绝Null字符串一场
			Date time = new Date();
			drug.setCreatedUser(username);
			drug.setCreatedTime(time);
			drug.setModifiedUser(username);
			drug.setModifiedTime(time);
		}
		insertDrug(drug);
	}
	
	/**
	 * 查询药品数据（关联查询）药品类别表
	 * @return
	 */
	public PaginationVO<DrugANDDrugCategory> getselectDrug(Map<String,Object> map){
		List<DrugANDDrugCategory> list = selectDrug(map);
		long count = selectCountDrug(map);
		PaginationVO<DrugANDDrugCategory> VO = new PaginationVO<DrugANDDrugCategory>();
		//把List和long封装成VO
		VO.setCount(count);
		VO.setDataList(list);
		return VO;
	};
	
	/**
	 * 根据uid查询药品全部数据
	 * @param uid
	 * @return
	 */
	public Drug getfindId(Integer id){
		return findId(id);
	};
	
	/**
	 * 修改药品数据
	 * @param drug
	 * @return
	 */
	public void getupdateIdDrug(Drug drug,String username){
		//设置四项日志
		if( drug != null ) {	//杜绝Null字符串一场
			Date time = new Date();
			drug.setModifiedUser(username);
			drug.setModifiedTime(time);
		}
		updateIdDrug(drug);
	};
	
	/**
	 * 根据id删除药品
	 * @param id
	 * @return 
	 */
	public void getdeleteIdDrug(String[] ids,String username)
		throws ForeignKeyReferenceException,DeleteException{
			//判断是否可以删除数据
			String str = "";
			String normalId = "";
			for (String string : ids) {
				Integer count = deleteDrugId( Integer.parseInt(string) );
				if( count != 0 ){
					Drug drug = findId(Integer.parseInt(string));
					str += drug.getDrugName()+",";
				}else{
					normalId+=string+",";
				}
			}	
			if( str.trim().length() != 0 ){
				throw new ForeignKeyReferenceException("（"+str+"）药品,有数据引用，不能删除");
			}
			String[] stringUid = normalId.split(",");
			for(String id:stringUid){
				//设置is_delete
				Integer isDelete = 1;
				String modifiedUser = username;
				Date modifiedTime = new Date();
				deleteIdDrug(Integer.parseInt(id),isDelete,modifiedUser,modifiedTime);
			}
	};
	
	/**
	 * 查询药品数量
	 * @return
	 */
	public Long getselectIdCount(){
		return selectIdCount();
	};
	
	/**
	 * 查询数据导出
	 * @return
	 */
	public List<DrugANDDrugCategory> findselectIsdelete(Map<String,Object> map){
		return selectIsdelete(map);
	};
	
	
	
	/***************************************************************************************************************/
	
	/**
	 * 查询数据导出
	 * @return
	 */
	private List<DrugANDDrugCategory> selectIsdelete(Map<String,Object> map){
		return drugdao.findselectIsdelete(map);
	};
	
	/**
	 * 查询药品数量
	 * @return
	 */
	private Long selectIdCount(){
		return drugdao.selectIdCount();
	};
	
	/**
	 * 修改药品数据
	 * @param drug
	 * @return
	 */
	private void updateIdDrug(Drug drug){
		Integer count = drugdao.updateIdDrug(drug);
		if( count != 1 ) {
			throw new UpdateException("修改数据时出现未知错误！");
		}
	};
	
	/**
	 * 根据uid查询药品全部数据
	 * @param uid
	 * @return
	 */
	private Drug findId(Integer id){
		return drugdao.findId(id);
	};
	
	/**
	 * 查询药品所有数据
	 * @return
	 */
	private List<DrugANDDrugCategory> selectDrug(Map<String,Object> map){
		return drugdao.selectDrug(map);
	};
	
	/**
	 * 查询药品数量
	 * @param map
	 * @return
	 */
	private long selectCountDrug(Map<String,Object> map){
		Long count = drugdao.selectCountDrug(map);
		return count;
	};
	
	/**
	 * 根据条形码查询数据
	 * @param barCode
	 * @return
	 */
	private Drug findByBarCode(String barCode) {
		return drugdao.findByBarCode(barCode);
	};
	
	/**
	 * 判断该条数据是否被引用
	 * @param uid
	 * @return
	 */
	private Integer deleteDrugId(Integer uid){
		return drugdao.deleteDrugId(uid);
	};
	
	/**
	 * 添加药品
	 * @param drug
	 * @return
	 */
	private void insertDrug(Drug drug) {
		Integer count = drugdao.insertDrug(drug);
		if( count < 1 ) {
			throw new InsertException("插入数据时出现未知错误！");
		}
	};
	
	/**
	 * 删除药品
	 * @param id
	 * @return
	 */
	private void deleteIdDrug(Integer id,Integer isDelete, String modifiedUser,Date modifiedTime){
		Integer count = drugdao.deleteIdDrug(id,isDelete,modifiedUser,modifiedTime);
		if( count < 1 ) {
			throw new DeleteException("删除数据时出现未知错误！");
		}
	};
	
	
	
	
	
	
}
