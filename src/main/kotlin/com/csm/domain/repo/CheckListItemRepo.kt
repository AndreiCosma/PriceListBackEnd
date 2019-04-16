package com.csm.domain.repo

import com.csm.domain.entity.CheckList
import com.csm.domain.entity.CheckListItem
import com.csm.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param


/*
* Created by I503342 - 21/03/2019
*/
interface CheckListItemRepo : JpaRepository<CheckListItem, String> {
    @Query("select r from CheckListItem r where r.id = :id and :user MEMBER OF r.checkList.users")
    fun findByIdAndUser(@Param("id") id: String, @Param("user") user: User): CheckList

    @Query("select r from CheckListItem r where :user MEMBER OF r.checkList.users")
    fun findByUser(@Param("user") user: User): List<CheckList>

    @Query("delete from CheckListItem r where r.id = :id and :user MEMBER OF r.checkList.users")
    fun deleteByIdAndUser(@Param("id") id: String, @Param("user") user: User)
}