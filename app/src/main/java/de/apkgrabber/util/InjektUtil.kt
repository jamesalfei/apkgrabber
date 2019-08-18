package de.apkgrabber.util


import de.apkgrabber.activity.MainActivity
import de.apkgrabber.adapter.UpdaterAdapter
import de.apkgrabber.model.AppState
import uy.kohesive.injekt.api.InjektScope
import uy.kohesive.injekt.api.addSingleton
import uy.kohesive.injekt.registry.default.DefaultRegistrar


class InjektUtil {


    companion object {


        var injekt: InjektScope? = null


        fun init() {
            injekt = InjektScope(DefaultRegistrar())
        }


        fun addAppStateSingleton(appState: AppState) {
            injekt?.addSingleton(appState)
        }


        fun addMyBusSingleton(bus: MyBus) {
            injekt?.addSingleton(bus)
        }


        fun addLogUtilSingleton(logger: LogUtil) {
            injekt?.addSingleton(logger)
        }


        fun addActivitySingleton(activity: MainActivity) {
            injekt?.addSingleton(activity)
        }


        fun addUpdaterAdapterSingleton(adapter: UpdaterAdapter) {
            injekt?.addSingleton(adapter)
        }


    }


}

