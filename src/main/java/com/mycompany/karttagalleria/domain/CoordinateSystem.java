package com.mycompany.karttagalleria.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Tero Tuomala
 */

@Entity
public class CoordinateSystem extends AbstractPersistable<Long> {
    
    @NotBlank
    @Column(unique = true)
    private String name;
    
    @OneToMany(mappedBy = "coordinateSystem", fetch = FetchType.EAGER)
    private List<Map> maps;
    
    
    public CoordinateSystem() {
        this.maps = new ArrayList<Map>();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the maps
     */
    public List<Map> getMaps() {
        return maps;
    }

    /**
     * @param maps the maps to set
     */
    public void setMaps(Map map) {
        this.maps.add(map);
    }
    
}
