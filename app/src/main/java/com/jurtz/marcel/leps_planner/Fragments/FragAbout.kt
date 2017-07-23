package com.jurtz.marcel.leps_planner.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jurtz.marcel.leps_planner.R
import kotlinx.android.synthetic.main.fragment_about.*

class FragAbout : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_about, container, false)
    }

    override fun onStart() {
        super.onStart()
        //wvAbout.loadDataWithBaseURL("file:///android_asset/", "about.html", "text/html; charset=utf-8", "utf-8", null);
        wvAbout.loadUrl("file:///android_asset/about.html");
    }
}
