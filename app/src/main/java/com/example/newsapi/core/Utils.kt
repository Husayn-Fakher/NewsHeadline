package com.example.newsapi.core

import com.example.newsapi.core.Constants.DEFAULT_IMAGE_URL
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class Utils {


    companion object {
        fun encodeImageUrl(url: String?): String {
            var encoded: String

            try {
                encoded = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
            } catch (e: UnsupportedEncodingException) {

                encoded = URLEncoder.encode(DEFAULT_IMAGE_URL, StandardCharsets.UTF_8.toString())
            }

            if (url.equals("")) {
                encoded = URLEncoder.encode(DEFAULT_IMAGE_URL, StandardCharsets.UTF_8.toString())

            }

            return encoded
        }

        fun encodeNewsUrl(url: String?): String {

            var encoded: String

            try {
                encoded = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
            } catch (e: UnsupportedEncodingException) {

                encoded =
                    URLEncoder.encode("https://www.google.com/", StandardCharsets.UTF_8.toString())
            }

            if (url.equals("")) {
                encoded =
                    URLEncoder.encode("https://www.google.com/", StandardCharsets.UTF_8.toString())

            }

            return encoded
        }
    }
}