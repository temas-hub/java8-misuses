package com.xpinjection.java8.misused

/**
 * @author Artem Zhdanov
 * @since 19.09.2016
 */


fun convertToDto(user: User): UserDto {
    val dto = UserDto()
    dto.id = user.id
    dto.name = user.name
    return dto
}

fun hasPermission(permission: Permission): (User) -> Boolean {
    return { u -> u.roles.any({ r -> r.permissions.contains(permission) }) }
}

// method refs example
fun convert(users: List<User>): List<UserDto> {
    return users.filter(hasPermission(Permission.EDIT)).map(::convertToDto).toList()
}

// findAny and requireNotNull analogues
fun hasSomthing(users: List<User?>): Boolean = users.filterNotNull().any { it.roles.size > 2 }


// map transformation example
fun getUserNames(users: Map<String, User>): Map<String, String> {
    return users.mapValues { it.value.name }
}
