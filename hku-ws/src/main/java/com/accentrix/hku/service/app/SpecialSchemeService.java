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

import com.accentrix.hku.vo.app.SpecialSchemeVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午3:14:42
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "SpecialScheme Service", description = "specialSchemeService")
public interface SpecialSchemeService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/specialScheme/get/", verb = ApiVerb.GET, description = "get specialScheme by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    SpecialSchemeVo get(@ApiQueryParam(name = "id", description = "The specialScheme id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/specialScheme/", verb = ApiVerb.POST, description = "save specialScheme", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    SpecialSchemeVo save(@ApiBodyObject SpecialSchemeVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/specialScheme/save-list/", verb = ApiVerb.POST, description = "save specialScheme list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<SpecialSchemeVo> save(List<SpecialSchemeVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/specialScheme/{id}/", verb = ApiVerb.DELETE, description = "delete specialScheme by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The specialScheme Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/specialScheme/delete-specialScheme/", verb = ApiVerb.POST, description = "delete specialScheme", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject SpecialSchemeVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/specialScheme/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<SpecialSchemeVo> findList();

    @GET
    @Path("/getByCodeAndApplicationId/")
    @ApiMethod(path = "/api/rest/specialScheme/getByCodeAndApplicationId/", verb = ApiVerb.GET, description = "get specialScheme by code and applicationId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    SpecialSchemeVo getByCodeAndApplicationId(@ApiQueryParam(name = "code") @QueryParam("code") String code,
            @ApiQueryParam(name = "applicationId") @QueryParam("applicationId") String applicationId);
}
