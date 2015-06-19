package com.web.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.base.entity.MobileUser;

public interface CmsUserService {
	
	public Page<MobileUser> findall(Pageable pageable);
	
	public MobileUser getOne(Integer id);
	
}
