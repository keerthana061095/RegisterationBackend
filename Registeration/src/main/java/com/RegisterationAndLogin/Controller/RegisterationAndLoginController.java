package com.RegisterationAndLogin.Controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.RegisterationAndLogin.Request.RegisterationAndLoginRequest;
import com.RegisterationAndLogin.Util.Util;
import com.RegisterationAndLogin.response.RegisterResponse;
import com.RegisterationAndLogin.service.RegisterationAndLoginService;

@RestController
public class RegisterationAndLoginController {
	
	@Autowired
	RegisterationAndLoginService service;

	@PostMapping("/create")
    public @ResponseBody Map<String, String> create(@RequestBody RegisterationAndLoginRequest request) {
		Map<String, String> res = new LinkedHashMap<>();
		boolean emailCheck = true;
		if (request.getFirstName().isEmpty()) {
			res.put("Failer", "FirstName should not be Null or empty");
			return res;
		}
		if (request.getLastName().isEmpty()) {
			res.put("Failer", "LastName should not be null or empty");
			return res;
		}
		if (request.getPassword().isEmpty()) {
			res.put("Failer", "Password should not be null or empty ");
			return res;
		}
		if (request.getMiddleName().isEmpty()) {
			res.put("Failer", "MiddleName should not be null or empty ");
			return res;
		}
		if (request.getHeight().isEmpty()){
			res.put("Failer", "Height should not be null or empty ");
			return res;
		}
		if (request.getWeight().isEmpty()) {
			res.put("Failer", "weight should not be null or empty ");
			return res;
		}
		if(request.getPassword().length() < 8){
			res.put("Failer", "Password length should be greater than 8 characters");
			return res;
		}
		if (request.getEmailId().isEmpty()) {
			res.put("Failer", "EmailId should not be null or empty");
			return res;
		}
		if (request.getPhoneNumber().isEmpty()) {
			res.put("Failer", "Phone should not be null or empty");
			return res;
		}
		emailCheck = Util.checkValidMail(request.getEmailId());
		if (!emailCheck) {
			res.put("Failer", "EmailId Should be valid");
			return res;
		}
		emailCheck = Util.isValidMobileNumber(request.getPhoneNumber());
		if(!emailCheck) {
			res.put("Failer", "please provide numerics only");
			return res;
		}
		res = service.save(request);
        return res;
    }

	@PostMapping(value = "/update")
	public @ResponseBody Map<String, Object> update(@RequestBody RegisterationAndLoginRequest request){
		Map<String, Object> res = service.update(request);
		return res;
	}
	
	@PostMapping(value = "/delete")
	public @ResponseBody Map<String, Object> delete(@RequestBody RegisterationAndLoginRequest request){
		Map<String, Object> res = service.delete(request.getId());
		return res;
	}

	@PostMapping(value = "/list")
	public @ResponseBody List<RegisterResponse> list(@RequestBody RegisterationAndLoginRequest req){
		List<RegisterResponse> res = service.list(req);
		return res;
	}
	
	@PostMapping("/login")
	public @ResponseBody Map<String, Object> login(@RequestBody RegisterationAndLoginRequest request){
		Map<String, Object> res = new LinkedHashMap<>();
		boolean emailCheck = true;
		if (request.getEmailId().isEmpty()) {
			res.put("Failer", "EmailId should not be null or empty");
			return res;
		}
		if (request.getPassword().isEmpty()) {
			res.put("Failer", "Password should not be null or empty");
			return res;
		}
		emailCheck = Util.checkValidMail(request.getEmailId());
		if (!emailCheck) {
			res.put("Failer", "EmailId Should be valid");
			return res;
		}
		res = service.login(request);
		return res;

	}
}
