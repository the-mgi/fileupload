package com.hu.fypimplbackend.fileupload.serviceImpls

import com.hu.fypimplbackend.fileupload.services.IFileStorageService
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream


@Service
class FileStorageServiceImpl : IFileStorageService {
    private val root: Path = Paths.get("uploads")

    override fun init() {
        try {
            Files.createDirectory(root)
        } catch (e: IOException) {
            throw RuntimeException("Could not initialize folder for upload!")
        }
    }

    override fun save(file: MultipartFile?): String {
        try {
            Files.copy(file!!.inputStream, this.root.resolve(file.originalFilename!!))
            return Paths.get("root", file.originalFilename).toString()
        } catch (e: Exception) {
            throw RuntimeException("Could not store the file. Error: " + e.message)
        }
    }

    override fun load(filename: String?): Resource? {
        return try {
            val file = root.resolve(filename!!)
            val resource: Resource = UrlResource(file.toUri())
            if (resource.exists() || resource.isReadable) {
                resource
            } else {
                throw RuntimeException("Could not read the file!")
            }
        } catch (e: MalformedURLException) {
            throw RuntimeException("Error: " + e.message)
        }
    }

    override fun deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile())
    }

    override fun loadAll(): Stream<Path?>? {
        return try {
            Files.walk(this.root, 1).filter { path: Path -> path != this.root }.map { path: Path? ->
                this.root.relativize(
                    path!!
                )
            }
        } catch (e: IOException) {
            throw RuntimeException("Could not load the files!")
        }
    }
}