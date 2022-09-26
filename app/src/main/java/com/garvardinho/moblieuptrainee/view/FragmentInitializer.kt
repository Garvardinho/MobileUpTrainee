package com.garvardinho.moblieuptrainee.view

import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

const val KEY_PARAM = "KEY_PARAM"

interface FragmentInitializer<T: Parcelable> {

    fun newInstance(arg: T): Fragment {
        val declaringClassName = this::class.java.declaringClass?.name
        return FragmentFactory().instantiate(
            this::class.java.classLoader!!,
            declaringClassName!!
        ).apply {
            arguments = bundleOf(
                KEY_PARAM to arg
            )
        }
    }
}