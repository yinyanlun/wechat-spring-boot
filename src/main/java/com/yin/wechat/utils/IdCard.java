package com.yin.wechat.utils;

import java.util.Calendar;

/**
 * .
 */
public class IdCard {
    /** 中国公民身份证号码最小长度。 */
    public  final int CHINA_ID_MIN_LENGTH = 15;

    /** 中国公民身份证号码最大长度。 */
    public  final int CHINA_ID_MAX_LENGTH = 18;
    /**
     * 根据身份编号获取年龄
     *
     * @param idCard
     *            身份编号
     * @return 年龄
     */
    public static int getAgeByIdCard(String idCard) {
        int iAge = 0;
        Calendar cal = Calendar.getInstance();
        String year = idCard.substring(6, 10);
        int iCurrYear = cal.get(Calendar.YEAR);
        iAge = iCurrYear - Integer.valueOf(year);
        return iAge;
    }

    /**
     * 根据身份编号获取生日
     *
     * @param idCard 身份编号
     * @return 生日(yyyyMMdd)
     */
    public static String getBirthByIdCard(String idCard) {
        return idCard.substring(6, 14);
    }

    /**
     * 根据身份编号获取生日年
     *
     * @param idCard 身份编号
     * @return 生日(yyyy)
     */
    public static Short getYearByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(6, 10));
    }

    /**
     * 根据身份编号获取生日月
     *
     * @param idCard
     *            身份编号
     * @return 生日(MM)
     */
    public static Short getMonthByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(10, 12));
    }

    /**
     * 根据身份编号获取生日天
     *
     * @param idCard
     *            身份编号
     * @return 生日(dd)
     */
    public static Short getDateByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(12, 14));
    }

    /**
     * 根据身份编号获取性别
     *
     * @param idCard 身份编号
     * @return 性别(M-男，F-女，N-未知)
     */
    public static String getGenderByIdCard(String idCard) {
        String sGender = "未知";

        String sCardNum = idCard.substring(16, 17);
        if (Integer.parseInt(sCardNum) % 2 != 0) {
            sGender = "男";//男
        } else {
            sGender = "女";//女
        }
        return sGender;
    }

//修改年龄的方法
 /*   @RequestMapping(value = "shebaoshuju",method = RequestMethod.GET)
    public String shebaoshuju(){
        List<Wcbxx> list= wcbxxservice.all();
        int  cou=0;
        for(Wcbxx w:list){
            if(w.getIdcard()!=null && w.getIdcard().length()>17) {
                String sex = IdCard.getGenderByIdCard(w.getIdcard());
                int age = IdCard.getAgeByIdCard(w.getIdcard());
                w.setAge(Long.valueOf(age));
                w.setSex(sex);
                int a = wcbxxservice.updatess(w);
                if (a>0) {
                    System.out.println("姓名" + w.getRname() + "身份证" + w.getIdcard() + "年龄:" + age + "性别:" + sex);
                }
            }
            cou++;
            System.out.println(cou);
        }
        return "";
    }*/

   
    public static  void  main(String [] a){
        String idcard="460200199209275127";
        String sex= getGenderByIdCard(idcard);
        System.out.println("性别:" + sex);
        int age= getAgeByIdCard(idcard);
        System.out.println("年龄:" + age);
        Short nian=getYearByIdCard(idcard);
        Short yue=getMonthByIdCard(idcard);
        Short ri=getDateByIdCard(idcard);
        System.out.print(nian+"年"+yue+"月"+ri+"日");

        String sr=getBirthByIdCard(idcard);
        System.out.println("生日:" + sr);
    }
}