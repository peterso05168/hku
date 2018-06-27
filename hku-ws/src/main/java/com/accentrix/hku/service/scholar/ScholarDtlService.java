package com.accentrix.hku.service.scholar;

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

import com.accentrix.hku.vo.scholar.ScholarDtlVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月19日 上午11:44:48 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "ScholarDtl Service", description = "scholarDtlService")
public interface ScholarDtlService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/scholarDtl/get/", verb = ApiVerb.GET, description = "get scholarDtl by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ScholarDtlVo get(@ApiQueryParam(name = "id", description = "The scholarDtl id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/scholarDtl/", verb = ApiVerb.POST, description = "save scholarDtl", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ScholarDtlVo save(@ApiBodyObject ScholarDtlVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/scholarDtl/save-list/", verb = ApiVerb.POST, description = "save scholarDtl list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ScholarDtlVo> save(List<ScholarDtlVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/scholarDtl/{id}/", verb = ApiVerb.DELETE, description = "delete scholarDtl by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The scholarDtl Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/scholarDtl/delete-scholarDtl/", verb = ApiVerb.POST, description = "delete scholarDtl", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject ScholarDtlVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/scholarDtl/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ScholarDtlVo> findList();

    @POST
    @Path("/findByScholarId/")
    @ApiMethod(path = "/api/rest/scholarDtl/findByScholarId/", verb = ApiVerb.POST, description = "findByScholarId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ScholarDtlVo> findByScholarId(
            @ApiQueryParam(name = "scholarId", description = "The scholar id") @QueryParam("scholarId") String scholarId);
}
