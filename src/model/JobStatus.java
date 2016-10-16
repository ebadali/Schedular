/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Ebad Ali
 */
public enum JobStatus {
    


    COMPLETED("(green) Template Completed"),
    TO_BE_COMPLETED("(yellow) To Be Templated"),
    ON_HOLD("(purple) Project on Hold"),
    MISSING_MATERIAL("(red) Missing Material"),
    READY("(blue) Ready for Install"),
    WAITING("(orange) Waiting for Fabrication"),
    NONE("");
    private final String text;

    /**
     * @param text
     */
    private JobStatus(final String text) {
        this.text = text;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : text.contains(otherName);
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
    
    
    
     //From String method will return you the Enum for the provided input string
    public static JobStatus fromString(Object parameterName) {
        if (parameterName != null) {
            String comp = parameterName.toString();
            for (JobStatus objType : JobStatus.values()) {
                if (objType.text.equalsIgnoreCase(comp)) {
                    return objType;
                }
            }
        }
        return NONE;
    }
}
