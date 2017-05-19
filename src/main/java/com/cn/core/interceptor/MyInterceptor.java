package com.cn.core.interceptor;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.cn.core.policy.Policy;
import com.cn.uuu.core.exception.MainException;
/**
 * 自动注入policyList只需要实现com.cn.core.policy.Policy接口。
 * @author zhangtejun
 * @date 2017年5月19日 上午10:32:07
 */
public abstract class MyInterceptor implements HandlerInterceptor{
	public List<Policy> policyList;
	
	
	@Autowired
	public void setPolicyList(List<Policy> policyList) {
		this.policyList = policyList;
	}
	public List<Policy> getPolicyList() {
		return policyList;
	}
	
	public boolean  execute(HttpServletRequest request) throws MainException {
		List<Policy> list = getPolicyList();
		if(list==null)return true;
		 Policy policy;
		for(Iterator it = list.iterator();it.hasNext();policy.permit(request)){
			policy=(Policy) it.next();
		}
		return true;
	}
	
}
