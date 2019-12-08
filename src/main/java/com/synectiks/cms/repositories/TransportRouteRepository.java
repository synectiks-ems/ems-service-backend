package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.TransportRoute;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the TransportRoute entity.
 */
@Repository
public interface TransportRouteRepository
		extends JPASearchRepository<TransportRoute, Long> {

}