package com.cursos.ec.testalquimia.util;

import com.cursos.ec.testalquimia.messages.request.GenericReqDTO;
import com.cursos.ec.testalquimia.messages.response.GenericRespDTO;

import java.util.Objects;

public class GeneralUtil {

    public static <T> GenericRespDTO<T> buildGenericSuccessResp(T data, String message) {
        return new GenericRespDTO<T>("OK",
                (Objects.isNull(message) ? "Peticion procesada correctamente" : message),
                data);
    }

    public static <T> GenericReqDTO<T> buildGenericReq(T data) {
        return new GenericReqDTO<T>("test", data);
    }

}
