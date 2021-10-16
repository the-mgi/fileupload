package com.hu.fypimplbackend.fileupload.serviceImpls

import com.hu.fypimplbackend.fileupload.config.AWSApplicationConfig
import com.hu.fypimplbackend.fileupload.services.IFileStore
import com.hu.fypimplbackend.fileupload.services.IUserService
import org.apache.http.entity.ContentType
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.*

@Service
class UserServiceImpl(
    @Autowired
    private val iFileStore: IFileStore,

    @Autowired
    private val awsApplicationConfig: AWSApplicationConfig,
    @Autowired
    private val loggerFactory: Logger

) : IUserService {
    override fun downloadImage(username: String): ByteArray {
//        val user = this.userRepository.getByUsername(username)
        return this.iFileStore.download("dummy path", "dummy file name")
    }

    override fun updateProfileImage(username: String, multipartFile: MultipartFile): Pair<String, String> {
        if (multipartFile.isEmpty) {
            throw IllegalStateException("Cannot upload empty file")
        }
        if (!listOf(
                ContentType.IMAGE_PNG.mimeType,
                ContentType.IMAGE_BMP.mimeType,
                ContentType.IMAGE_GIF.mimeType,
                ContentType.IMAGE_JPEG.mimeType
            ).contains(multipartFile.contentType)
        ) {
            throw IllegalStateException("FIle uploaded is not an image")
        }

        // getting the file metadata
        val fileMetadata = hashMapOf(
            "Content-Type" to multipartFile.contentType!!,
            "Content-Length" to multipartFile.size.toString()
        )
        val path = "${this.awsApplicationConfig.profileImageBucket}/${UUID.randomUUID()}"
        val fileName = multipartFile.originalFilename!!

        try {
            this.iFileStore.upload(path, fileName, Optional.of(fileMetadata), multipartFile.inputStream)
            loggerFactory.info("Image uploaded successfully")
            return Pair(path, fileName)
        } catch (e: IOException) {
            throw IllegalStateException("Failed to upload file", e)
        }
    }
}