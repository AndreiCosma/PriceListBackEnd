package com.csm.domain.repo

import com.csm.domain.entity.Authority
import org.springframework.data.repository.CrudRepository

interface AuthorityRepo : CrudRepository<Authority, Long>