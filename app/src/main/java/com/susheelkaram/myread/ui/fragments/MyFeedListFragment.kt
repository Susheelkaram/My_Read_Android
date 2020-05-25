package com.susheelkaram.myread.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.susheelkaram.myread.R
import com.susheelkaram.myread.adapter.FeedListAdapter

import com.susheelkaram.myread.databinding.FragmentMyFeedListBinding
import com.susheelkaram.myread.generated.callback.OnClickListener
import com.susheelkaram.myread.ui.activities.AddFeedActivity
import com.susheelkaram.myread.ui.viewmodel.MyFeedListViewModel
import kotlinx.android.synthetic.main.fragment_my_feed_list.view.*

class MyFeedListFragment : Fragment(), View.OnClickListener {

    lateinit var B: FragmentMyFeedListBinding
    lateinit var vm: MyFeedListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        B = FragmentMyFeedListBinding.inflate(inflater, container, false)
        return B.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(this).get(MyFeedListViewModel::class.java)

        B.btnAddFeed.setOnClickListener(this)
        setupFeedList()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_AddFeed -> openFeedEditor()
        }
    }

    private fun openFeedEditor() {
        var intent = Intent(requireContext(), AddFeedActivity::class.java)
        startActivity(intent)
    }

    private fun setupFeedList() {
        var feedListAdapter = FeedListAdapter(requireContext())
        B.rvFeedList.adapter = feedListAdapter
        B.rvFeedList.layoutManager = LinearLayoutManager(requireContext())
        vm.feedList.observe(viewLifecycleOwner, Observer {
            feedListAdapter.setData(it)
        })
    }
}
