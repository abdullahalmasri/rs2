package com.company.labeling.dao.sql;

import com.company.labeling.dao.LabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepo extends JpaRepository<LabelEntity,Integer> {
}
