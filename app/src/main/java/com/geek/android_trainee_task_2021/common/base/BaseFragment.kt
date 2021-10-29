package com.geek.android_trainee_task_2021.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<Vb : ViewBinding> :
    Fragment() {

    internal lateinit var ui: Vb
    abstract fun bind(): Vb


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        ui = bind()
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUI()
        setupListeners()
    }

    abstract fun setupListeners()

    abstract fun setupUI()

}