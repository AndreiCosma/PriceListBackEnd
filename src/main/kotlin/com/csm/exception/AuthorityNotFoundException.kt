package com.csm.exception


/*
* Created by I503342 - 17/04/2019
*/

class AuthorityNotFoundException(
        reason: String
) : RuntimeException(reason)