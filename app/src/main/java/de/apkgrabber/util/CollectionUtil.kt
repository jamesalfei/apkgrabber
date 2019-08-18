package de.apkgrabber.util


import de.apkgrabber.model.APKMirror.AppExistsResponseApk


class CollectionUtil {


    companion object {


        fun sortAppExistsResponseApk(list: List<AppExistsResponseApk>): List<AppExistsResponseApk> {
            try {
                return list.sortedWith(compareByDescending<AppExistsResponseApk> { it.minapi }.thenByDescending { it.dpis[0] }.thenByDescending { it.arches[0] })
            } catch (e: Exception) {
                return list
            }
        }


        fun getFirstAppExistResponse(list: List<AppExistsResponseApk>): AppExistsResponseApk {
            return list.firstOrNull { it.dpis[0] != "nodpi" } ?: list.first()
        }


    }


}

