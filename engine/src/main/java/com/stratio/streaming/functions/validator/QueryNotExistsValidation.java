/**
 * Copyright (C) 2014 Stratio (http://stratio.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stratio.streaming.functions.validator;

import com.stratio.streaming.commons.constants.REPLY_CODES;
import com.stratio.streaming.commons.messages.StratioStreamingMessage;
import com.stratio.streaming.exception.RequestValidationException;
import com.stratio.streaming.service.StreamOperationService;

public class QueryNotExistsValidation extends BaseSiddhiRequestValidation {

    private final static String QUERY_DOES_NOT_EXIST_MESSAGE = "Query %s in stream %s does not exists";

    public QueryNotExistsValidation(StreamOperationService streamOperationService) {
        super(streamOperationService);
    }

    @Override
    public void validate(StratioStreamingMessage request) throws RequestValidationException {
        if (!getStreamOperationService().queryExists(request.getStreamName(), request.getRequest())) {
            throw new RequestValidationException(REPLY_CODES.KO_QUERY_DOES_NOT_EXIST, String.format(
                    QUERY_DOES_NOT_EXIST_MESSAGE, request.getRequest(), request.getStreamName()));
        }
    }
}
