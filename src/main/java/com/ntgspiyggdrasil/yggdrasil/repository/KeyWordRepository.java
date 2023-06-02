package com.ntgspiyggdrasil.yggdrasil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ntgspiyggdrasil.yggdrasil.models.KeyWord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface KeyWordRepository extends JpaRepository<KeyWord, Long> {
    Optional<KeyWord> findByName(String keyWordName);
    Boolean existsByName(String keyWordName);
    @Query(value = "select key_words.id, key_words.name, key_words.date_create, key_words.creator_id from key_words inner join document_attached_keywords dak on key_words.id = dak.keyword_id where dak.document_id = ?1", nativeQuery = true)
    Set<KeyWord> findByDocumentId(long documentId);

    @Query(value = "insert into document_attached_keywords values(?1, ?2) RETURNING *", nativeQuery = true)
    long attachDocumentKeyWord(long documentId, long keyWordId);
    @Query(value = "delete from document_attached_keywords kw where kw.document_id = ?1 and kw.keyword_id = ?2 RETURNING *", nativeQuery = true)
    long deleteAttachDocumentKeyWord(long documentId, long keyWordId);

    @Query(value = "delete from document_attached_keywords kw where kw.keyword_id = ?1 RETURNING *", nativeQuery = true)
    List<Long> deleteAttachDocumentKeyWord(long keyWordId);
}
