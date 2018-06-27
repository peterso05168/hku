package com.accentrix.hku.service.cpc;

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

import com.accentrix.hku.vo.cpc.CityVo;
import com.accentrix.hku.vo.cpc.ProvinceCityVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 下午1:40:56
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "City Service", description = "cityService")
public interface CityService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/city/get/", verb = ApiVerb.GET, description = "get city by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CityVo get(@ApiQueryParam(name = "id", description = "The City id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/city/", verb = ApiVerb.POST, description = "save city", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CityVo save(@ApiBodyObject CityVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/city/save-list/", verb = ApiVerb.POST, description = "save city list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<CityVo> save(List<CityVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/city/{id}/", verb = ApiVerb.DELETE, description = "delete city by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The City Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/city/delete-city/", verb = ApiVerb.POST, description = "delete city", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject CityVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/city/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CityVo> findList();

    @POST
    @Path("/findByCountryIdOrProvinceId/")
    @ApiMethod(path = "/api/rest/city/findByCountryIdOrProvinceId/", verb = ApiVerb.POST, description = "findByCountryIdOrProvinceId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CityVo> findByCountryIdOrProvinceId(
            @ApiQueryParam(name = "countryId", required = false) @QueryParam("countryId") String countryId,
            @ApiQueryParam(name = "provinceId", required = false) @QueryParam("provinceId") String provinceId);

    @POST
    @Path("/findByCountryId/")
    @ApiMethod(path = "/api/rest/city/findByCountryId/", verb = ApiVerb.POST, description = "findByCountryId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ProvinceCityVo> findByCountryId(
            @ApiQueryParam(name = "countryId", required = false) @QueryParam("countryId") String countryId);
}
