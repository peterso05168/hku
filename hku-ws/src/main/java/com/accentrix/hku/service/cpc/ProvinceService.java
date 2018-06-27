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

import com.accentrix.hku.vo.cpc.ProvinceCityVo;
import com.accentrix.hku.vo.cpc.ProvinceVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 下午1:40:38
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Province Service", description = "provinceService")
public interface ProvinceService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/province/get/", verb = ApiVerb.GET, description = "get province by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ProvinceVo get(
            @ApiQueryParam(name = "id", required = false, description = "The Province id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/province/", verb = ApiVerb.POST, description = "save province", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ProvinceVo save(@ApiBodyObject ProvinceVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/province/save-list/", verb = ApiVerb.POST, description = "save province list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ProvinceVo> save(List<ProvinceVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/province/{id}/", verb = ApiVerb.DELETE, description = "delete province by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The Province id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/province/delete-province/", verb = ApiVerb.POST, description = "delete province", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject ProvinceVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/province/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ProvinceVo> findList();

    @POST
    @Path("/findByCountryId/")
    @ApiMethod(path = "/api/rest/province/findByCountryId/", verb = ApiVerb.POST, description = "findByCountryId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ProvinceVo> findByCountryId(
            @ApiQueryParam(name = "countryId", required = false) @QueryParam("countryId") String countryId);

    @POST
    @Path("/findByCId/")
    @ApiMethod(path = "/api/rest/province/findByCId/", verb = ApiVerb.POST, description = "findByCId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ProvinceCityVo> findByCId(
            @ApiQueryParam(name = "countryId", required = false) @QueryParam("countryId") String countryId);
}
