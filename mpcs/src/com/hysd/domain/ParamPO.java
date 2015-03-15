package com.hysd.domain;

public class ParamPO {
	/** SN号 */
	private String sn;
	/** 手机号 */
	private String phone;
	/** 所在地区 */
	private String area;
	private String act;
	private EqinfoPO eqinfoPO;
	private EqmonitorinfoPO eqmonitorinfoPO;
	private ClientPO clientPO;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public EqinfoPO getEqinfoPO() {
		return eqinfoPO;
	}

	public void setEqinfoPO(EqinfoPO eqinfoPO) {
		this.eqinfoPO = eqinfoPO;
	}

	public EqmonitorinfoPO getEqmonitorinfoPO() {
		return eqmonitorinfoPO;
	}

	public void setEqmonitorinfoPO(EqmonitorinfoPO eqmonitorinfoPO) {
		this.eqmonitorinfoPO = eqmonitorinfoPO;
	}

	public ClientPO getClientPO() {
		return clientPO;
	}

	public void setClientPO(ClientPO clientPO) {
		this.clientPO = clientPO;
	}

}
