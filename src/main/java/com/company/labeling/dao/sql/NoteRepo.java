package com.company.labeling.dao.sql;

import com.company.labeling.dao.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoteRepo extends JpaRepository<NoteEntity,Long> {
//    Page<NoteEntity> findAllByContent (Pageable pageable);
}
