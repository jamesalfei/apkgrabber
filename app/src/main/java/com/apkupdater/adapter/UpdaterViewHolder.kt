package com.apkupdater.adapter

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.View
import android.view.ViewGroup
import com.apkupdater.R
import com.apkupdater.activity.MainActivity
import com.apkupdater.model.*
import com.apkupdater.util.*
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import com.github.yeriomin.playstoreapi.GooglePlayException
import kotlinx.android.synthetic.main.updater_item.view.*
import kotlin.concurrent.thread
import android.support.v7.widget.LinearLayoutManager
import android.test.ActivityUnitTestCase

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

open class UpdaterViewHolder(view: View)
	: RecyclerView.ViewHolder(view)
{
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	protected var mView : View? = view
	protected var mContext : Context? = view.context
	protected val mLog : LogUtil = Kodein.global.instance()
	protected val mBus: MyBus = Kodein.global.instance()
	protected val mActivity : MainActivity = Kodein.global.instance()
	protected val mAppState : AppState = Kodein.global.instance()

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	open fun bind(
		adapter : UpdaterAdapter,
		u : Update?
	) {
		mView?.installed_app_name?.text = u?.name
		mView?.installed_app_pname?.text = u?.pname
		mView?.installed_app_version?.text =
			String.format("%1s (%2s) -> %3s (4%s)", u?.version, u?.versionCode, u?.newVersion, u?.newVersionCode)

		// Icon
		mView?.installed_app_icon?.setImageDrawable(mView?.context?.packageManager?.getApplicationIcon(u?.pname))

		// Beta icon
		mView?.isbeta_icon?.visibility = if (u?.isBeta as Boolean) View.VISIBLE else View.GONE
		mView?.isbeta_icon?.background?.setColorFilter(
			ColorUtil.getColorFromTheme(mContext?.theme, R.attr.colorAccent),
			android.graphics.PorterDuff.Mode.MULTIPLY
		)

		// Click handler for expand/collapse
		mView?.setOnClickListener{
			AnimationUtil.startDefaultAnimation(mContext, mView?.change_log_container)
			mView?.change_log_container?.visibility =
				if (mView?.change_log_container?.visibility == View.GONE) View.VISIBLE else View.GONE
		}

		// Changelog
		if (u.changeLog?.isNullOrEmpty() ?: true) {
			mView?.change_log_text?.text = ""
			mView?.change_log_text?.visibility = View.GONE
		} else {
			mView?.change_log_text?.text = Html.fromHtml(u.changeLog)
			mView?.change_log_text?.visibility = View.VISIBLE
		}

		mView?.button_bar?.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
		mView?.button_bar?.adapter = ButtonBarAdapter(mContext as Context)

		configureActionButton(u)
		setTopMargin(0)
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private fun configureActionButton(
		u : Update
	) {
		val text : String = getActionString(u)
		val adapter : ButtonBarAdapter = mView?.button_bar?.adapter as ButtonBarAdapter
		adapter.addButton(ActionButton(
			text,
			u.installStatus.status == InstallStatus.STATUS_INSTALLING,
			{ if (text == mContext?.getString(R.string.action_play)) launchInstall(u) else launchBrowser(u) }
		))
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private fun launchInstall(
		u : Update
	) {
		changeAppInstallStatusAndNotify(u, InstallStatus.STATUS_INSTALLING, 0)
		thread {
			try {
				val data = GooglePlayUtil.getAppDeliveryData(GooglePlayUtil.getApi(mContext), u.pname)

				val id = DownloadUtil.downloadFile(
					mContext,
					data.downloadUrl,
					data.getDownloadAuthCookie(0).name + "=" + data.getDownloadAuthCookie(0).value,
					u.pname + " " + u.newVersionCode
				)

				mAppState.downloadInfo.put(id, DownloadInfo(u.pname, u.newVersionCode, u.newVersion))
				changeAppInstallStatusAndNotify(u, InstallStatus.STATUS_INSTALLING, id)
			} catch (gex: GooglePlayException) {
				SnackBarUtil.make(mActivity, gex.message.toString())
				mLog.log("UpdaterAdapter", gex.toString(), LogMessage.SEVERITY_ERROR)
				changeAppInstallStatusAndNotify(u, InstallStatus.STATUS_INSTALL, 0)
			} catch (e: Exception) {
				SnackBarUtil.make(mActivity, "Error downloading.")
				mLog.log("UpdaterAdapter", e.toString(), LogMessage.SEVERITY_ERROR)
				changeAppInstallStatusAndNotify(u, InstallStatus.STATUS_INSTALL, 0)
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private fun launchBrowser(
		u : Update
	) {
		DownloadUtil.launchBrowser(mContext, u.url)
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private fun getActionString(
		u : Update
	) : String {
		if (u.url.contains("apkmirror.com")) {
			return mContext?.getString(R.string.action_apkmirror)!!
		} else if (u.url.contains("uptodown.com")) {
			return mContext?.getString(R.string.action_uptodown)!!
		} else if (u.url.contains("apkpure.com")) {
			return mContext?.getString(R.string.action_apkpure)!!
		} else if (u.cookie != null) {
			if (u.installStatus.status == InstallStatus.STATUS_INSTALL) {
				return mContext?.getString(R.string.action_play)!!
			} else if (u.installStatus.status == InstallStatus.STATUS_INSTALLED) {
				return mContext?.getString(R.string.action_installed)!!
			} else if (u.installStatus.status == InstallStatus.STATUS_INSTALLING) {
				return ""
			}
		}
		return "ERROR"
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private fun changeAppInstallStatusAndNotify(
		app: Update?,
		status: Int,
		id: Long
	) {
		val adapter : UpdaterAdapter = Kodein.global.instance()
		app?.installStatus?.id = id
		app?.installStatus?.status = status
		mView?.post {
			adapter.notifyItemChanged(adapterPosition)
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	fun setTopMargin(
		margin: Int
	) {
		val params = mView?.layoutParams as ViewGroup.MarginLayoutParams?
		params?.topMargin = PixelConversion.convertDpToPixel(margin.toFloat(), mContext).toInt()
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////