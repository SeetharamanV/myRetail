package com.myretail.products.prices.repositories

import com.myretail.products.prices.entities.PricesDocument
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PricesRepository : MongoRepository<PricesDocument, ObjectId> {
    fun findByProductId(productId: Long): PricesDocument?
}
