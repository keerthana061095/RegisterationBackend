package com.RegisterationAndLogin.dao;

import java.util.List;

import com.RegisterationAndLogin.Model.UserRegisteration;
import com.RegisterationAndLogin.response.RegisterResponse;

public interface RegisterationAndLogindao {

	void save(UserRegisteration userRegis);

	UserRegisteration alreadyExist(String emailId);

	UserRegisteration getById(String id);

	void delete(String id);

	List<RegisterResponse> list(Integer integer, Integer integer2);

	UserRegisteration isUserAccountExists(String userName);

	UserRegisteration login(String userName, String password);

	void saveAndUpdate(UserRegisteration userRegis);

}
