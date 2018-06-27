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

import com.accentrix.hku.vo.app.AppNjceeSubjectStructureVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月25日 下午9:31:16
 */
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "AppNjceeSubjectStructure Service", description = "appNjceeSubjectStructureService")
public interface AppNjceeSubjectStructureService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/appNjceeSubjectStructure/get/", verb = ApiVerb.GET, description = "get appNjceeSubjectStructure by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AppNjceeSubjectStructureVo get(
            @ApiQueryParam(name = "id", description = "The appNjceeSubjectStructure id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/appNjceeSubjectStructure/", verb = ApiVerb.POST, description = "save appNjceeSubjectStructure", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AppNjceeSubjectStructureVo save(@ApiBodyObject AppNjceeSubjectStructureVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/appNjceeSubjectStructure/save-list/", verb = ApiVerb.POST, description = "save appNjceeSubjectStructure list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<AppNjceeSubjectStructureVo> save(List<AppNjceeSubjectStructureVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/appNjceeSubjectStructure/{id}/", verb = ApiVerb.DELETE, description = "delete appNjceeSubjectStructure by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The appNjceeSubjectStructure Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/appNjceeSubjectStructure/delete-appNjceeSubjectStructure/", verb = ApiVerb.POST, description = "delete appNjceeSubjectStructure", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject AppNjceeSubjectStructureVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/appNjceeSubjectStructure/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AppNjceeSubjectStructureVo> findList();

    @POST
    @Path("/getByAppNjceeScoringSystemId/")
    @ApiMethod(path = "/api/rest/appNjceeSubjectStructure/getByAppNjceeScoringSystemId/", verb = ApiVerb.POST, description = "getByAppNjceeScoringSystemId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AppNjceeSubjectStructureVo> getByAppNjceeScoringSystemId(
            @ApiQueryParam(name = "appNjceeScoringSystemId") @QueryParam("appNjceeScoringSystemId") String appNjceeScoringSystemId);

    @POST
    @Path("/getByAppNjceeScoringSystemIdAndType/")
    @ApiMethod(path = "/api/rest/appNjceeSubjectStructure/getByAppNjceeScoringSystemIdAndType/", verb = ApiVerb.POST, description = "getByAppNjceeScoringSystemIdAndType", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AppNjceeSubjectStructureVo> getByAppNjceeScoringSystemIdAndType(
            @ApiQueryParam(name = "appNjceeScoringSystemId") @QueryParam("appNjceeScoringSystemId") String appNjceeScoringSystemId,
            @ApiQueryParam(name = "type") @QueryParam("type") String type);
}
