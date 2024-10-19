package br.com.elotech.biblioteca_elo.infrastructure.middleware.mappers;

public interface MappingLayerObjects {

    <T, R> R fromRequestToDomain(T request, Class<R> domainClass);
    <T, R> R fromDomainToEntity(T domain, Class<R> entityClass);
    <T, R> R fromEntityToDomain(T entity, Class<R> domainClass);
    <T, R> R fromDomainToResponse(T domain, Class<R> responseClass);
}
