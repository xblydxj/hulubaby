package com.heima.jyl.redboy.utils.Constants;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public class Constants  {
    /**
     * saleDown(销量降序)，
     * priceUp(价格升序)，
     * priceDown(价格降序)，
     * commentDown(评价降序)，
     * shelvesDown(上架降序)。
     * 目前只有价格有双向排序，其他都只有降序。
     */
    public static String SALE_DOWN = "saleDown";
    public static String PRICE_DOWN = "priceDown";
    public static String PRICE_UP = "priceUp";
    public static String COMMENTDOWN = "commentDown";
    public static String SHWLVESDOWN = "shelvesDown";

    public static String DB_NAME = "goodsMessage.db";
    public static int VERSION = 1;
    public static String TABLE_NAME = "goodsMessage";
    public static String COLUMN_ID = "id";
    public static String COLUMN_NAME = "name";
    public static String COLUMN_PIC = "pic";
    public static String COLUMN_PRICE = "price";
    public static String COLUMN_MARKEYPRICE = "marketprice";

    public static String SQL_CREATE_TABLE = "create table " + TABLE_NAME + " ( " + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_PRICE + " text "
            + COLUMN_MARKEYPRICE + " text "
            + COLUMN_PIC + " text "
            + COLUMN_NAME + " text unique )";


}
