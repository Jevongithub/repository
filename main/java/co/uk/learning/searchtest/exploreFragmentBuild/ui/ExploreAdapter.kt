package co.uk.learning.searchtest.exploreFragmentBuild.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.uk.learning.searchtest.R
import co.uk.learning.searchtest.model.Broadcasts
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.profile_link.view.*

class ExploreAdapter : PagedListAdapter<Broadcasts, ExploreAdapter.UserViewHolder>(
    USER_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.profile_link, parent, false)

        return UserViewHolder(view)


    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user = getItem(position)
        user?.let { holder.bind(user) }
    }


    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageView = view.iv_avatar
        private val userName = view.tv_user_name
        private val userEmail = view.tv_user_email

        fun bind(user: Broadcasts) {
            userName.text = user.firstName + " " + user.lastName
            userEmail.text = user.email

            Glide.with(imageView.context)
                .load(user.avatar)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
        }


    }


    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<Broadcasts>() {
            override fun areItemsTheSame(oldItem: Broadcasts, newItem: Broadcasts): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Broadcasts, newItem: Broadcasts): Boolean =
                newItem == oldItem

        }
    }

}