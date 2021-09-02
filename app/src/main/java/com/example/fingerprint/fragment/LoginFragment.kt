package com.example.fingerprint.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fingerprint.R
import com.example.fingerprint.databinding.FragmentLoginBinding
import com.example.fingerprint.utils.FingerprintCallback
import com.example.fingerprint.utils.FingerprintManager
import com.example.fingerprint.utils.PrefsHelper

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PrefsHelper.init(requireContext())

        binding.login.setOnClickListener {

            val fingerprintAuth = FingerprintManager.FingerprintBuilder(requireContext())
                .setTitle(getString(R.string.fingerprint_title))
                .setSubtitle(getString(R.string.fingerprint_subtitle))
                .setDescription(getString(R.string.fingerprint_description))
                .setNegativeButtonText(getString(R.string.fingerprint_negative_button_text))
                .build()
            //start authentication
            fingerprintAuth.authenticate(object : FingerprintCallback {
                override fun onSdkVersionNotSupported() {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.fingerprint_error_sdk_not_supported),
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onBiometricAuthenticationNotSupported() {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.fingerprint_error_hardware_not_supported),
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onBiometricAuthenticationNotAvailable() {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.fingerprint_error_fingerprint_not_available),
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onBiometricAuthenticationPermissionNotGranted() {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.fingerprint_error_permission_not_granted),
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onBiometricAuthenticationInternalError(error: String?) {
                    Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                }

                override fun onAuthenticationFailed() {
                    // Update count
                    var count = PrefsHelper.read(PrefsHelper.COUNT, 0)
                    PrefsHelper.write(PrefsHelper.COUNT, count!!.plus(1))
                }

                override fun onAuthenticationCancelled() {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.fingerprint_cancelled),
                        Toast.LENGTH_LONG
                    ).show()
                    fingerprintAuth.cancelAuthentication()
                }

                override fun onAuthenticationSuccessful() {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.fingerprint_success),
                        Toast.LENGTH_LONG
                    ).show()

                    requireActivity().supportFragmentManager.beginTransaction()
                        .add(R.id.container, WelcomeFragment()).addToBackStack(null).commit()
                }

                override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                }
            })
        }
    }
}


