package com.gmail.bukin26.mg.navigation

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import java.lang.ref.WeakReference
import javax.inject.Singleton

@Singleton
class Router {

    private var navController: NavController? = null
    private var context: WeakReference<Context>? = null

    fun navigate(navDirections: NavDirections) {
        navController?.navigate(navDirections)
    }

    fun navigate(@IdRes navId: Int) {
        navController?.navigate(navId)
    }

     fun navigate(@IdRes navId: Int, args: Bundle) {
        navController?.navigate(navId, args)
    }

     fun navigateUp() {
        navController?.navigateUp()
    }

     fun attach(navController: NavController, context: Context) {
        this.navController = navController
        this.context = WeakReference(context)
    }

     fun detach() {
        navController = null
    }
}