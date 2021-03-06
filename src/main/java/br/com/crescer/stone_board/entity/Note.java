package br.com.crescer.stone_board.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.Index;
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
@Table(name = "NOTE",
        indexes = {
            @Index(name = "IDX_NOTE", columnList = "ID,ID_CARD")})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note implements Serializable {

    private static final String SQ_NAME = "SQ_NOTE";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID")
    @Basic(optional = false)
    private Long id;

    @ManyToOne
    @Basic(optional = false)
    @JoinColumn(name = "ID_PERSON_WRITER", nullable = false)
    private Person writer;

    @Basic(optional = false)
    @Column(name = "TEXT", length = 200)
    private String text;

}
