package com.example.newsapi.core

import com.example.newsapi.core.Constants.DEFAULT_IMAGE_URL
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UtilsTest {

    @Test
    fun `empty url returns a default url`() {

        val result = Utils.encodeImageUrl("")
        val default = Utils.encodeImageUrl(DEFAULT_IMAGE_URL)

        assertThat(result).isEqualTo(default)

    }


}