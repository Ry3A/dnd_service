package ru.aplk.dnd_service.additions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.aplk.dnd_service.additions.types.ResponseWithStatus;
import ru.aplk.dnd_service.additions.types.StatusCode;
import ru.aplk.dnd_service.additions.types.functions.Func2Args;
import ru.aplk.dnd_service.additions.types.functions.Func3Args;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ControllerFunctions {
    public <ObjectType, PropType> ResponseEntity<ResponseWithStatus<ObjectType>> responseWithStatus(
            PropType property,
            Function<PropType, ResponseWithStatus<ObjectType>> findFunction
    ) {
        ResponseWithStatus<ObjectType> response = findFunction.apply(property);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    public <ObjectType, PropType> ResponseEntity<ResponseWithStatus<ObjectType>> responseWithStatus(
            PropType property,
            Func2Args<PropType, HttpServletRequest, ResponseWithStatus<ObjectType>> findFunction,
            HttpServletRequest request
    ) {
        ResponseWithStatus<ObjectType> response = findFunction.apply(property, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    public <ObjectType, PropType1, PropType2> ResponseEntity<ResponseWithStatus<ObjectType>> responseWithStatus(
            PropType1 property1,
            PropType2 property2,
            Func3Args<PropType1, PropType2, HttpServletRequest, ResponseWithStatus<ObjectType>> findFunction,
            HttpServletRequest request
    ) {
        ResponseWithStatus<ObjectType> response = findFunction.apply(property1, property2, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


    public <T> ResponseEntity<StatusCode> statusCode(
            T propertyOrObject,
            Func2Args<T, HttpServletRequest, StatusCode> function,
            HttpServletRequest request
    ) {
        StatusCode response = function.apply(propertyOrObject, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
