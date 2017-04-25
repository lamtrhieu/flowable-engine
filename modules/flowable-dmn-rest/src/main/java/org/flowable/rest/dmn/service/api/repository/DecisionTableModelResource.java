/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.rest.dmn.service.api.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.flowable.dmn.api.DmnDecisionTable;
import org.flowable.dmn.model.DmnDefinition;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yvo Swillens
 */
@RestController
@Api(tags = { "Decision Tables" }, description = "Manage Decision Tables", authorizations = { @Authorization(value = "basicAuth") })
public class DecisionTableModelResource extends BaseDecisionTableResource {

    @ApiOperation(value = "Get a decision table DMN (definition) model", tags = { "Decision Tables" }, nickname = "getDmnModelResource")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Indicates the decision table was found and the model is returned."),
            @ApiResponse(code = 404, message = "Indicates the requested decision table was not found.")
    })
    @RequestMapping(value = "/dmn-repository/decision-tables/{decisionTableId}/model", method = RequestMethod.GET, produces = "application/json")
    public DmnDefinition getDmnModelResource(@ApiParam(name = "decisionTableId") @PathVariable String decisionTableId) {
        DmnDecisionTable decisionTable = geDecisionTableFromRequest(decisionTableId);
        return dmnRepositoryService.getDmnDefinition(decisionTable.getId());
    }
}