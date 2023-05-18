package com.example.demo_instaforfood.Fragments.ReviewsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo_instaforfood.Models.MenuItemRating
import com.example.demo_instaforfood.Paging.ReveiwsImagesPagingAdapter
import com.example.demo_instaforfood.ViewModels.AppViewModel
import com.example.demo_instaforfood.databinding.FragmentReviewsBinding


class ReviewsFragment : Fragment() {

    lateinit var reviewsBinding: FragmentReviewsBinding
    lateinit var appViewModel: AppViewModel
    lateinit var adapter: ReveiwsImagesPagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        reviewsBinding = FragmentReviewsBinding.inflate(inflater)

        val menuRatinglist = listOf(
            MenuItemRating("+9",175),
            MenuItemRating("8",85),
            MenuItemRating("7",22),
            MenuItemRating("6",34),
            MenuItemRating("1 to 5",24)
        )

        val MenuRatingAdapter = MenuRatingAdapter(menuRatinglist)
        reviewsBinding.rvMenuRatings.layoutManager = LinearLayoutManager(requireContext())
        reviewsBinding.rvMenuRatings.adapter = MenuRatingAdapter

        appViewModel = ViewModelProvider(requireActivity()).get(AppViewModel::class.java)
        adapter = ReveiwsImagesPagingAdapter()
        reviewsBinding.rvReviewImages.layoutManager = GridLayoutManager(requireContext(),3)
        reviewsBinding.rvReviewImages.adapter = adapter
        
        appViewModel.list.observe(requireActivity(),{
            adapter.submitData(lifecycle,it)
        })

        return reviewsBinding.root
    }




}