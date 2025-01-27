package com.example.domain.usecase

import com.example.common.UiStatus
import com.example.common.abstraction.FileDownloader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DownloadImageUseCase @Inject constructor(
    private val fileDownloader: FileDownloader,
    private val cacheDirFilePath: String
) {

    operator fun invoke(downloadUrl: String) = flow<UiStatus<Unit>> {
        emit(UiStatus.Loading())
        //init FileDownload
        emit(
            UiStatus.Success(
                fileDownloader.downloadResource(
                    downloadUrl = downloadUrl,
                    destPath = cacheDirFilePath
                )
            )
        )
    }.catch {
        emit(UiStatus.Failure(it.message))
    }.flowOn(Dispatchers.IO)
}