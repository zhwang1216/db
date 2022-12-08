package com.zhwang.drug.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zhwang.drug.entity.Supplier;
import com.zhwang.drug.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhwang.drug.dao.SupplierDao;
import com.zhwang.drug.entity.domain.PaginationVO;
import com.zhwang.drug.service.exception.DeleteException;
import com.zhwang.drug.service.exception.EmailDuplicateException;
import com.zhwang.drug.service.exception.ForeignKeyReferenceException;
import com.zhwang.drug.service.exception.InsertException;
import com.zhwang.drug.service.exception.PhoneDuplicateException;
import com.zhwang.drug.service.exception.SupplierNotFoundException;
import com.zhwang.drug.service.exception.UpdateException;

@Service
public class SupplierServiceImpl implements ISupplierService {
	
	@Autowired
	private SupplierDao supplierDao;
	
	@Override
	public void addnew(Supplier supplier) throws InsertException,PhoneDuplicateException,EmailDuplicateException {
		String phone = supplier.getPhone();
		Supplier resultPhone = findByPhone(phone);
		String email = supplier.getEmail();
		Supplier resultEmail = findByEmail(email);
		
		////检查手机号是否被占用:如果结果不为空表示已经被占用.如果结果为空表示没有被占用
		if(resultPhone!=null){
			throw new PhoneDuplicateException("添加失败,电话号码已被注册");
		}
		
		if(resultEmail!=null){
			throw new EmailDuplicateException("添加失败,邮箱已被注册");
		}
		
		supplier.setIsDelete(0);
		Date now = new Date();
		supplier.setCreatedUser(supplier.getUsername()); 
		supplier.setCreatedTime(now);
		supplier.setModifiedUser(supplier.getUsername());
		supplier.setModifiedTime(now);
	
		insert(supplier);
	}
	
	/**
	 * 修改供应商信息
	 */
	@Override
	public void changeInfo(Supplier supplier) throws SupplierNotFoundException, UpdateException {
		Supplier result = findByUid(supplier.getUid());
		if(result==null){
			throw new SupplierNotFoundException("修改失败!尝试修改的供应商不存在!");
		}
		//用户已经被删除
		if(result.getIsDelete().equals(1)){
			throw new UpdateException("修改失败!尝试修改的供应商不存在!");
		}
		String supplierName = result.getUsername();
		supplier.setModifiedUser(supplierName);
		Date modifiedTime = new Date();
		supplier.setModifiedTime(modifiedTime);
		
		updateInfo(supplier);
		
	}
	
	/**
	 * 查询供应商信息
	 */
	@Override
	public Supplier getByUid(Integer uid) {
		Supplier result = supplierDao.findByUid(uid);
		//判断查询结果是否为空
		if(result==null){
			throw new SupplierNotFoundException("获取用户信息失败!尝试访问的供应商不存在!");
		}
		//供应商已经被删除
		if(result.getIsDelete().equals(1)){
			throw new SupplierNotFoundException("获取供应商信息失败!尝试访问的供应商不存在!");
		}
		
		return result;
	}

	/**
	 * 查询所有供应商信息
	 */
	@Override
	public List<Supplier> selectAll() {
		return findAll();
	}
	
	/**
	 * 删除供应商(假删除,修改isDelete)
	 */
	@Override
	public void changeIsDelet(String[] uids,String username) 
			throws ForeignKeyReferenceException,DeleteException{
		//判断是否可以删除数据
		String str = "";
		String normalId = "";
		for (String string : uids) {
			Integer count = deleteSupplierId( Integer.parseInt(string) );
			if( count != 0 ){
				Supplier supplier = findByUid(Integer.parseInt(string));
//						System.err.println( drugCategory.getCategoryName() );
				str += supplier.getUsername()+",";
			}else{
				normalId+=string+",";
			}
		}	
		if( str.trim().length() != 0 ){
			throw new ForeignKeyReferenceException("（"+str+"）供应商,有数据引用，不能删除");
		}
		String[] stringUid = normalId.split(",");
		for(String uid:stringUid){
			updateIsDelete(Integer.valueOf(uid), username,new Date());
		}
		
	}
	
	@Override
	public PaginationVO<Supplier> getSelectSupplier(Map<String, Object> map) {
		List<Supplier> list = selectSupplierForPage(map);
		long count = selectCountSupplier(map);
		PaginationVO<Supplier> VO = new PaginationVO<Supplier>();
		//把List和long封装成VO
		VO.setCount(count);
		VO.setDataList(list);
		return VO;
	}

	/**
	 * 查询供货商数量
	 * @return
	 */
	public Long getselectIdCount(){
		return selectIdCount();
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*******************************************************************************************************/
	
	/**
	 * 查询供货商数量
	 * @return
	 */
	private Long selectIdCount(){
		return supplierDao.selectIdCount();
	};
	
	private void insert(Supplier supplier){
		Integer rows = supplierDao.insert(supplier);
		if(rows != 1){
			throw new InsertException("插入供应商数据时出现未知异常");
		}
	}
	
	private Supplier findByPhone(String phone){
		return supplierDao.findByPhone(phone);
	}
	
	private Supplier findByEmail(String email){
		return supplierDao.findByPhone(email);
	}
	
	
	private Supplier findByUid(Integer uid){
		return supplierDao.findByUid(uid);
	}
	
	private List<Supplier> findAll(){
		return supplierDao.findAll();
	}
	
	private void updateInfo(Supplier supplier){
		Integer rows = supplierDao.updateInfo(supplier);
		if(rows!=1){
			throw new UpdateException("修改供应商数据时出现未知错误!");
		}
	}
	
	private List<Supplier> selectSupplierForPage(Map<String,Object> map){
		return supplierDao.selectSupplierForPage(map);
	}
	
	private Long selectCountSupplier(Map<String,Object> map){
		Long count = supplierDao.selectCountSupplier(map);
		return count;
	}
	
	/**
	 * 根据供应商id判断，该条数据是否被引用
	 * @param categoryId
	 * @return
	 */
	private Integer deleteSupplierId(Integer uid){
		return supplierDao.deleteSupplierId(uid);
	};
	
	/**
	 * 根据uid删除供应商
	 * @param uid 供应商id
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改时间
	 * @return
	 */
	private void updateIsDelete(Integer uid,
			String modifiedUser,Date modifiedTime){
		Integer rows = supplierDao.updateIsDelete(uid, modifiedUser, modifiedTime);
		if(rows!=1){
			throw new DeleteException("删除数据时出现未知错误!");
		}
	};
	
	
}






