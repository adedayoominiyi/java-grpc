/*
 * Copyright 2017-2018 The OpenTracing Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.opentracing.contrib.grpc;

import io.grpc.Status;
import io.opentracing.Span;
import io.opentracing.tag.StringTag;
import io.opentracing.tag.Tags;

/**
 * Package private utility methods for common gRPC tags.
 */
final class GrpcTags {
    private GrpcTags() {}

    /** gRPC status code tag */
    static StringTag GRPC_STATUS = new StringTag("grpc.status");

    /** Value for {@link Tags#COMPONENT} for gRPC */
    static String COMPONENT_VALUE = "grpc-java";

    /**
     * Sets {@code grpc.status} and {@code error} tags on span.
     * @param span Span
     * @param status gRPC call status
     */
    static void setStatusTags(Span span, Status status) {
        GRPC_STATUS.set(span, status.getCode().name());
        if (!status.isOk()) {
            Tags.ERROR.set(span, true);
        }
    }
}
