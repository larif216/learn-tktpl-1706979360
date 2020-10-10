package com.example.helloworldapp.fragments

import android.os.Build
import android.os.Bundle
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.helloworldapp.R
import com.example.helloworldapp.viewmodels.HeroViewModel

/**
 * A fragment representing a list of Items.
 */
class HeroDetailFragment : Fragment() {

    private lateinit var viewModel: HeroViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hero_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(HeroViewModel::class.java)
        viewModel.getSelectedHero().observe(viewLifecycleOwner, Observer {
            Glide.with(view.context)
                    .load(viewModel.getSelectedHero().value?.photo)
                    .apply(RequestOptions().override(350,350))
                    .into(view.findViewById(R.id.detail_photo))
            view.findViewById<TextView>(R.id.detail_name).text = viewModel.getSelectedHero().value?.name
            view.findViewById<TextView>(R.id.detail_description).text = viewModel.getSelectedHero().value?.description
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                view.findViewById<TextView>(R.id.detail_description).justificationMode = JUSTIFICATION_MODE_INTER_WORD
            }
        })
    }
}