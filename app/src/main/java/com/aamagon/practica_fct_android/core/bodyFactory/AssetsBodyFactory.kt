package com.aamagon.practica_fct_android.core.bodyFactory

import co.infinum.retromock.BodyFactory
import java.io.InputStream

class AssetsBodyFactory (private val openAsset: (String) -> InputStream): BodyFactory {

    override fun create(path: String): InputStream {
        return openAsset(path)
    }
}