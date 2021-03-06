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
@Table(name = "VOTE",
        indexes = {
            @Index(name = "IDX_VOTE", columnList = "ID,ID_CARD,ID_PERSON")})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vote implements Serializable {

    private static final String SQ_NAME = "SQ_VOTE";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID")
    @Basic(optional = false)
    private Long id;

    @ManyToOne
    @Basic(optional = false)
    @JoinColumn(name = "ID_PERSON", nullable = false)
    private Person person;

    @Basic(optional = false)
    private boolean positive;
}
