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

import com.accentrix.hku.vo.app.PrevStudiesVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:23:15
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Prev Studies Service", description = "prevStudiesService")
public interface PrevStudiesService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/prevStudies/get/", verb = ApiVerb.GET, description = "get prevStudies by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    PrevStudiesVo get(@ApiQueryParam(name = "id", description = "The prevStudies id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/prevStudies/", verb = ApiVerb.POST, description = "save prevStudies", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    PrevStudiesVo save(@ApiBodyObject PrevStudiesVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/prevStudies/save-list/", verb = ApiVerb.POST, description = "save prevStudies list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<PrevStudiesVo> save(List<PrevStudiesVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/prevStudies/{id}/", verb = ApiVerb.DELETE, description = "delete prevStudies by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The prevStudies Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/prevStudies/delete-prevStudies/", verb = ApiVerb.POST, description = "delete prevStudies", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject PrevStudiesVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/prevStudies/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<PrevStudiesVo> findList();

    @POST
    @Path("/findListByApplicationId/")
    @ApiMethod(path = "/api/rest/prevStudies/findListByApplicationId/", verb = ApiVerb.POST, description = "findListByApplicationId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<PrevStudiesVo> findListByApplicationId(
            @ApiQueryParam(name = "applicationId") @QueryParam("applicationId") String applicationId);
}
