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

import com.accentrix.hku.vo.app.ProgressVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月1日 上午10:45:30 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Progress Service", description = "progressService")
public interface ProgressService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/progress/get/", verb = ApiVerb.GET, description = "get progress by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ProgressVo get(@ApiQueryParam(name = "id", description = "The progress id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/progress/", verb = ApiVerb.POST, description = "save progress", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ProgressVo save(@ApiBodyObject ProgressVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/progress/save-list/", verb = ApiVerb.POST, description = "save progress list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ProgressVo> save(List<ProgressVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/progress/{id}/", verb = ApiVerb.DELETE, description = "delete progress by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The progress Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/progress/delete-progress/", verb = ApiVerb.POST, description = "delete progress", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject ProgressVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/progress/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ProgressVo> findList(@ApiBodyObject ProgressVo vo);

    @POST
    @Path("/findByApplicationId/")
    @ApiMethod(path = "/api/rest/progress/findByApplicationId/", verb = ApiVerb.POST, description = "findByApplicationId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ProgressVo findByApplicationId(
            @ApiQueryParam(name = "applicationId", description = "The applicationId") @QueryParam("applicationId") String applicationId);
}
