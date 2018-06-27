package com.accentrix.hku.service.campaign;

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

import com.accentrix.hku.vo.campaign.SessionToApplicationVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:32:18 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "SessionToApplication Service", description = "sessionToApplicationService")
public interface SessionToApplicationService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/sessionToApplication/get/", verb = ApiVerb.GET, description = "get sessionToApplication by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    SessionToApplicationVo get(
            @ApiQueryParam(name = "id", description = "The sessionToApplication id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/sessionToApplication/", verb = ApiVerb.POST, description = "save sessionToApplication", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    SessionToApplicationVo save(@ApiBodyObject SessionToApplicationVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/sessionToApplication/save-list/", verb = ApiVerb.POST, description = "save sessionToApplication list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<SessionToApplicationVo> save(@ApiBodyObject List<SessionToApplicationVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/sessionToApplication/{id}/", verb = ApiVerb.DELETE, description = "delete sessionToApplication by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The sessionToApplication Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/sessionToApplication/delete-sessionToApplication/", verb = ApiVerb.POST, description = "delete sessionToApplication", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject SessionToApplicationVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/sessionToApplication/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<SessionToApplicationVo> findList();
}
