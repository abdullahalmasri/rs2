package com.company.labeling.dao.sql;

import com.company.labeling.dao.LabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepo extends JpaRepository<LabelEntity,Integer> {
}
