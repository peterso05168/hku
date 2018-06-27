package com.accentrix.hku.service.app;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.jsondoc.core.pojo.ApiVerb;

import com.accentrix.hku.vo.app.IbdBestExamVo;

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "IbdBestExam Service", description = "ibdBestExamService")
public interface IbdBestExamService {

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/ibdBestExam/", verb = ApiVerb.POST, description = "save ibdBestExam", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    IbdBestExamVo save(@ApiBodyObject IbdBestExamVo vo);

    @POST
    @Path("/getBestIBD/")
    @ApiMethod(path = "/api/rest/ibdBestExam/getBestIBD/", verb = ApiVerb.POST, description = "getBestIBD", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    IbdBestExamVo getBestIBD(
            @ApiQueryParam(name = "applicationId", description = "The application id") @QueryParam("applicationId") String applicationId,
            @ApiQueryParam(name = "calculateType", description = "The calculate type") @QueryParam("calculateType") String calculateType,
            @ApiQueryParam(name = "outOf", description = "The out of") @QueryParam("outOf") String outOf);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/ibdBestExam/{id}/", verb = ApiVerb.DELETE, description = "delete ibdBestExam by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The grade Id") @PathParam("id") String id);

}
