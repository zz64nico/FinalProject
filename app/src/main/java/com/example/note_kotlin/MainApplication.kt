package com.example.note_kotlin

import android.app.Application
import com.google.firebase.FirebaseApp
import org.xutils.DbManager
import org.xutils.DbManager.DaoConfig
import org.xutils.x


class MainApplication : Application() {

    var dbManager: DbManager? = null //数据库存储


    var daoConfig = DaoConfig()
        .setDbVersion(1)
        .setDbUpgradeListener { db, oldVersion, newVersion -> }

    override fun onCreate() {
        super.onCreate()
        instance = this
        x.Ext.init(this);
        if (dbManager == null) {
            dbManager = x.getDb(daoConfig);
        }
        FirebaseApp.initializeApp(this);
    }

    companion object {
        var instance: MainApplication? = null
            private set
    }
}