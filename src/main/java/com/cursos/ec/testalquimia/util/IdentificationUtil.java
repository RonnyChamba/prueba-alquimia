package com.cursos.ec.testalquimia.util;

import com.cursos.ec.testalquimia.exceptions.BadRequestException;
import com.cursos.ec.testalquimia.exceptions.GenericException;

import java.util.List;

public class IdentificationUtil {

    /**
     * Method to validate the identification
     *
     * @param type            : String
     * @param identification: String
     * @throws GenericException : GenericException
     */

    public static void validateIdentification(String type, String identification) throws GenericException {

        var listAllowed = List.of(
                "CED",
                "RUC"
        );
        if (!listAllowed.contains(type.trim().toUpperCase())) {
            throw new BadRequestException(String.format("The type of identification %s is not allowed", type));
        }

        if (type.trim().equalsIgnoreCase("CED") && identification.length() != 10) {
            throw new BadRequestException("The identification must be 10 digits");
        }

        if (type.trim().equalsIgnoreCase("RUC") && identification.length() != 13) {
            throw new BadRequestException("The identification must be 13 digits");
        }
    }
}
