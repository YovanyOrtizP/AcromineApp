package com.example.acromineapp.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acromineapp.R
import com.example.acromineapp.data.model.AcromineResponse
import com.example.acromineapp.databinding.FragmentInformationBinding
import com.example.acromineapp.util.UIState
import com.example.acromineapp.viewmodel.AcromineViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationFragment : Fragment() {

    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding!!
    private lateinit var builder: AlertDialog.Builder
    private val viewModel: AcromineViewModel by lazy {
        ViewModelProvider(requireActivity())[AcromineViewModel::class.java]
    }


    private val mAdapter by lazy {
        InformationAdapter ()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInformationBinding.inflate(inflater, container, false)

        binding.rvInformation.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        viewModel.resultSF.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS<List<AcromineResponse>> -> {
                    initViews(it.response)
                }
                is UIState.ERROR -> {
                    builder = AlertDialog.Builder(context)
                    builder.setTitle("Oops!")
                        .setMessage("Information not found, please search another word")
                        .setPositiveButton("Agree",null).show()
                }
            }
        }
        return binding.root
    }

    private fun initViews(response: List<AcromineResponse>) {
        val info = response.firstOrNull()
        info?.let {
            binding.tvAcronym.text = info.sf ?: "Not available"
            mAdapter.updateInfoAdapter(info.lfs ?: emptyList())
        }

    }
}