package com.example.helloworldapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.myrecyclerview.HeroesData
import com.example.helloworldapp.models.Hero

class HeroViewModel : ViewModel() {
    private val heroesData = MutableLiveData<List<Hero>>()
    private var selectedHero = MutableLiveData<Hero>()

    init {
        heroesData.value = HeroesData().getListData()
    }

    fun getHeroesList(): MutableLiveData<List<Hero>> {
        return heroesData
    }

    fun setSelectedHero(item: Hero) {
        selectedHero.value = item
    }

    fun getSelectedHero(): MutableLiveData<Hero> {
        return selectedHero
    }
}