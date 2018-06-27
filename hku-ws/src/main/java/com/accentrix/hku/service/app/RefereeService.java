package com.accentrix.hku.service.app;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
import com.accentrix.hku.vo.app.RefereeVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:25:31 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Referee Service", description = "refereeService")
public interface RefereeService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/referee/get/", verb = ApiVerb.GET, description = "get referee by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    RefereeVo get(@ApiQueryParam(name = "id", description = "The referee id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/referee/", verb = ApiVerb.POST, description = "save referee", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    RefereeVo save(@ApiBodyObject RefereeVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/referee/save-list/", verb = ApiVerb.POST, description = "save referee list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<RefereeVo> save(List<RefereeVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/referee/{id}/", verb = ApiVerb.DELETE, description = "delete referee by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The referee Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/referee/delete-referee/", verb = ApiVerb.POST, description = "delete referee", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject RefereeVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/referee/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<RefereeVo> findList();

    @POST
    @Path("/findListByReferenceId/")
    @ApiMethod(path = "/api/rest/referee/findListByReferenceId/", verb = ApiVerb.POST, description = "findListByReferenceId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<RefereeVo> findListByReferenceId(
            @ApiQueryParam(name = "referenceId") @QueryParam("referenceId") String referenceId);
}
