/**
 * Description:���ڻ�����Ϣ������
 */
package com.util;

import java.math.BigDecimal;


/**
 * ���ڻ�����Ϣ��ָͶ����Ŀ���е��ں�һ�λ�����Ϣ
 * 
 */
public class oneRepayPrincipalInterest {
	/**
	 * ���ں�һ�λ�����Ϣ
	 * 
	 * ��ʽ�����ں��յ��ı�Ϣ=�����(1+������)^����������
	 * 
	 * @param principal
	 *            ����
	 * @param yearRate
	 *            ������
	 * @param totalMonth
	 *            ����������
	 * @return ���ں��յ��ı�Ϣ
	 */
	public static BigDecimal getPerMonthPrincipalInterest(double principal,
			double yearRate, int totalMonth) {

		return new BigDecimal(principal).multiply(BigDecimal.valueOf(Math.pow(
				1 + yearRate, totalMonth)));
	}
}
