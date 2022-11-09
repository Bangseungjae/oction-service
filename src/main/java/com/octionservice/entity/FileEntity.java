package com.octionservice.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="FILE_ENTITY")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID")
    private Long id;

    @Column
    private String urls;

    @Column(name = "file_name")
    private String filename;

    @Column
    private String username;

    @Column
    private String originalName;

    @Column(name = "nft_name")
    private String nftName;

    @Column
    @Enumerated(EnumType.STRING)
    private Type type;
}
