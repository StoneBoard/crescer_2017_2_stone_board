/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.entity;

import br.com.crescer.stone_board.utils.LocalDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "NOTIFICATION")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification implements Serializable{

    private static final String SQ_NAME = "SQ_NOTIFICATION";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID")
    @Basic(optional = false)
    private Long id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @Basic(optional = false)
    @JoinColumn(name = "ID_BOARD", nullable = false)
    private Board board;
    
    @Column(name = "CREATION_DATE")
    @Basic(optional = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime creationDate;
    
    @Basic(optional = false)
    @Column(name = "CHECKED")
    private boolean checked;

}
