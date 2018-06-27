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

import com.accentrix.hku.vo.app.AppNjceeScoringSystemVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月25日 下午8:47:05
 */
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "AppNjceeScoringSystem Service", description = "appNjceeScoringSystemService")
public interface AppNjceeScoringSystemService {
    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/appNjceeScoringSystem/get/", verb = ApiVerb.GET, description = "get appNjceeScoringSystem by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AppNjceeScoringSystemVo get(
            @ApiQueryParam(name = "id", description = "The appNjceeScoringSystem id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/appNjceeScoringSystem/", verb = ApiVerb.POST, description = "save appNjceeScoringSystem", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AppNjceeScoringSystemVo save(@ApiBodyObject AppNjceeScoringSystemVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/appNjceeScoringSystem/save-list/", verb = ApiVerb.POST, description = "save appNjceeScoringSystem list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<AppNjceeScoringSystemVo> save(List<AppNjceeScoringSystemVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/appNjceeScoringSystem/{id}/", verb = ApiVerb.DELETE, description = "delete appNjceeScoringSystem by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The appNjceeScoringSystem Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/appNjceeScoringSystem/delete-appNjceeScoringSystem/", verb = ApiVerb.POST, description = "delete appNjceeScoringSystem", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject AppNjceeScoringSystemVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/appNjceeScoringSystem/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AppNjceeScoringSystemVo> findList();

    @POST
    @Path("/getByProvinceIdAndStream/")
    @ApiMethod(path = "/api/rest/appNjceeScoringSystem/getByProvinceIdAndStream/", verb = ApiVerb.POST, description = "getByProvinceIdAndStream", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    AppNjceeScoringSystemVo getByProvinceIdAndStream(
            @ApiQueryParam(name = "provinceId") @QueryParam("provinceId") String provinceId,
            @ApiQueryParam(name = "stream") @QueryParam("stream") String stream);
}
