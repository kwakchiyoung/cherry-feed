package com.bazzi.cherryfeed.apps.file.repository;

import com.bazzi.cherryfeed.apps.file.domain.FileStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileStore, Long> {
    Optional<FileStore> findByFileName(String fileName);
}
