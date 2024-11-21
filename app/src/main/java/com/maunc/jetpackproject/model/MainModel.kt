package com.maunc.jetpackproject.model

import com.maunc.jetpackmvvm.base.BaseModel
import com.maunc.jetpackproject.utils.DataStorePreferences

class MainModel : BaseModel() {

    suspend fun setFirstInitApp(boolean: Boolean) =
        DataStorePreferences.setBoolean("isFirst", boolean)

    suspend fun isFirstInitApp(): Boolean =
        DataStorePreferences.getBoolean("isFirst", true)
}