package com.hu.fypimplbackend.fileupload.services

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.stream.Stream

interface IFileStorageService {
    fun init()
    fun save(file: MultipartFile?): String
    fun load(filename: String?): Resource?
    fun deleteAll()
    fun loadAll(): Stream<Path?>?
}