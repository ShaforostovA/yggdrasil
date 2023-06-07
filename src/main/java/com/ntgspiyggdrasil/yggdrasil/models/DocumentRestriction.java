package com.ntgspiyggdrasil.yggdrasil.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Data
@Entity
@Table(name = "restrictions")
@NoArgsConstructor
public class DocumentRestriction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "struct_id", referencedColumnName = "id")
    private DocumentStructure documentStructure;
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "date_create")
    private Date dateCreate;
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "date_end")
    private Date dateEnd;
}