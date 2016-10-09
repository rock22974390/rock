/**
 * Description:���¸�Ϣ,���ڻ���������
 */
package com.util;

import java.math.BigDecimal;

/**
 * ���¸�Ϣ,���ڻ�����ָÿ������ָ��ʱ��֧��һ���µ���Ϣ����Ŀ���ں󻹱���
 * 
 */
public class mothlyRepayInterest {
	/**
	 * �ȶ������ȡ���ʽΪ���¸�Ϣ,���ڻ�����ÿ�³�����Ϣ
	 * 
	 * ��ʽ��ÿ��֧������Ϣ=����������ʡ�365������
	 * 
	 * @param principal
	 *            ����
	 * @param yearRate
	 *            ������
	 * @param days
	 *           ����
	 * @return ���ں��յ��ı�Ϣ
	 */
	public static BigDecimal getmothlyRepayInterest(double principal,
			double yearRate, int days) {

		return new BigDecimal(principal).multiply(BigDecimal.valueOf(yearRate))
				.multiply(BigDecimal.valueOf(365))
				.divide(BigDecimal.valueOf(days));
	}
}
