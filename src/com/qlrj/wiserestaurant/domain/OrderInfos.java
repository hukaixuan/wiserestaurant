package com.qlrj.wiserestaurant.domain;

import java.util.ArrayList;

public class OrderInfos {
	public String orderNum;
	public String remark;
	public long commitTime;
	public boolean hasPay=false;//是否支付
	public boolean hasCommited=false;//是否提交
	public float totalMoney;//订单总价
	public boolean isPackages;
	public ArrayList<OrderItemInfo> orderItems=new ArrayList<OrderItemInfo>();
	@Override
	public String toString() {
		return "OrderItemInfos [orderNum=" + orderNum + ", hasPay=" + hasPay + ", hasCommited=" + hasCommited
				+ ", totalMoney=" + totalMoney + ", orderItems=" + orderItems + "]";
	}
	
}
