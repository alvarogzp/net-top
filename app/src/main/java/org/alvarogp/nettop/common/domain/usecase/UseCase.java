package org.alvarogp.nettop.common.domain.usecase;

import org.alvarogp.nettop.common.domain.usecase.executable.Executable;

public interface UseCase<T extends UseCaseRequest, R extends UseCaseResponse> {
    Executable<R> getExecutable(T request);
}
