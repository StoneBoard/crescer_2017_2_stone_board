package br.com.crescer.stone_board.entity;

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
@Table(name = "BOARD_SESSION")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardSession implements Serializable{
    
    private static final String SQ_NAME = "SQ_BOARD_SESSION";
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID")
    @Basic(optional = false)
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "TITLE", length=128)
    private String title;
    
    @Basic(optional = false)
    @Column(name= "COLOR")
    private int color;
    
    @Basic(optional = true)
    @Column(name = "ICON", length=512)
    private String icon;
     
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_SESSION", nullable = false)
    private List<Card> cards;
    
    
}
