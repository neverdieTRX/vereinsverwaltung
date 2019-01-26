package de.trx.veve.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 7795296740526497268L;
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Ermittelt ob die Entität neu ist
     *
     * @return ist die Entität neu?
     */
    public boolean isNew() {
        return this.id == null;
    }
}
