package com.mrappbuilder.productbrowserapp

import androidx.compose.ui.window.ComposeUIViewController
import com.mrappbuilder.productbrowserapp.ApiHandler.createProductsViewModel

fun MainViewController() = ComposeUIViewController { App(createProductsViewModel()) }