package com.myretail.products.prices.exceptions

data class PricesExceptionConstant(val code: String, val message: String)

object PricesExceptionConstants {
    val PRICES_NOT_FOUND = PricesExceptionConstant(code = "PA-4040", message = "Couldn't find prices for the given product id.")
    val PRICES_DATABASE_EXCEPTION = PricesExceptionConstant(code = "PA-5000", message = "Exception occurred while attempting to update prices for product id.")
}
