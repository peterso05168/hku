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

import com.accentrix.hku.vo.app.ExpAndAchievementsVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:20:49
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "ExpAndAchievements Service", description = "expAndAchievementsService")
public interface ExpAndAchievementsService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/expAndAchievements/get/", verb = ApiVerb.GET, description = "get expAndAchievements by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ExpAndAchievementsVo get(
            @ApiQueryParam(name = "id", description = "The expAndAchievements id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/expAndAchievements/", verb = ApiVerb.POST, description = "save expAndAchievements", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ExpAndAchievementsVo save(@ApiBodyObject ExpAndAchievementsVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/expAndAchievements/save-list/", verb = ApiVerb.POST, description = "save expAndAchievements list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ExpAndAchievementsVo> save(List<ExpAndAchievementsVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/expAndAchievements/{id}/", verb = ApiVerb.DELETE, description = "delete expAndAchievements by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The expAndAchievements Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/expAndAchievements/delete-expAndAchievements/", verb = ApiVerb.POST, description = "delete expAndAchievements", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject ExpAndAchievementsVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/expAndAchievements/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ExpAndAchievementsVo> findList();

    @POST
    @Path("/findListByApplicationId/")
    @ApiMethod(path = "/api/rest/expAndAchievements/findListByApplicationId/", verb = ApiVerb.POST, description = "findListByApplicationId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ExpAndAchievementsVo> findListByApplicationId(
            @ApiQueryParam(name = "applicationId") @QueryParam("applicationId") String applicationId);

    @POST
    @Path("/findListByApplicationIdAndCat/")
    @ApiMethod(path = "/api/rest/expAndAchievements/findListByApplicationIdAndCat/", verb = ApiVerb.POST, description = "findListByApplicationIdAndCat", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ExpAndAchievementsVo> findListByApplicationIdAndCat(
            @ApiQueryParam(name = "applicationId") @QueryParam("applicationId") String applicationId,
            @ApiQueryParam(name = "catCd") @QueryParam("catCd") String catCd);
}
