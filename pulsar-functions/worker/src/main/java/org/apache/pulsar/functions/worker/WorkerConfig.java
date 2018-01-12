/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.pulsar.functions.worker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.pulsar.functions.fs.LimitsConfig;
import org.apache.pulsar.functions.fs.MetricsConfig;

@Data
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Accessors(chain = true)
public class WorkerConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private String workerId;
    private int workerPort;
    private String zookeeperServers;
    private String functionMetadataTopic;
    private String pulsarServiceUrl;
    private int numFunctionPackageReplicas;
    private String downloadDirectory;
    private LimitsConfig limitsConfig;
    private MetricsConfig metricsConfig;

    @Data
    @Setter
    @Getter
    @EqualsAndHashCode
    @ToString
    class ThreadContainerFactory {
        private String threadGroupName;
    }
    private ThreadContainerFactory threadContainerFactory;

    @Data
    @Setter
    @Getter
    @EqualsAndHashCode
    @ToString
    class ProcessContainerFactory {
        private String javaInstanceJarLocation;
        private String logDirectory;
    }
    private ProcessContainerFactory processContainerFactory;

    public static WorkerConfig load(String yamlFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(new File(yamlFile), WorkerConfig.class);
    }

}
