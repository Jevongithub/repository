package co.uk.learning.searchtest.exploreFragmentBuild

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.uk.learning.searchtest.R
import co.uk.learning.searchtest.exploreFragmentBuild.ui.ExploreAdapter
import co.uk.learning.searchtest.exploreFragmentBuild.viewmodel.ExploreViewModel
import kotlinx.android.synthetic.main.fragment_explore.*
import kotlinx.android.synthetic.main.fragment_explore.view.*


class ExploreFragment : Fragment() {


    private val contentAdapter = ExploreAdapter()

    fun String.intOrString(): Any {
        val v = toIntOrNull()
        return when(v) {
            null -> 1
            else -> 2
        }
    }

    private val receiverUpdateDownload: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            //Getdata from intent
            // intent?.getStringExtra("searching") is equivalent to intent.extras.getString("searching")

            Toast.makeText(context, "Typing ${searching.string}", Toast.LENGTH_LONG).show()

            searching.string = intent?.getStringExtra("searching")!!.intOrString() as Int

            // i think whats next is for me to clear the recyclerview before initiating the next functions

            val itemViewModel = ViewModelProvider(requireActivity()).get(ExploreViewModel::class.java)

            itemViewModel.userPagedList.observe(viewLifecycleOwner, Observer {
                contentAdapter.submitList(it)
            })

            contentAdapter.notifyDataSetChanged()


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        if (view.list is RecyclerView) {
            with(view.list) {
                layoutManager = LinearLayoutManager(context)

                adapter = contentAdapter

            }
        }


        val filter = IntentFilter("STRING_ID_FOR_BRODCAST")
        requireActivity().registerReceiver(receiverUpdateDownload, filter)

        Toast.makeText(view.context, "fragment activated", Toast.LENGTH_LONG).show()


        return view
    }


    override fun onStop() {
        super.onStop()
        try {
            requireActivity().unregisterReceiver(receiverUpdateDownload)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}


