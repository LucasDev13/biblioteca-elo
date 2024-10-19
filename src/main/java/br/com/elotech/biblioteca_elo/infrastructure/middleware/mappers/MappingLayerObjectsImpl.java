package br.com.elotech.biblioteca_elo.infrastructure.middleware.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MappingLayerObjectsImpl implements MappingLayerObjects {

    @Override
    public <T, R> R fromRequestToDomain(T request, Class<R> domainClass) {
        if(!Objects.isNull(request)) {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(request, domainClass);
        }
        throw new IllegalArgumentException("Request cannot be null");
    }

    @Override
    public <T, R> R fromDomainToEntity(T domain, Class<R> entityClass) {
        if(!Objects.isNull(domain)){
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(domain, entityClass);
        }
        throw new IllegalArgumentException("Domain object cannot be null");
    }

    @Override
    public <T, R> R fromEntityToDomain(T entity, Class<R> domainClass) {
        if(!Objects.isNull(entity)){
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(entity,domainClass);
        }
        throw new IllegalArgumentException("Entity object cannot be null");
    }

    @Override
    public <T, R> R fromDomainToResponse(T domain, Class<R> responseClass) {
        if(!Objects.isNull(domain)){
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(domain, responseClass);
        }
        throw new IllegalArgumentException("Domain object cannot be null");
    }
}
