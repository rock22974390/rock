/**
 * Description:�ȶ�𹤾���
 */

package com.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * �ȶ����ָһ�ִ���Ļ��ʽ�����ڻ������ڰѴ������ܶ�ȷ֣�ÿ�³���ͬ������ı����ʣ������ڸ�������������Ϣ����������ÿ�µĻ�����̶���
 * ����ϢԽ��Խ�٣�������������ѹ���ϴ󣬵�����ʱ�������ÿ�»�����ҲԽ��Խ�١�
 */
public class AverageCapitalUtils {

    /**
     * �ȶ������ȡ���ʽΪ�ȶ���ÿ�³����������Ϣ
     * 
     * ��ʽ��ÿ�³�������=(�����»�������)+(�����-�ѹ黹�����ۼƶ�)��������
     * 
     * @param invest
     *            �ܽ�������
     * @param yearRate
     *            ������
     * @param month
     *            ����������
     * @return ÿ�³����������Ϣ,���������룬ֱ�ӽ�ȡС���������λ
     */
    public static Map<Integer, Double> getPerMonthPrincipalInterest(double invest, double yearRate, int totalMonth) {
        Map<Integer, Double> map = new HashMap<Integer, Double>();
        // ÿ�±���
        double monthPri = getPerMonthPrincipal(invest, totalMonth);
        // ��ȡ������
        double monthRate = yearRate / 12;
        monthRate = new BigDecimal(monthRate).setScale(6, BigDecimal.ROUND_DOWN).doubleValue();
        for (int i = 1; i <= totalMonth; i++) {
            double monthRes = monthPri + (invest - monthPri * (i - 1)) * monthRate;
            monthRes = new BigDecimal(monthRes).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            map.put(i, monthRes);
        }
        return map;
    }

    /**
     * �ȶ������ȡ���ʽΪ�ȶ���ÿ�³�����Ϣ
     * 
     * ��ʽ��ÿ��Ӧ����Ϣ=ʣ�౾���������=(�����-�ѹ黹�����ۼƶ�)��������
     * 
     * @param invest
     *            �ܽ�������
     * @param yearRate
     *            ������
     * @param month
     *            ����������
     * @return ÿ�³�����Ϣ
     */
    public static Map<Integer, Double> getPerMonthInterest(double invest, double yearRate, int totalMonth) {
        Map<Integer, Double> inMap = new HashMap<Integer, Double>();
        double principal = getPerMonthPrincipal(invest, totalMonth);
        Map<Integer, Double> map = getPerMonthPrincipalInterest(invest, yearRate, totalMonth);
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            BigDecimal principalBigDecimal = new BigDecimal(principal);
            BigDecimal principalInterestBigDecimal = new BigDecimal(entry.getValue());
            BigDecimal interestBigDecimal = principalInterestBigDecimal.subtract(principalBigDecimal);
            interestBigDecimal = interestBigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
            inMap.put(entry.getKey(), interestBigDecimal.doubleValue());
        }
        return inMap;
    }

    /**
     * �ȶ������ȡ���ʽΪ�ȶ���ÿ�³�������
     * 
     * ��ʽ��ÿ��Ӧ������=�����»�������
     * 
     * @param invest
     *            �ܽ�������
     * @param yearRate
     *            ������
     * @param month
     *            ����������
     * @return ÿ�³�������
     */
    public static double getPerMonthPrincipal(double invest, int totalMonth) {
        BigDecimal monthIncome = new BigDecimal(invest).divide(new BigDecimal(totalMonth), 2, BigDecimal.ROUND_DOWN);
        return monthIncome.doubleValue();
    }

    /**
     * �ȶ������ȡ���ʽΪ�ȶ�������Ϣ
     * 
     * @param invest
     *            �ܽ�������
     * @param yearRate
     *            ������
     * @param month
     *            ����������
     * @return ����Ϣ
     */
    public static double getInterestCount(double invest, double yearRate, int totalMonth) {
        BigDecimal count = new BigDecimal(0);
        Map<Integer, Double> mapInterest = getPerMonthInterest(invest, yearRate, totalMonth);

        for (Map.Entry<Integer, Double> entry : mapInterest.entrySet()) {
            count = count.add(new BigDecimal(entry.getValue()));
        }
        return count.doubleValue();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        double invest = 10000; // ����
        int month = 12;
        double yearRate = 0.15; // ������
        Map<Integer, Double> getPerMonthPrincipalInterest = getPerMonthPrincipalInterest(invest, yearRate, month);
        System.out.println("�ȶ��---ÿ�±�Ϣ��" + getPerMonthPrincipalInterest);
        double benjin = getPerMonthPrincipal(invest, month);
        System.out.println("�ȶ��---ÿ�±���:" + benjin);
        Map<Integer, Double> mapInterest = getPerMonthInterest(invest, yearRate, month);
        System.out.println("�ȶ��---ÿ����Ϣ:" + mapInterest);

        double count = getInterestCount(invest, yearRate, month);
        System.out.println("�ȶ��---����Ϣ��" + count);
    }
}