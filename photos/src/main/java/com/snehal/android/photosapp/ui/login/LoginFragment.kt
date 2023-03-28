package com.snehal.android.photosapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.snehal.android.photosapp.R
import com.snehal.android.photosapp.ViewModel
import com.snehal.android.photosapp.databinding.FragmentLoginBinding
import com.snehal.android.photosapp.ui.photos.PhotoFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnContinue.setOnClickListener {
            binding.progressBarLogin.visibility= View.VISIBLE
            viewModel.getLogin(binding.edtUser.text.toString(),binding.edtPass.text.toString())
            moveToDashboard()
        }
    }

    private fun moveToDashboard() {
        binding.progressBarLogin.visibility= View.GONE
        val fragment = PhotoFragment.newInstance()
        requireFragmentManager().beginTransaction().apply {
            replace(R.id.fragmentContainerHome, fragment, "Photo_Fragment")
            addToBackStack("Photo_Fragment")
            commit()
        }
    }

}