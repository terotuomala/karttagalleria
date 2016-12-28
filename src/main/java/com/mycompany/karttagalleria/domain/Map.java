package com.mycompany.karttagalleria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author Tero Tuomala
 * @version 1.0
 */

@Entity
public class Map extends AbstractPersistable<Long> {
    
    @NotBlank
    @Length(min = 3, max = 30)
    @Column(unique = true)
    private String title;
    
    @NotNull
    @ManyToOne
    private Category category;
    
    @NotBlank
    @Length(min = 3, max = 80)
    private String description;
    
    @NotNull
    @ManyToOne
    private CoordinateSystem coordinateSystem;
    
    @NotBlank
    @URL
    private String url;
    

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * @return the coordinateSystem
     */
    public CoordinateSystem getCoordinateSystem() {
        return coordinateSystem;
    }

    /**
     * @param coordinateSystem the coordinateSystem to set
     */
    public void setCoordinateSystem(CoordinateSystem coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
