/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.entity;

import br.com.crescer.stone_board.utils.LocalDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "BOARD")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    
    private static final String SQ_NAME = "SQ_BOARDS";
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID")
    @Basic(optional = false)
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "TITLE", length=128)
    private String title;
    
    @Column(name = "DEADLINE")
    @Basic(optional = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime deadline;
    
    @Basic(optional = false)
    @Column(name = "ACTIVE")
    private boolean active;
    
    @Basic(optional = true)
    @ManyToMany(mappedBy="connectBoards")
    List<Person> members;
    
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_BOARD")
    private List<BoardSession> sessions;

}