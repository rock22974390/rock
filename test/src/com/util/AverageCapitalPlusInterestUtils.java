/**
 * Description:�ȶϢ������
 */

package com.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * �ȶϢ���Ҳ�ƶ��ڸ�Ϣ���������ÿ�°���ȵĽ������Ϣ������ÿ�´�����Ϣ���³�ʣ��������㲢���½��塣�Ѱ��Ҵ���ı����ܶ�����Ϣ�ܶ���ӣ�
 * Ȼ��ƽ����̯���������޵�ÿ�����С���Ϊ�����ˣ�ÿ���»������й̶�����ÿ�»�����еı���������µ�������Ϣ�������µݼ���
 */

public class AverageCapitalPlusInterestUtils {

    /**
     * �ȶϢ�����ȡ���ʽΪ�ȶϢ��ÿ�³����������Ϣ
     * 
     * ��ʽ��ÿ�³�����Ϣ=�������������ʡ�(1��������)�޻����������¡�(1��������)�޻�������-1��
     * 
     * @param invest
     *            �ܽ�������
     * @param yearRate
     *            ������
     * @param month
     *            ����������
     * @return ÿ�³����������Ϣ,���������룬ֱ�ӽ�ȡС���������λ
     */
    public static double getPerMonthPrincipalInterest(double invest, double yearRate, int totalmonth) {
        double monthRate = yearRate / 12;
        BigDecimal monthIncome = new BigDecimal(invest)
                .multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, totalmonth)))
                .divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth) - 1), 2, BigDecimal.ROUND_DOWN);
        return monthIncome.doubleValue();
    }

    /**
     * �ȶϢ�����ȡ���ʽΪ�ȶϢ��ÿ�³�����Ϣ
     * 
     * ��ʽ��ÿ�³�����Ϣ=�����������ʡ���(1+������)^��������-(1+������)^(���������-1)���¡�(1+������)^��������-1��
     * @param invest
     *            �ܽ�������
     * @param yearRate
     *            ������
     * @param month
     *            ����������
     * @return ÿ�³�����Ϣ
     */
    public static Map<Integer, BigDecimal> getPerMonthInterest(double invest, double yearRate, int totalmonth) {
        Map<Integer, BigDecimal> map = new HashMap<Integer, BigDecimal>();
        double monthRate = yearRate/12;
        BigDecimal monthInterest;
        for (int i = 1; i < totalmonth + 1; i++) {
            BigDecimal multiply = new BigDecimal(invest).multiply(new BigDecimal(monthRate));
            BigDecimal sub  = new BigDecimal(Math.pow(1 + monthRate, totalmonth)).subtract(new BigDecimal(Math.pow(1 + monthRate, i-1)));
            monthInterest = multiply.multiply(sub).divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth) - 1), 6, BigDecimal.ROUND_DOWN);
            monthInterest = monthInterest.setScale(2, BigDecimal.ROUND_DOWN);
            map.put(i, monthInterest);
        }
        return map;
    }

    /**
     * �ȶϢ�����ȡ���ʽΪ�ȶϢ��ÿ�³�������
     * 
     * @param invest
     *            �ܽ�������
     * @param yearRate
     *            ������
     * @param month
     *            ����������
     * @return ÿ�³�������
     */
    public static Map<Integer, BigDecimal> getPerMonthPrincipal(double invest, double yearRate, int totalmonth) {
        double monthRate = yearRate / 12;
        BigDecimal monthIncome = new BigDecimal(invest)
                .multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, totalmonth)))
                .divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth) - 1), 2, BigDecimal.ROUND_DOWN);
        Map<Integer, BigDecimal> mapInterest = getPerMonthInterest(invest, yearRate, totalmonth);
        Map<Integer, BigDecimal> mapPrincipal = new HashMap<Integer, BigDecimal>();

        for (Map.Entry<Integer, BigDecimal> entry : mapInterest.entrySet()) {
            mapPrincipal.put(entry.getKey(), monthIncome.subtract(entry.getValue()));
        }
        return mapPrincipal;
    }

    /**
     * �ȶϢ�����ȡ���ʽΪ�ȶϢ������Ϣ
     * 
     * @param invest
     *            �ܽ�������
     * @param yearRate
     *            ������
     * @param month
     *            ����������
     * @return ����Ϣ
     */
    public static double getInterestCount(double invest, double yearRate, int totalmonth) {
        BigDecimal count = new BigDecimal(0);
        Map<Integer, BigDecimal> mapInterest = getPerMonthInterest(invest, yearRate, totalmonth);

        for (Map.Entry<Integer, BigDecimal> entry : mapInterest.entrySet()) {
            count = count.add(entry.getValue());
        }
        return count.doubleValue();
    }

    /**
     * Ӧ�������ܺ�
     * @param invest
     *            �ܽ�������
     * @param yearRate
     *            ������
     * @param month
     *            ����������
     * @return Ӧ�������ܺ�
     */
    public static double getPrincipalInterestCount(double invest, double yearRate, int totalmonth) {
        double monthRate = yearRate / 12;
        BigDecimal perMonthInterest = new BigDecimal(invest)
                .multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, totalmonth)))
                .divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth) - 1), 2, BigDecimal.ROUND_DOWN);
        BigDecimal count = perMonthInterest.multiply(new BigDecimal(totalmonth));
        count = count.setScale(2, BigDecimal.ROUND_DOWN);
        return count.doubleValue();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        double invest = 20000; // ����
        int month = 12;
        double yearRate = 0.15; // ������
        double perMonthPrincipalInterest = getPerMonthPrincipalInterest(invest, yearRate, month);
        System.out.println("�ȶϢ---ÿ�»��Ϣ��" + perMonthPrincipalInterest);
        Map<Integer, BigDecimal> mapInterest = getPerMonthInterest(invest, yearRate, month);
        System.out.println("�ȶϢ---ÿ�»�����Ϣ��" + mapInterest);
        Map<Integer, BigDecimal> mapPrincipal = getPerMonthPrincipal(invest, yearRate, month);
        System.out.println("�ȶϢ---ÿ�»����" + mapPrincipal);
        double count = getInterestCount(invest, yearRate, month);
        System.out.println("�ȶϢ---����Ϣ��" + count);
        double principalInterestCount = getPrincipalInterestCount(invest, yearRate, month);
        System.out.println("�ȶϢ---Ӧ����Ϣ�ܺͣ�" + principalInterestCount);
    }
}