package de.apkgrabber.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.apkgrabber.R
import de.apkgrabber.model.ActionButton
import kotlinx.android.synthetic.main.button_bar_item.view.*


class ButtonBarAdapter(context: Context) : RecyclerView.Adapter<ButtonBarAdapter.ButtonBarViewHolder>() {

    private var mItems: MutableList<ActionButton> = mutableListOf()
    private var mContext: Context = context

    override fun onBindViewHolder(holder: ButtonBarViewHolder, position: Int) {
        holder.bind(mItems[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonBarViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.button_bar_item, parent, false)
        return ButtonBarViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mItems.count()
    }

    class ButtonBarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mView: View = view

        fun bind(data: ActionButton) {
            mView.button.text = data.text
            mView.button.setOnClickListener { data.callback() }
            if (data.loading) {
                mView.progress_bar.visibility = View.VISIBLE
                mView.button.visibility = View.INVISIBLE
            } else {
                mView.progress_bar.visibility = View.INVISIBLE
                mView.button.visibility = View.VISIBLE
            }
        }
    }

    fun addButton(button: ActionButton) {
        mItems.add(button)
    }

}

