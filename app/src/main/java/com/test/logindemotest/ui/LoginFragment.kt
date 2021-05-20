package com.test.logindemotest.ui

import com.test.logindemotest.R

import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.logindemotest.databinding.LoginFragmentBinding

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.login_fragment) {


    lateinit var binding: LoginFragmentBinding

    private val viewModel by viewModels<LoginViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LoginFragmentBinding.bind(view)
        initUi()
        attachObserver()
    }

    private fun attachObserver() {
        handleResponse(viewModel.loginLiveData, binding.progress) {
            navigateToUserProfile(it)
        }
    }

    private fun navigateToUserProfile(userId: String) {
        findNavController()
            .navigate(LoginFragmentDirections.actionLoginFragmentToUserProfileFragment(userId))
    }

    private fun initUi() {
        binding.btLogin.setOnClickListener {
            validFormAndLogin()
        }
        binding.etUserId.editText?.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE
                && event.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                validFormAndLogin()
                return@setOnEditorActionListener true;
            }
            return@setOnEditorActionListener false
        }
    }

    private fun validFormAndLogin() {
        binding.apply {
            etPassword.editText?.doAfterTextChanged { text ->
                etPassword.error = null
                etPassword.isErrorEnabled = false
            }
            etUserId.editText?.doAfterTextChanged { text ->
                etUserId.error = null
                etPassword.isErrorEnabled = false
            }
            var isPass = true
            val userId = etUserId.editText?.text?.toString()
            val password = etPassword.editText?.text?.toString()
            if (userId.isNullOrEmpty()) {
                etUserId.error = "UserId is required"
                isPass = false
            } else if (userId.length != 6) {
                etUserId.error = "UserId must be 6 digit"
                isPass = false
            }
            if (password.isNullOrEmpty()) {
                etPassword.error = "password is required"
                isPass = false
            }
            if (isPass) {
                viewModel.login(userId.orEmpty(), password.orEmpty())
            } else {
                return
            }
        }
    }

}