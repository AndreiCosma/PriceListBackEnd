package com.csm.domain.repo

import com.csm.domain.entity.CheckList
import org.springframework.data.jpa.repository.JpaRepository


/*
* Created by I503342 - 21/03/2019
*/
interface CheckListRepo: JpaRepository<CheckList, Long>