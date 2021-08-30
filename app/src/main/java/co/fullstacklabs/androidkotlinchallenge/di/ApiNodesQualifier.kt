package co.fullstacklabs.androidkotlinchallenge.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

object ApiNodesQualifier : Qualifier {
    override val value: QualifierValue
        get() = "ApiNodesQualifier"
}