package com.RegisterationAndLogin.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RegisterationAndLogin.Model.UserAddress;
import com.RegisterationAndLogin.Model.UserRegisteration;
import com.RegisterationAndLogin.Request.RegisterationAndLoginRequest;
import com.RegisterationAndLogin.Util.Util;
import com.RegisterationAndLogin.dao.RegisterationAndLogindao;
import com.RegisterationAndLogin.response.RegisterResponse;
import com.RegisterationAndLogin.service.RegisterationAndLoginService;

@Service
public class RegisterationAndLoginSeviceImpl implements RegisterationAndLoginService {

	@Autowired
	public RegisterationAndLogindao dao;

	@Override
	public Map<String, String> save(RegisterationAndLoginRequest request) {
		Map<String, String> res = new HashMap<>();
		UserRegisteration userRegis = new UserRegisteration();
		UserRegisteration user = dao.alreadyExist(request.getEmailId());
		if (user == null) {
			userRegis.setFirstName(request.getFirstName());
			userRegis.setLastName(request.getLastName());
			userRegis.setMiddleName(request.getMiddleName());
			userRegis.setEmailId(request.getEmailId());
			userRegis.setPhoneNumber(request.getPhoneNumber());
			userRegis.setPassword(Util.md5(request.getPassword()));
			userRegis.setWeight(request.getWeight());
			userRegis.setHeight(request.getHeight());
			userRegis.setUserAddress(getAddress(request));
			dao.save(userRegis);
			res.put("Message", "Inserted Successfully");
		} else {
			res.put("Message", "EmailId Already Exist");
		}
		return res;
	}

	private UserAddress getAddress(RegisterationAndLoginRequest request) {
		UserAddress res = new UserAddress();
		res.setAddress(request.getAddress());
		res.setCountry(request.getCountry());
		res.setState(request.getState());
		res.setZipcode(request.getZipcode());
		return res;
	}

	@Override
	public Map<String, Object> update(RegisterationAndLoginRequest request) {
		Map<String, Object> res = new HashMap<>();
		UserRegisteration userRegis = dao.getById(request.getId());
		if(userRegis!=null) {
		userRegis.setFirstName(request.getFirstName());
		userRegis.setLastName(request.getLastName());
		userRegis.setMiddleName(request.getMiddleName());
	    userRegis.setPhoneNumber(request.getPhoneNumber());
		userRegis.setEmailId(request.getEmailId());
		userRegis.setWeight(request.getWeight());
		userRegis.setHeight(request.getHeight());
		userRegis.setUserAddress(getAddress(request));
		dao.saveAndUpdate(userRegis);
		res.put("Message", "Updated Successfully");
		}else {
			res.put("Message", "Updated Successfully");
		}
		return res;
	}

	@Override
	public Map<String, Object> delete(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		dao.delete(id);
		map.put("status", "success");
		map.put("message", "deleted successfully");		
		return map;
	}

	@Override
	public List<RegisterResponse> list(RegisterationAndLoginRequest req) {
		List<RegisterResponse> res = dao.list(req.getIndex(),req.getPageSize());
		return res;
		
		
	}

	@Override
	public Map<String, Object> login(RegisterationAndLoginRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userName = request.getEmailId();
		String password = request.getPassword();
		UserRegisteration user = dao.isUserAccountExists(userName);
		if (user != null) {
			UserRegisteration regiVo = dao.login(userName, password);
			if(regiVo!=null) {
				map.put("status", "sccuess");
				map.put("message", "LOGIN SUCCESSFULLY");	
			}else {
				map.put("message", "PLEASE ENTER THE CORRECT PASSWORD");
			}
		}else {
			map.put("message", "PLEASE ENTER VALID USER CREDENTIALS");
		}
		
		return map;
	}

}
