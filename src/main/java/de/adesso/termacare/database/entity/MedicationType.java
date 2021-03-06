package de.adesso.termacare.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Possible types of medication available
 */
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum MedicationType {
    
    INSPECTION(0L, "inspection"),
    OPERATION(1L, "operation");
    
    private long id;
    private String type;
}
