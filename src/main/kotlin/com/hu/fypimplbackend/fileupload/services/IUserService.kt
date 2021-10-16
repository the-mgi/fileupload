package com.hu.fypimplbackend.fileupload.services

import org.springframework.web.multipart.MultipartFile

interface IUserService {
    fun downloadImage(username: String): ByteArray
    fun updateProfileImage(username: String, multipartFile: MultipartFile): Pair<String, String>
}