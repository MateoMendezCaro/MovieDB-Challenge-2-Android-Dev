package com.app.moviedb.base.navigation


sealed class Destination(protected val route: String, vararg params: String) {

    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    sealed class NoArgumentsDestination(route: String) : Destination(route) {
        operator fun invoke(): String = route
    }

    data object HomeScreen : NoArgumentsDestination(HOME_ROUTE)

    data object DetailScreen : Destination(DETAIL_ROUTE, ID, TYPE) {
        operator fun invoke(id: String, type: String): String = route.appendParams(
            ID to id,
            TYPE to type
        )
    }

    companion object {
        private const val HOME_ROUTE = "home"
        private const val DETAIL_ROUTE = "DetailMovie"
        private const val ID = "id"
        private const val TYPE = "type"
    }
}

internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}