package com.example.fingerprint.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fingerprint.R
import com.example.fingerprint.databinding.FragmentWelcomeScreenBinding
import com.example.fingerprint.utils.PrefsHelper

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var count = PrefsHelper.read(PrefsHelper.COUNT, 0)
        PrefsHelper.write(PrefsHelper.COUNT, 0)
        binding.failedMsg.text = getString(R.string.failed_count, count)
    }
}