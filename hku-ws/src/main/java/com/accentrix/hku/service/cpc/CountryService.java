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

import com.accentrix.hku.vo.cpc.CountryVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 下午1:40:13
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Country Service", description = "countryService")
public interface CountryService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/country/get/", verb = ApiVerb.GET, description = "get country by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CountryVo get(@ApiQueryParam(name = "id", description = "The Country id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/country/", verb = ApiVerb.POST, description = "save country", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CountryVo save(@ApiBodyObject CountryVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/country/save-list/", verb = ApiVerb.POST, description = "save country list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<CountryVo> save(List<CountryVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/country/{id}/", verb = ApiVerb.DELETE, description = "delete country by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The Country id") @PathParam("id") String id);

    @POST
    @Path("/delete-country/")
    @ApiMethod(path = "/api/rest/country/delete-country/", verb = ApiVerb.POST, description = "delete country", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject CountryVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/country/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CountryVo> findList();

    @GET
    @Path("/getByCode/")
    @ApiMethod(path = "/api/rest/country/getByCode/", verb = ApiVerb.GET, description = "getByCode", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    CountryVo getByCode(@ApiQueryParam(name = "code", required = false) @QueryParam("code") String code);

    @POST
    @Path("/getByDesc/")
    @ApiMethod(path = "/api/rest/country/getByDesc/", verb = ApiVerb.POST, description = "getByDesc", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    CountryVo getByDesc(@ApiQueryParam(name = "description") @QueryParam("description") String description);
}
