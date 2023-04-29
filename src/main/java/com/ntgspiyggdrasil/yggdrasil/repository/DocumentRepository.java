package com.ntgspiyggdrasil.yggdrasil.repository;

import com.ntgspiyggdrasil.yggdrasil.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query("select d from Document d where d.user.department.id = ?1 and ((d.documentStatus.id = 2 or d.documentStatus.id = 3) or (d.documentStatus.id = 1 and d.user.id = ?2)) order by d.id desc")
    List<Document> findAllByUserDepartmentId(long departmentId, long userId);
    @Query("select d from Document d where d.documentStatus.id = 2 or d.documentStatus.id = 3 order by d.id desc")
    List<Document> findAllByDocumentForAdmin();
    @Query("select d from Document d where d.user.id = ?1 order by d.id desc")
    List<Document> findAllByUserId(long userId);
    @Query("select d from Document d where d.user.id = ?1 and d.id = ?2")
    Optional<Document> findByIdAndUserId(long userId, long documentId);
    @Query("select d from Document d where d.user.department.id = ?1 and d.id = ?2")
    Optional<Document> findByIdAndDepartmentId(long departmentId, long documentId);

    @Modifying
    @Transactional
    @Query("delete from Document d where d.id = ?1")
    void deleteById(long documentId);

    @Modifying
    @Transactional
    @Query(value = "update documents set document_status_id = ?2, date_update = now() from documents d join document_statuses on document_statuses.id = d.document_status_id  where documents.id = ?1 and document_statuses.id <> 4", nativeQuery = true)
    void updateDocumentStatusById(long documentId, long documentStatusId);

    @Modifying
    @Transactional
    @Query(value = "update documents set document_data = to_json(?2), date_update = now() from documents d join document_statuses on document_statuses.id = d.document_status_id  where documents.id = ?1 and document_statuses.id <> 4", nativeQuery = true)
    void updateDocumentDataById(long documentId, String documentData);

//    @Query("select d from Document d where d.user.id = ?1 and d.documentStatus.id = ?2 and d.documentStructure.documentType.id = ?3")
    @Query("select d from Document d where d.user.id = ?1 and d.documentStatus.id = ?2 and d.documentStructure.documentType.id = ?3 and d.dateCreate >= ?4 and d.dateCreate <= ?5")
    List<Document> findCountDocumentUser(long userId, long statusId, long typeId, Date minDate, Date maxDate);

    @Query("select d from Document d where d.user.department.id = ?1 and d.documentStatus.id = ?2 and d.documentStructure.documentType.id = ?3 and d.dateCreate >= ?4 and d.dateCreate <= ?5")
    List<Document> findCountDocumentByDepartmentId(long departmentId, long statusId, long typeId, Date minDate, Date maxDate);

    @Query("select d from Document d where d.user.department.faculty.id = ?1 and d.documentStatus.id = ?2 and d.documentStructure.documentType.id = ?3 and d.dateCreate >= ?4 and d.dateCreate <= ?5")
    List<Document> findCountDocumentByFacultyId(long facultyId, long statusId, long typeId, Date minDate, Date maxDate);
    @Query("select d from Document d where d.documentStatus.id = ?1 and d.documentStructure.documentType.id = ?2 and d.dateCreate >= ?3 and d.dateCreate <= ?4")
    List<Document> findCountDocumentAll(long statusId, long typeId, Date minDate, Date maxDate);
}