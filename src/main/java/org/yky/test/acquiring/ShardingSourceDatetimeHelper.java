package org.yky.test.acquiring;

import java.util.Date;

/***
 * @description 交易日期分表辅助方法
 * @author kaiyang.yan
 * @create 18/03/2022
 * @version V1.0
 */
public class ShardingSourceDatetimeHelper {

    public static final String SDSPLIT = "_";

    /**
     * 根据交易日期，返回今年属于第几个周期中
     * @param sourceDate
     * @return
     */
    public static String getSourceDatetimeKey(Date sourceDate) {
        String year = DateUtil.getYear(sourceDate);
        Integer staticTranSplit = 10;
        if (staticTranSplit == null) {
        }
        return year + SDSPLIT + String.format("%04d", DateUtil.getDateIndex(sourceDate, staticTranSplit));
    }

    public static void main(String[] args) {
        System.out.println(getSourceDatetimeKey(new Date()));
    }

}
