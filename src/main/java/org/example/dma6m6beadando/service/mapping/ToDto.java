package org.example.dma6m6beadando.service.mapping;

import java.util.List;
import java.util.stream.Collectors;

public interface ToDto<E,D>{

    D toDto(E e);

    default List<D> toDto(List<E> e){
        if(e==null) return List.of();
        return e.stream().map(this::toDto).collect(Collectors.toList());
    }

}
