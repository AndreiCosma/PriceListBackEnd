package com.csm.domain.repo

import com.csm.domain.entity.CheckList
import com.csm.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*


/*
* Created by I503342 - 21/03/2019
*/
interface CheckListRepo : JpaRepository<CheckList, String> {

    @Query("select r from CheckList r where r.id = :id and :user MEMBER OF r.users")
    fun findByIdAndUser(@Param("id") id: String, @Param("user") user: User): CheckList

    @Query("select r from CheckList r where :user MEMBER OF r.users")
    fun findByUser(@Param("user") user: User): List<CheckList>

    @Query("delete from CheckList r where r.id = :id and :user MEMBER OF r.users")
    fun deleteByIdAndUser(@Param("id") id: String, @Param("user") user: User)

}