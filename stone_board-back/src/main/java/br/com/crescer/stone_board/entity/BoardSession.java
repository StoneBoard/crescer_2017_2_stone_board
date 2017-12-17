package br.com.crescer.stone_board.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Júlia
 */

@Data
@Entity
@Table(name = "BOARD_SESSION",
       indexes = {@Index(name = "SEARCH_BY_ID",  columnList="ID", unique = true),
                  @Index(name = "SEARCH_BY_ID_BOARD", columnList="ID_BOARD", unique = true)})
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
     
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_SESSION", nullable = false)
    @Fetch(FetchMode.SUBSELECT)
    private List<Card> cards;
           
}
