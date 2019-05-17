package com.csm.domain.repo

import com.csm.domain.entity.CheckListItem
import com.csm.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional


/*
* Created by I503342 - 21/03/2019
*/
interface ListItemRepo : JpaRepository<CheckListItem, String> {
    @Query("select r from CheckListItem r where r.id = :id and :user MEMBER OF r.checkList.users")
    fun findByIdAndUser(@Param("id") id: String, @Param("user") user: User): CheckListItem

    @Query("select r from CheckListItem r where :user MEMBER OF r.checkList.users")
    fun findByUser(@Param("user") user: User): List<CheckListItem>

    //ToDo: This does not work. Fix it.
    @Transactional
    @Modifying
    @Query("delete from CheckListItem i where i.id  = ( select r.id from CheckListItem r where r.id = :id and :user MEMBER OF r.checkList.users )")
    fun deleteByIdAndUser(@Param("id") id: String, @Param("user") user: User)
}