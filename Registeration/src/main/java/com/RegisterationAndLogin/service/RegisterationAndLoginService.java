package com.RegisterationAndLogin.service;

import java.util.List;
import java.util.Map;

import com.RegisterationAndLogin.Request.RegisterationAndLoginRequest;
import com.RegisterationAndLogin.response.RegisterResponse;

public interface RegisterationAndLoginService {

	Map<String, String> save(RegisterationAndLoginRequest request);

	Map<String, Object> update(RegisterationAndLoginRequest request);

	Map<String, Object> delete(String id);

	List<RegisterResponse> list(RegisterationAndLoginRequest req);

	Map<String, Object> login(RegisterationAndLoginRequest request);

}
