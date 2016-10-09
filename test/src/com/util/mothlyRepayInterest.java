/**
 * Description:按月付息,到期还本工具类
 */
package com.util;

import java.math.BigDecimal;

/**
 * 按月付息,到期还本是指每个月在指定时间支付一个月的利息，项目到期后还本金
 * 
 */
public class mothlyRepayInterest {
	/**
	 * 等额本金计算获取还款方式为按月付息,到期还本的每月偿还利息
	 * 
	 * 公式：每月支付的利息=本金×年利率÷365×天数
	 * 
	 * @param principal
	 *            本金
	 * @param yearRate
	 *            年利率
	 * @param days
	 *           天数
	 * @return 到期后收到的本息
	 */
	public static BigDecimal getmothlyRepayInterest(double principal,
			double yearRate, int days) {

		return new BigDecimal(principal).multiply(BigDecimal.valueOf(yearRate))
				.multiply(BigDecimal.valueOf(365))
				.divide(BigDecimal.valueOf(days));
	}
}
