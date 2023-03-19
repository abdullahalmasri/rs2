package com.company.labeling.dao.sql;

import com.company.labeling.dao.LabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LabelRepo extends JpaRepository<LabelEntity,Long> {

    @Query("select l " +
            "from LabelEntity l where l.name=:str")
    LabelEntity findByName(String str);

}
