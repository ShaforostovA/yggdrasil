package com.ntgspiyggdrasil.yggdrasil.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Data
@Entity
@Table(name = "key_words")
@NoArgsConstructor
public class KeyWord {
    public KeyWord(String name, User user, Date dateCreate) {
        this.name = name;
        this.user = user;
        this.dateCreate = dateCreate;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User user;
    @Column(name = "date_create")
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private Date dateCreate;
}