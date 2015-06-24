package com.web.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.base.entity.MobileUser;
import com.web.base.repository.CmsUserRepository;
import com.web.base.service.CmsUserService;

@Service
public class CmsUserServiceImpl implements CmsUserService {

	@Autowired
	CmsUserRepository userRepository;
	
	@Override
	public Page<MobileUser> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public MobileUser getOne(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public MobileUser findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	
}
