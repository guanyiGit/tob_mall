package com.orgmanagement.service.impl;

import com.orgmanagement.dao.RoleInfoDao;
import com.orgmanagement.dao.RoleRefResourceDao;
import com.orgmanagement.dao.UserInfoDao;
import com.orgmanagement.dao.UserRefRoleDao;
import com.orgmanagement.domain.RoleDO;
import com.orgmanagement.domain.RoleMenuDO;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.RoleInfoService;
import com.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class RoleInfoServiceImpl implements RoleInfoService {
//	@Autowired
//	private RoleInfoDao roleInfoDao;
//
//	@Override
//	public RoleInfo get(Integer id){
//		return roleInfoDao.get(id);
//	}
//
//	@Override
//	public List<RoleInfo> list(Map<String, Object> map){
//		return roleInfoDao.list(map);
//	}
//
//	@Override
//	public int count(Map<String, Object> map){
//		return roleInfoDao.count(map);
//	}
//
//	@Override
//	public int save(RoleInfo roleInfo){
//		return roleInfoDao.save(roleInfo);
//	}
//
//	@Override
//	public int update(RoleInfo roleInfo){
//		return roleInfoDao.update(roleInfo);
//	}
//
//	@Override
//	public int remove(Integer id){
//		return roleInfoDao.remove(id);
//	}
//
//	@Override
//	public int batchRemove(Integer[] ids){
//		return roleInfoDao.batchRemove(ids);
//	}
//
//	@Override
//	public RoleInfo selectByRoleName(Map<String, Object> map) {
//		return roleInfoDao.selectByRoleName(map);
//	}


	public static final String ROLE_ALL_KEY = "\"role_all\"";

	public static final String DEMO_CACHE_NAME = "role";

	@Autowired
	RoleInfoDao roleMapper;
	@Autowired
	RoleRefResourceDao roleMenuMapper;
	@Autowired
	UserInfoDao userMapper;
	@Autowired
	UserRefRoleDao userRoleMapper;

	@Override
	public int listCount(Map<String, Object> params) {
		Integer offset = Integer.parseInt(params.get("offset").toString());
		Integer limit = Integer.parseInt(params.get("limit").toString());
		Integer roleId = Integer.parseInt(params.get("roleId").toString());
		Integer count = 0;
		if (roleId == 1){
			List<Integer> types = new ArrayList<>();
			//如果是平台管理员,获取type为1的角色
			types.add(1);
			count = roleMapper.listCount(offset,limit,types);
		}else if(roleId == 3){
			//如果是总部管理员，获取type为2和3的角色
			List<Integer> types = new ArrayList<>();
			types.add(2);
			types.add(3);
			count = roleMapper.listCount(offset,limit,types);
		} else if (roleId == 5) {
			//如果是分部管理员，获取type为3的角色
			List<Integer> types = new ArrayList<>();
			types.add(3);
			count = roleMapper.listCount(offset,limit,types);
		}
		return count;
	}

	@Override
	public List<RoleDO> list(Map<String, Object> params) {
		Integer offset = Integer.parseInt(params.get("offset").toString());
		Integer limit = Integer.parseInt(params.get("limit").toString());
		Integer roleId = Integer.parseInt(params.get("roleId").toString());
		List<RoleDO> roleDOS = new ArrayList<>();
		if (roleId == 1){
			List<Integer> types = new ArrayList<>();
			//如果是平台管理员,获取type为1的角色
			types.add(1);
			roleDOS = roleMapper.list(offset,limit,types);
		}else if(roleId == 3){
			//如果是总部管理员，获取type为2和3的角色
			List<Integer> types = new ArrayList<>();
			types.add(2);
			types.add(3);
			roleDOS = roleMapper.list(offset,limit,types);
		} else if (roleId == 5) {
			//如果是分部管理员，获取type为3的角色
			List<Integer> types = new ArrayList<>();
			types.add(3);
			roleDOS =  roleMapper.list(offset,limit,types);
		}
		return  roleDOS;
	}

	@Override
	public List<RoleDO> initRoleCheckBox(Integer roleId) {
		List<RoleDO> roleDOS = new ArrayList<>();
		if (roleId == 1){
			List<Integer> types = new ArrayList<>();
			//如果是平台管理员,获取type为1的角色
			 types.add(1);
			 roleDOS = roleMapper.initRoleCheckBox(types);
		}else {
			//如果是总部管理员，获取type为2和3的角色
			List<Integer> types = new ArrayList<>();
			types.add(2);
			types.add(3);
			roleDOS = roleMapper.initRoleCheckBox(types);
		}
		return roleDOS;
	}

	@Transactional
	@Override
	public int save(RoleDO role) {
		int count = roleMapper.save(role);
		List<Long> menuIds = role.getMenuIds();
		Long roleId = role.getRoleId();
		List<RoleMenuDO> rms = new ArrayList<>();
		for (Long menuId : menuIds) {
			RoleMenuDO rmDo = new RoleMenuDO();
			rmDo.setRoleId(roleId);
			rmDo.setMenuId(menuId);
			rms.add(rmDo);
		}
		roleMenuMapper.removeByRoleId(roleId);
		if (rms.size() > 0) {
			roleMenuMapper.batchSave(rms);
		}
		return count;
	}

	@Transactional
	@Override
	public int remove(Long id) {
		int count = roleMapper.remove(id);
		userRoleMapper.removeByRoleId(id);
		roleMenuMapper.removeByRoleId(id);
		return count;
	}

	@Override
	public RoleDO get(Long id) {
		RoleDO roleDO = roleMapper.get(id);
		return roleDO;
	}

	@Override
	public int update(RoleDO role) {
		int r = roleMapper.update(role);
		List<Long> menuIds = role.getMenuIds();
		Long roleId = role.getRoleId();
		roleMenuMapper.removeByRoleId(roleId);
		List<RoleMenuDO> rms = new ArrayList<>();
		for (Long menuId : menuIds) {
			RoleMenuDO rmDo = new RoleMenuDO();
			rmDo.setRoleId(roleId);
			rmDo.setMenuId(menuId);
			rms.add(rmDo);
		}
		if (rms.size() > 0) {
			roleMenuMapper.batchSave(rms);
		}
		return r;
	}

	@Override
	public int batchremove(Long[] ids) {
		int r = roleMapper.batchRemove(ids);
		return r;
	}
}
