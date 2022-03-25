package com.hoahieu.demo.domain.usecase

import kotlinx.coroutines.flow.Flow

interface FlowUseCase<In, Out> {
    fun execute(parameters: In): Flow<Out>
}

fun <In, Out> FlowUseCase<In?, Out>.execute() = execute(null)