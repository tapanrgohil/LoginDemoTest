package com.test.logindemotest.ui

import com.test.logindemotest.R


import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.test.logindemotest.databinding.UserProfileFragmentBinding

@AndroidEntryPoint
class UserProfileFragment : Fragment(R.layout.user_profile_fragment) {


    private val viewModel by viewModels<UserProfileViewModel>()
    private lateinit var binding:UserProfileFragmentBinding

    private val extra by navArgs<UserProfileFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = UserProfileFragmentBinding.bind(view)
        initUi()
    }


    private fun initUi() {
        binding.tvHello.text = getString(R.string.hello,extra.userId)

    }

}