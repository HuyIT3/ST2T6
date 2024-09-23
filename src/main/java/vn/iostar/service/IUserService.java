package vn.iostar.service;

import vn.iostar.controllers.models.UserModel;

public interface IUserService {
	UserModel findByUserName(String username);
	UserModel login(String username, String password);
	
	boolean register(String username, String password, String email, String fullname, String phone);
	
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	
	boolean resetPass(String email, String password);
}
