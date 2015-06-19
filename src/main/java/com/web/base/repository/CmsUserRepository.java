package com.web.base.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.base.entity.MobileUser;

public interface CmsUserRepository extends
		PagingAndSortingRepository<MobileUser, Integer>,
		JpaSpecificationExecutor<MobileUser> {

}
