package com.igorwojda.showcase.feature.base.data.retrofit

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.CancellationException
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class ApiResultCallTest {
    private val delegate = mockk<Call<String>>()
    private val delegateCallback = slot<Callback<String>>()
    private val sut = ApiResultCall(delegate)
    private var result: ApiResult<String>? = null
    private var failure: Throwable? = null

    private fun enqueue() {
        every { delegate.enqueue(capture(delegateCallback)) } answers { Unit }
        every { delegate.isCanceled } returns false
        sut.enqueue(
            object : Callback<ApiResult<String>> {
                override fun onResponse(
                    call: Call<ApiResult<String>>,
                    response: Response<ApiResult<String>>,
                ) {
                    result = response.body()
                }

                override fun onFailure(
                    call: Call<ApiResult<String>>,
                    throwable: Throwable,
                ) {
                    failure = throwable
                }
            },
        )
    }

    @Test
    fun `all HTTP errors reach the caller with their status`() {
        enqueue()
        for (code in listOf(401, 403, 404, 429, 500, 503)) {
            result = null
            delegateCallback.captured.onResponse(delegate, Response.error(code, "error".toResponseBody()))
            assertTrue(result is ApiResult.Error)
            assertEquals(code, (result as ApiResult.Error).code)
        }
    }

    @Test
    fun `IO failure is captured for classification`() {
        enqueue()
        val exception = IOException("offline")
        delegateCallback.captured.onFailure(delegate, exception)
        assertSame(exception, (result as ApiResult.Exception).throwable)
    }

    @Test
    fun `cancelled calls keep the failure callback`() {
        enqueue()
        every { delegate.isCanceled } returns true
        val exception = IOException("cancelled")
        delegateCallback.captured.onFailure(delegate, exception)
        assertSame(exception, failure)
        assertEquals(null, result)
    }

    @Test
    fun `cancellation exception is never an API result`() {
        enqueue()
        val exception = CancellationException()
        delegateCallback.captured.onFailure(delegate, exception)
        assertSame(exception, failure)
        assertEquals(null, result)
    }
}
