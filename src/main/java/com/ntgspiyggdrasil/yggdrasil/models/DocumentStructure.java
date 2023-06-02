package com.ntgspiyggdrasil.yggdrasil.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Entity
@Table(name = "document_structures")
@NoArgsConstructor
public class DocumentStructure {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "document_structure", columnDefinition = "jsonb")
    private String structure;
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "date_create")
    private Date dateCreate;
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "date_update")
    private Date dateUpdate;
    @Column(name = "old_structure_id")
    private Long oldStructureId;
    @Column(name = "is_active")
    private Boolean isActive;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id", referencedColumnName = "id")
    private DocumentType documentType;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "documentStructure")
    private List<Document> documents;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "documentStructure")
    private List<DocumentRestriction> documentRestrictions;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "documentStructure")
    private List<DocumentPermission> DocumentPermissions;

}
