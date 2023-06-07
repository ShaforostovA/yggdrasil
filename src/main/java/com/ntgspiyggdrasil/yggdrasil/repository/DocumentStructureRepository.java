package com.ntgspiyggdrasil.yggdrasil.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ntgspiyggdrasil.yggdrasil.models.DocumentStructure;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentStructureRepository extends JpaRepository<DocumentStructure, Long> {
//    @Query(value = "select * from document_structures order by document_structures.id desc", nativeQuery = true)
    @Query("select ds from DocumentStructure ds where lower(concat(ds.id, ' ', ds.name)) like lower(concat('%',?1,'%')) and lower(ds.documentType.name) like lower(concat('%', ?2, '%')) and (ds.isActive = coalesce(?3, ds.isActive)) and ds.dateCreate >= ?4 and ds.dateCreate < ?5")
    Page<DocumentStructure> findAllSort(String parameter, String documentTypeName, Boolean isActive, Date minDate, Date maxDate, Pageable pageable);
    @Query(value = "select * from document_structures where document_structures.is_active = ?1 order by document_structures.id desc", nativeQuery = true)
    List<DocumentStructure> findAllByIsActive(Boolean isActive);

    @Modifying
    @Transactional
    @Query(value = "update document_structures set name = ?2, description = ?3, document_type_id = ?4, document_structure = to_json(?5), date_update = now() where document_structures.id = ?1", nativeQuery = true)
    void updateDocumentStructure(long documentStructureId, String name, String description, long documentTypeId, String structureData);

    @Modifying
    @Transactional
    @Query(value = "update document_structures set is_active = ?2, date_update = now() where document_structures.id = ?1", nativeQuery = true)
    void updateStatusDocumentStructure(long documentStructureId, Boolean isActive);

    Boolean existsById(long documentStructureId);

    List<DocumentStructure> findAllByOldStructureId(long oldStructureId);
}
