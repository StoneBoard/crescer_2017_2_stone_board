/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Júlia
 */
@Data
@Entity
@Table(name = "PERSON")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person implements Serializable{
    
    private static final String SQ_NAME = "SQ_PERSON";
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID")
    @Basic(optional = false)
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "FULL_NAME", length=255)
    private String fullName;
    
    @Basic(optional = false)
    @Column(name = "EMAIL", length = 255)
    private String email;
    
    @JsonIgnore
    @Basic(optional = false)
    @Column(name = "PASS", length=128)
    private String pass;
    
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PERSON", nullable = false)
    private List<Board> myBoards;
    
    @ManyToMany
    @JoinTable( name = "PERSON_BOARD", joinColumns = @JoinColumn(name = "ID_PERSON"),
                                        inverseJoinColumns = @JoinColumn(name = "ID_BOARD") )
    private List<Board> connectBoards;          
    
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PERSON", nullable = false)
    private List<Notification> notifications;
}
