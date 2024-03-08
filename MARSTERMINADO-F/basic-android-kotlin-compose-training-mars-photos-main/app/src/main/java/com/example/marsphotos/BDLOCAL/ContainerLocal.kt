package com.example.marsphotos.BDLOCAL

import android.content.Context
import com.example.marsphotos.BDLOCAL.BD.BD
import com.example.marsphotos.BDLOCAL.REPO.REPOIMPLEMENT
import com.example.marsphotos.BDLOCAL.REPO.REPOSUSPEND
import com.example.marsphotos.data.REPO


interface AppContainer {
    val REPOLOCAL: REPOSUSPEND
}
class ContainerLocal(private val context: Context) : AppContainer {

    override val REPOLOCAL: REPOSUSPEND  by lazy {
        REPOIMPLEMENT(BD.getDatabase(context).DAO())
    }
}