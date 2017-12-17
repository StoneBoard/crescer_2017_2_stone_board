/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "RESULT_GROUP",
       indexes = {@Index(name = "SEARCH_BY_ID",  columnList="ID", unique = true)})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultGroup implements Serializable{

    private static final String SQ_NAME = "SQ_RESULT_GROUP";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID")
    @Basic(optional = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "TITLE", length = 128)
    private String title;

    @Basic(optional = false)
    @Column(name = "DESCRIPTION", length = 500)
    private String description;
    
    @Basic(optional = true)
    @OneToMany(mappedBy = "resultGroup")
    private List<Card> cards;
    
    @Basic(optional = false)
    @ManyToOne
    @JoinColumn(name = "ID_BOARD", nullable = false)
    private Board board;
}
