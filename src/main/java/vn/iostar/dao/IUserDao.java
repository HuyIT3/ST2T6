package vn.iostar.dao;

import java.util.List;

import vn.iostar.controllers.models.UserModel;

public interface IUserDao {
	UserModel findByUsername(String username);
	UserModel findById(int id);
	List<UserModel> findAll();
	void insert(UserModel user);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	void updatePassword(String email, String password);
}
