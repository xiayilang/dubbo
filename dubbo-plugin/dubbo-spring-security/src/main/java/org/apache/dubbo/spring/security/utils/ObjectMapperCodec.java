/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dubbo.spring.security.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.dubbo.common.utils.StringUtils;
import org.springframework.security.jackson2.CoreJackson2Module;

public class ObjectMapperCodec {

    private ObjectMapper mapper = new ObjectMapper();


    public ObjectMapperCodec(){
        mapper.registerModule(new CoreJackson2Module());
    }

    public <T> T deserialize(byte [] bytes,Class<T> clazz) {
        try {
            if (bytes == null || bytes.length == 0) {
                return null;
            }

            return mapper.readValue(bytes, clazz);

        } catch (Exception exception) {
            throw new RuntimeException(
                String.format("objectMapper! deserialize error %s", exception));
        }
    }

    public <T> T deserialize(String content,Class<T> clazz) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        return deserialize(content.getBytes(), clazz);
    }
    public  String serialize(Object object) {
        try {
            if (object == null) {
                return null;
            }

            return mapper.writeValueAsString(object);

        } catch (Exception ex) {
            throw new RuntimeException(String.format("objectMapper! serialize error %s", ex));
        }
    }

}
