/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.uniffle.coordinator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MustDiffHostAssignmentStrategy implements HostAssignmentStrategy {
  @Override
  public List<ServerNode> assign(List<ServerNode> allNodes, int expectNum) {
    List<ServerNode> candidatesNodes = new ArrayList<>();
    Set<String> hostIpCandidate = new HashSet<>();
    for (ServerNode node : allNodes) {
      if (hostIpCandidate.contains(node.getIp())) {
        continue;
      }
      hostIpCandidate.add(node.getIp());
      candidatesNodes.add(node);
      if (candidatesNodes.size() >= expectNum) {
        break;
      }
    }
    return candidatesNodes;
  }
}
