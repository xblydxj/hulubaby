package com.heima.jyl.redboy.dbdao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class UserDBOpenHelper extends OrmLiteSqliteOpenHelper {
    public UserDBOpenHelper(Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            // 创建表
            TableUtils.createTable(connectionSource,User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            // 更新表
            TableUtils.dropTable(connectionSource,User.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static UserDBOpenHelper instance;

    public static synchronized UserDBOpenHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (UserDBOpenHelper.class) {
                if (instance == null) {
                    instance = new UserDBOpenHelper(context);
                }
            }
        }
        return instance;
    }

    private Dao<User, Integer> dao;

    // 获取操作数据库的DAO
    public Dao<User, Integer> getUserDao() throws SQLException {
        if (dao == null) {
            dao = getDao(User.class);
        }
        return dao;
    }



    @Override
    public void close() {
        super.close();
        dao = null;
    }
}