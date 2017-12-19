package br.com.crescer.stone_board.entity;

import br.com.crescer.stone_board.utils.LocalDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Júlia
 */
@Data
@Entity
@Table(name = "CARD",
        indexes = {
            @Index(name = "IDX_CARD", columnList = "ID,ID_SESSION,ID_RESULT_GROUP")})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card implements Serializable {

    private static final String SQ_NAME = "SQ_CARD";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID")
    @Basic(optional = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "TEXT", length = 300)
    private String text;

    @ManyToOne
    @JoinColumn(name = "ID_PERSON_WRITER", nullable = false)
    private Person writer;

    @Column(name = "CREATION_DATE")
    @Basic(optional = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime creationDate;

    @Basic(optional = false)
    @Column(name = "COLOR")
    private int color;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CARD", nullable = false)
    @Fetch(FetchMode.SUBSELECT)
    private List<Vote> votes;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CARD", nullable = false)
    @Fetch(FetchMode.SUBSELECT)
    private List<Note> notes;

    @ManyToOne
    @JoinColumn(name = "ID_RESULT_GROUP")
    private ResultGroup resultGroup;

}
