package vn.iostar.controllers.models;
import java.io.Serializable;

public class RoleModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int roleid;
	private String rolename;
	public RoleModel(int roleid, String rolename) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
	}
	@Override
	public String toString() {
		return "RoleModel [roleid=" + roleid + ", rolename=" + rolename + "]";
	}
	public RoleModel() {
		super();
	}
	
}
