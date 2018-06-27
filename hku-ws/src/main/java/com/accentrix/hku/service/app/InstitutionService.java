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

import com.accentrix.hku.vo.app.InstitutionVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 下午1:41:20
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Institution Service", description = "institutionService")
public interface InstitutionService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/institution/get/", verb = ApiVerb.GET, description = "get institution by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    InstitutionVo get(@ApiQueryParam(name = "id", description = "The Institution id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/institution/", verb = ApiVerb.POST, description = "save institution", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    InstitutionVo save(@ApiBodyObject InstitutionVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/institution/save-list/", verb = ApiVerb.POST, description = "save institution list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<InstitutionVo> save(List<InstitutionVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/institution/{id}/", verb = ApiVerb.DELETE, description = "delete institution by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The Institution Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/institution/delete-institution/", verb = ApiVerb.POST, description = "delete institution", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject InstitutionVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/institution/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<InstitutionVo> findList();

    @POST
    @Path("/findInstitutions/")
    @ApiMethod(path = "/api/rest/institution/findInstitutions/", verb = ApiVerb.POST, description = "findInstitutions", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<InstitutionVo> findInstitutions(
            @ApiQueryParam(name = "countryId", required = false) @QueryParam("countryId") String countryId,
            @ApiQueryParam(name = "provinceId", required = false) @QueryParam("provinceId") String provinceId,
            @ApiQueryParam(name = "cityId", required = false) @QueryParam("cityId") String cityId);
}
