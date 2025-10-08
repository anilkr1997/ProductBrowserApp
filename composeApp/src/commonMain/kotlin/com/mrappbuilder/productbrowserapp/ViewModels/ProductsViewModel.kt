package com.mrappbuilder.productbrowserapp.ViewModels


import com.mrappbuilder.productbrowserapp.DataClass.Category
import com.mrappbuilder.productbrowserapp.DataClass.Product
import com.mrappbuilder.productbrowserapp.UseCase.GetProductByCategory
import com.mrappbuilder.productbrowserapp.UseCase.GetProductCategory
import com.mrappbuilder.productbrowserapp.UseCase.GetProductsUseCase
import com.mrappbuilder.productbrowserapp.UseCase.SearchProductsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val getProducts: GetProductsUseCase,
    private val searchProducts: SearchProductsUseCase,
    private val categary: GetProductCategory,
   private val producetbycategory: GetProductByCategory
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products
    private val _producetcategery = MutableStateFlow<List<Category>>(emptyList())
    val productscategery: StateFlow<List<Category>> = _producetcategery

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun load() {
        scope.launch {
            _isLoading.value = true

            try {
                _products.value = getProducts()
                _producetcategery.value = categary()

            } catch (t: Throwable) {
                _error.value = t.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun search(q: String) {
        scope.launch {
            _isLoading.value = true
            try {
                _products.value = searchProducts(q)
            } catch (t: Throwable) {
                _error.value = t.message
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun productbycategory(q: String) {
        scope.launch {
            _isLoading.value = true
            try {
                _products.value = producetbycategory(q)
            } catch (t: Throwable) {
                _error.value = t.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
