package com.ntgspiyggdrasil.yggdrasil.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.*;

@Data
@Entity
@Table(name = "documents")
@NoArgsConstructor
public class Document {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "document_data", columnDefinition = "jsonb")
    private String documentData;
    @Column(name = "can_change")
    private Boolean canChange;
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "date_create")
    private Date dateCreate;
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "date_update")
    private Date dateUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_structure_id", referencedColumnName = "id")
    private DocumentStructure documentStructure;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_status_id", referencedColumnName = "id")
    private DocumentStatus documentStatus;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "document_attached_keywords", joinColumns = @JoinColumn(name = "document_id"), inverseJoinColumns = @JoinColumn(name = "keyword_id"))
    private Set<KeyWord> keyWords = new HashSet<>();
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "document_accomplices", joinColumns = @JoinColumn(name = "document_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
}
