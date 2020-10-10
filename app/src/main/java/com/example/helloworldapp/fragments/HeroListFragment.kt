package com.example.helloworldapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworldapp.R
import com.example.helloworldapp.adapters.HeroRecyclerViewAdapter
import com.example.helloworldapp.models.Hero
import com.example.helloworldapp.viewmodels.HeroViewModel

class HeroListFragment : Fragment(), HeroRecyclerViewAdapter.OnHeroClickListener {

    private lateinit var viewModel: HeroViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hero_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (view is RecyclerView) {
            view.layoutManager = LinearLayoutManager(context)

            viewModel = ViewModelProvider(requireActivity()).get(HeroViewModel::class.java)
            viewModel.getHeroesList().observe(viewLifecycleOwner, Observer {
                view.adapter = HeroRecyclerViewAdapter(viewModel.getHeroesList().value!!, this)
            })
        }
    }

    override fun onHeroClick(hero: Hero) {
        viewModel.setSelectedHero(hero)
        val detailFragment = HeroDetailFragment()
        requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit()
    }
}