/**
 * Description:到期还本付息工具类
 */
package com.util;

import java.math.BigDecimal;


/**
 * 到期还本付息就指投资项目持有到期后，一次还本付息
 * 
 */
public class oneRepayPrincipalInterest {
	/**
	 * 到期后一次还本付息
	 * 
	 * 公式：到期后收到的本息=本金×(1+年利率)^还款总月数
	 * 
	 * @param principal
	 *            本金
	 * @param yearRate
	 *            年利率
	 * @param totalMonth
	 *            还款总月数
	 * @return 到期后收到的本息
	 */
	public static BigDecimal getPerMonthPrincipalInterest(double principal,
			double yearRate, int totalMonth) {

		return new BigDecimal(principal).multiply(BigDecimal.valueOf(Math.pow(
				1 + yearRate, totalMonth)));
	}
}
