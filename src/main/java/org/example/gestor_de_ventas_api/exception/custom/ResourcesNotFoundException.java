package org.example.gestor_de_ventas_api.exception.custom;

public class ResourcesNotFoundException extends RuntimeException{

    public ResourcesNotFoundException(String message){
        super(message);
    }

}
