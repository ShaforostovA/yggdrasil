package com.ntgspiyggdrasil.yggdrasil.repository;

import com.ntgspiyggdrasil.yggdrasil.models.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = "select d.id, d.date_create, d.user_id, d.document_status_id, d.document_structure_id, d.date_update, d.can_change, d.document_data\n" +
            "from documents as d\n" +
            "         inner join document_structures ds on d.document_structure_id = ds.id\n" +
            "         inner join document_types dt on ds.document_type_id = dt.id\n" +
            "         inner join document_statuses s on d.document_status_id = s.id\n" +
            "         inner join users u on d.user_id = u.id\n" +
            "where u.department_id = ?1\n" +
            "    and ((d.user_id <> ?2 and s.id <> 1 and lower(s.name) like lower(concat('%',?4,'%'))  and d.date_create >= ?5 and d.date_create < ?6 and lower(dt.name) like lower(concat('%',?7,'%')) and (lower(concat(ds.name,' ', d.id)) like lower(concat('%',?3,'%')) or lower(concat(u.last_name,' ',u.name,' ',u.patronymic)) like lower(concat('%',?3,'%'))))\n" +
            "    or (lower(s.name) like lower(concat('%',?4,'%')) and d.date_create >= ?5 and d.date_create < ?6 and lower(dt.name) like lower(concat('%',?7,'%')) and d.user_id = ?2 and (lower(concat(ds.name,' ', d.id)) like lower(concat('%',?3,'%')) or lower(concat(u.last_name,' ',u.name,' ',u.patronymic)) like lower(concat('%',?3,'%')))))", nativeQuery = true)
    Page<Document> findAllByUserDepartmentId(long departmentId, long userId, String parameter, String statusName, Date minDate, Date maxDate, String typeName, Pageable pageable);

    @Query(value = "select distinct d.id, d.date_create, d.user_id, d.document_status_id, d.document_structure_id, d.date_update, d.can_change, d.document_data\n" +
            "from documents as d\n" +
            "         inner join document_attached_keywords dak on d.id = dak.document_id\n" +
            "         inner join key_words kw on dak.keyword_id = kw.id\n" +
            "         inner join document_structures ds on d.document_structure_id = ds.id\n" +
            "         inner join document_types dt on ds.document_type_id = dt.id\n" +
            "         inner join document_statuses s on d.document_status_id = s.id\n" +
            "         inner join users u on d.user_id = u.id\n" +
            "where u.department_id = ?1\n" +
            "    and ((d.user_id <> ?2 and s.id <> 1 and lower(s.name) like lower(concat('%',?4,'%')) and d.date_create >= ?5 and d.date_create < ?6 and lower(dt.name) like lower(concat('%',?7,'%')) and (lower(kw.name) like lower(concat('%',?3,'%')) or lower(concat('%',?3,'%')) like lower(concat('%',kw.name,'%'))))\n" +
            "         or (d.user_id = ?2 and lower(s.name) like lower(concat('%',?4,'%')) and d.date_create >= ?5 and d.date_create < ?6 and lower(dt.name) like lower(concat('%',?7,'%')) and (lower(kw.name) like lower(concat('%',?3,'%')) or lower(concat('%',?3,'%')) like lower(concat('%',kw.name,'%')))))", nativeQuery = true)
    Page<Document> findAllByDepartmentAndKeyWord(long departmentId, long userId, String keyWords, String statusName, Date minDate, Date maxDate, String typeName, Pageable pageable);

    @Query(value = "select d.id, d.date_create, d.user_id, d.document_status_id, d.document_structure_id, d.date_update, d.can_change, d.document_data\n" +
            "from documents as d\n" +
            "    inner join document_structures ds on d.document_structure_id = ds.id\n" +
            "    inner join document_types dt on ds.document_type_id = dt.id\n" +
            "    inner join document_statuses s on d.document_status_id = s.id\n" +
            "    inner join users u on d.user_id = u.id\n" +
            "    where d.document_status_id <> 1 and lower(s.name) like lower(concat('%',?2,'%')) and d.date_create >= ?3 and d.date_create < ?4 and lower(dt.name) like lower(concat('%',?5,'%')) and (lower(concat(ds.name,' ', d.id)) like lower(concat('%',?1,'%')) or lower(concat(u.last_name,' ',u.name,' ',u.patronymic)) like lower(concat('%',?1,'%')))", nativeQuery = true)
    Page<Document> findAllByDocumentForAdmin(String parameter, String statusName, Date minDate, Date maxDate, String typeName, Pageable pageable);
    @Query(value = "select distinct d.id, d.date_create, d.user_id, d.document_status_id, d.document_structure_id, d.date_update, d.can_change, d.document_data\n" +
            "from documents as d\n" +
            "         inner join document_attached_keywords dak on d.id = dak.document_id\n" +
            "         inner join key_words kw on dak.keyword_id = kw.id\n" +
            "         inner join document_structures ds on d.document_structure_id = ds.id\n" +
            "         inner join document_types dt on ds.document_type_id = dt.id\n" +
            "         inner join document_statuses s on d.document_status_id = s.id\n" +
            "         inner join users u on d.user_id = u.id\n" +
            "    where d.document_status_id <> 1\n" +
            "       and lower(s.name) like lower(concat('%',?2,'%'))\n" +
            "       and d.date_create >= ?3\n" +
            "       and d.date_create < ?4\n" +
            "       and lower(dt.name) like lower(concat('%',?5,'%'))\n" +
            "       and (lower(kw.name) like lower(concat('%',?1,'%')) or lower(concat('%',?1,'%')) like lower(concat('%',kw.name,'%')))", nativeQuery = true)
    Page<Document> findAllByDocumentForAdminKeyWord(String keyWords, String statusName, Date minDate, Date maxDate, String typeName, Pageable pageable);

    @Query(value = "select d.id, d.date_create, d.user_id, d.document_status_id, d.document_structure_id, d.date_update, d.can_change, d.document_data\n" +
            "from documents as d\n" +
            "    inner join document_structures ds on d.document_structure_id = ds.id\n" +
            "    inner join document_types dt on ds.document_type_id = dt.id\n" +
            "    inner join document_statuses s on d.document_status_id = s.id\n" +
            "    where d.user_id = ?1 and lower(s.name) like lower(concat('%',?3,'%')) and d.date_create >= ?4 and d.date_create < ?5 and lower(dt.name) like lower(concat('%',?6,'%')) and lower(concat(ds.name,' ', d.id)) like lower(concat('%',?2,'%'))", nativeQuery = true)
    Page<Document> findAllByUserIdPageable(long userId, String parameter, String statusName, Date minDate, Date maxDate, String typeName, Pageable pageable);

    @Query(value = "select distinct d.id, d.date_create, d.user_id, d.document_status_id, d.document_structure_id, d.date_update, d.can_change, d.document_data\n" +
            "from documents as d\n" +
            "    inner join document_attached_keywords dak on d.id = dak.document_id\n" +
            "    inner join key_words kw on dak.keyword_id = kw.id\n" +
            "    inner join document_structures ds on d.document_structure_id = ds.id\n" +
            "    inner join document_types dt on ds.document_type_id = dt.id\n" +
            "    inner join document_statuses s on d.document_status_id = s.id\n" +
            "    where d.user_id = ?1 and lower(s.name) like lower(concat('%',?3,'%')) and d.date_create >= ?4 and d.date_create < ?5  and lower(dt.name) like lower(concat('%',?6,'%')) and (lower(kw.name) like lower(concat('%',?2,'%')) or lower(concat('%',?2,'%')) like lower(concat('%',kw.name,'%')))", nativeQuery = true)
    Page<Document> findAllByUserIdPageableKeyWord(long userId, String keyWords, String statusName, Date minDate, Date maxDate, String typeName, Pageable pageable);
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


//    @Query("select d from Document d where d.user.id = ?1 and d.documentStatus.id = ?2 and d.documentStructure.documentType.id = ?3")
    @Query("select d from Document d where d.user.id = ?1 and d.documentStatus.id = ?2 and d.documentStructure.documentType.id = ?3 and d.dateCreate >= ?4 and d.dateCreate < ?5")
    List<Document> findCountDocumentUser(long userId, long statusId, long typeId, Date minDate, Date maxDate);

    @Query("select d from Document d where d.user.department.id = ?1 and d.documentStatus.id = ?2 and d.documentStructure.documentType.id = ?3 and d.dateCreate >= ?4 and d.dateCreate < ?5")
    List<Document> findCountDocumentByDepartmentId(long departmentId, long statusId, long typeId, Date minDate, Date maxDate);

    @Query("select d from Document d where d.user.department.faculty.id = ?1 and d.documentStatus.id = ?2 and d.documentStructure.documentType.id = ?3 and d.dateCreate >= ?4 and d.dateCreate < ?5")
    List<Document> findCountDocumentByFacultyId(long facultyId, long statusId, long typeId, Date minDate, Date maxDate);
    @Query("select d from Document d where d.documentStatus.id = ?1 and d.documentStructure.documentType.id = ?2 and d.dateCreate >= ?3 and d.dateCreate < ?4")
    List<Document> findCountDocumentAll(long statusId, long typeId, Date minDate, Date maxDate);

    @Modifying
    @Transactional
    @Query(value = "update documents d set document_status_id = 3, date_update = now() from document_statuses where document_statuses.id = 2 and d.document_structure_id = ?1 and d.document_status_id = document_statuses.id", nativeQuery = true)
    void updateDocumentStatusRemake(long documentStructureId);

    @Modifying
    @Transactional
    @Query(value = "update documents set can_change = ?2, date_update = now() where documents.document_structure_id = ?1 and documents.document_status_id <> 4", nativeQuery = true)
    void revActivateDocuments(long documentStructureId, Boolean canChange);

    @Query("select d from Document d where d.documentStructure.id = ?1 and d.documentStatus.id = ?2 order by d.id desc")
    List<Document> findAllByDocumentStructureIdAndStatus(long documentStructureId, long statusId);

    @Modifying
    @Transactional
    @Query(value = "update documents set document_structure_id = ?2, date_update = now() where documents.document_structure_id = ?1 and documents.document_status_id <> 4", nativeQuery = true)
    void replacementDocumentStructure(long findDocumentStructureId, long newDocumentStructureId);

    @Query("select d from Document d where d.documentStructure.id = ?1 and d.user.department.faculty.id = ?2 and d.dateCreate >= ?3 and d.dateCreate <= ?4 and (d.documentStatus.id = 2 or d.documentStatus.id = 4)")
    List<Document> findDocumentAllByStructureIdFacultyId(long structureId, long facultyId, Date minDate, Date maxDate);

    @Query("select d from Document d where d.documentStructure.id = ?1 and d.user.department.id = ?2 and d.dateCreate >= ?3 and d.dateCreate <= ?4 and (d.documentStatus.id = 2 or d.documentStatus.id = 4)")
    List<Document> findDocumentAllByStructureIdDepartmentId(long structureId, long departmentId, Date minDate, Date maxDate);

    @Modifying
    @Transactional
    @Query(value = "update documents set document_status_id = ?2, date_update = now() where documents.document_structure_id = ?1 and documents.document_status_id = 2 and documents.date_create >= ?3 and documents.date_create < ?4", nativeQuery = true)
    void archivingDocuments(long findDocumentStructureId, long documentStatusId, Date minDate, Date maxDate);

    @Query("select d from Document d where d.documentStructure.id = ?1 and (d.documentStatus.id = 2 or d.documentStatus.id = 4) and d.user.department.faculty.id = ?2 and d.dateCreate >= ?3 and d.dateCreate < ?4")
    List<Document> findAllByFacultyForExport(long documentStructureId, long facultyId, Date minDate, Date maxDate);

    @Query("select d from Document d where d.documentStructure.id = ?1 and (d.documentStatus.id = 2 or d.documentStatus.id = 4) and d.user.department.id = ?2 and d.dateCreate >= ?3 and d.dateCreate < ?4")
    List<Document> findAllByDepartmentForExport(long documentStructureId, long departmentId, Date minDate, Date maxDate);
}