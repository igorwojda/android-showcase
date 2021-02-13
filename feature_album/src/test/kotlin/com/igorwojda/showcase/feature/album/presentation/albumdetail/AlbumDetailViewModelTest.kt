package com.igorwojda.showcase.feature.album.presentation.albumdetail

import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel.ViewState
import com.igorwojda.showcase.library.testutils.CoroutinesTestExtension
import com.igorwojda.showcase.library.testutils.InstantTaskExecutorExtension
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class AlbumDetailViewModelTest {

    @ExperimentalCoroutinesApi
    @JvmField
    @RegisterExtension
    val coroutinesTestExtension = CoroutinesTestExtension()

    @JvmField
    @RegisterExtension
    val instantTaskExecutorExtension = InstantTaskExecutorExtension()

    @MockK
    internal lateinit var mockGetAlbumUseCase: GetAlbumUseCase

    @MockK
    internal lateinit var mockAlbumDetailFragmentArgs: AlbumDetailFragmentArgs

    private lateinit var cut: AlbumDetailViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        cut = AlbumDetailViewModel(
            mockGetAlbumUseCase,
            mockAlbumDetailFragmentArgs
        )
    }

    @Test
    fun `verify state when GetAlbumUseCase return null`() {
        // given
        val albumName = "Thriller"
        val artistName = "Michael Jackson"
        val mbId = "123"

        every { mockAlbumDetailFragmentArgs.albumName } returns albumName
        every { mockAlbumDetailFragmentArgs.artistName } returns artistName
        every { mockAlbumDetailFragmentArgs.mbId } returns mbId
        coEvery {
            mockGetAlbumUseCase.execute(artistName, albumName, mbId)
        } returns GetAlbumUseCase.Result.Error(Exception())

        // when
        cut.loadData()

        // then
        cut.stateLiveData.value shouldBeEqualTo ViewState(
            isLoading = false,
            isError = true,
            artistName = "",
            albumName = "",
            coverImageUrl = ""
        )
    }
}
