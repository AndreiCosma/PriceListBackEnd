package com.csm.domain.repo

import com.csm.domain.entity.Authority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

interface AuthorityRepo : JpaRepository<Authority, Long>