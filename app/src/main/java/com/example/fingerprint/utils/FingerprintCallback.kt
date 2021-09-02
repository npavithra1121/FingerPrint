package com.example.fingerprint.utils

interface FingerprintCallback {
    fun onSdkVersionNotSupported()
    fun onBiometricAuthenticationNotSupported()
    fun onBiometricAuthenticationNotAvailable()
    fun onBiometricAuthenticationPermissionNotGranted()
    fun onBiometricAuthenticationInternalError(error: String?)
    fun onAuthenticationFailed()
    fun onAuthenticationCancelled()
    fun onAuthenticationSuccessful()
    fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?)
    fun onAuthenticationError(errorCode: Int, errString: CharSequence?)
}