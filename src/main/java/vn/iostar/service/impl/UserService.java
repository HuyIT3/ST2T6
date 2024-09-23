package vn.iostar.service.impl;



import java.util.logging.Level;
import java.util.logging.Logger;

import vn.iostar.controllers.models.UserModel;
import vn.iostar.dao.IUserDao;
import vn.iostar.dao.impl.UserDaoImpl;
import vn.iostar.service.IUserService;

public class UserService implements IUserService{
	private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
	IUserDao userDao = new UserDaoImpl();
	@Override
	public UserModel findByUserName(String username) {
		return userDao.findByUsername(username);
		
	}

	@Override
	public UserModel login(String username, String password) {
		// TODO Auto-generated method stub
		UserModel user = this.findByUserName(username);
		if(user !=null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public boolean register(String userName, String password, String email, String fullname, String phone) {
		if (userDao.checkExistUsername(userName)) {
			return false;
			}
			long millis=System.currentTimeMillis();
			java.sql.Date date=new java.sql.Date(millis);
			userDao.insert(new UserModel(userName,email, password, fullname,null,phone,5,date));
			return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		
		return userDao.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		
		return userDao.checkExistPhone(phone);
	}

	@Override
	public boolean resetPass(String email, String password) {
		LOGGER.log(Level.INFO, "Attemping to reset password for email: {0}", email);
		if (!userDao.checkExistEmail(email)) {
			LOGGER.log(Level.WARNING, "Email does not exist: {0}", email);
			return false;
		}
		userDao.updatePassword(email, password);
		LOGGER.log(Level.INFO, "Password reset successful for email: {0}", email);
		return true;
	}
	public static void main(String[] args) {
		try {
			IUserService userService = new UserService();
			System.out.println(userService.login("huy","123"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
