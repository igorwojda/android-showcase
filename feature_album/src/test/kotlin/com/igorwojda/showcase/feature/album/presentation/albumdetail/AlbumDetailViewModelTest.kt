package com.igorwojda.showcase.feature.album.presentation.albumdetail

import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel.State
import com.igorwojda.showcase.library.testutils.CoroutinesTestExtension
import com.igorwojda.showcase.library.testutils.InstantTaskExecutorExtension
import io.mockk.coEvery
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class AlbumDetailViewModelTest {

    @JvmField
    @RegisterExtension
    val instantTaskExecutorExtension = InstantTaskExecutorExtension()

    @JvmField
    @RegisterExtension
    val coroutinesTestExtension = CoroutinesTestExtension()

    private val mockGetAlbumUseCase: GetAlbumUseCase = mockk()

    private val cut = AlbumDetailViewModel(
        mockGetAlbumUseCase
    )

    @Test
    fun `verify state when GetAlbumUseCase return null`() {
        // given
        val albumName = "Thriller"
        val artistName = "Michael Jackson"
        val mbId2 = "123"

        val mockAlbumDetailFragmentArgs = AlbumDetailFragmentArgs(albumName, artistName, mbId2)

        coEvery {
            mockGetAlbumUseCase.execute(artistName, albumName, mbId2)
        } returns GetAlbumUseCase.Result.Error(Exception())

        // when
        cut.onEnter(mockAlbumDetailFragmentArgs)

        // then
        cut.stateLiveData.value shouldBeEqualTo State(
            isLoading = false,
            isError = true,
            artistName = "",
            albumName = "",
            coverImageUrl = ""
        )
    }
}
